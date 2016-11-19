package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static jersey.repackaged.com.google.common.collect.Sets.newHashSet;

/**
 * Created by lupus on 19.11.16.
 */
@Component
public class UserSearchService {

    @Inject
    private AddressService addressService;
    @Inject
    private UserService userService;
    @Inject
    private PersonService personService;
    @Inject
    private RoleService roleService;


    public List<User> queryAll(UserSearchWebModel userSearchWebModel) {
        fillPercentSigns(userSearchWebModel);

        Set<Long> byEmail = getUserIdsFromFoundUsersByMail(userSearchWebModel);
        Set<Long> byAddress = getUsersFromFoundAddressList(userSearchWebModel);
        Set<Long> byPerson = getUserIdssFromFoundPersonList(userSearchWebModel);
        Set<Long> byRole = getUserIdsFromFoundRoleList(userSearchWebModel);

        if (byAddress.isEmpty() || byEmail.isEmpty() || byPerson.isEmpty() || byRole.isEmpty()) {
            return newArrayList();
        }

        return getIntersectionOfAllLists(byEmail, byAddress, byPerson, byRole);
    }

    private Set<Long> getUserIdsFromFoundUsersByMail(UserSearchWebModel userSearchWebModel) {
        List<User> byEmailEntities = userService
                .findByEmailAddressLike(userSearchWebModel.getMail());
        Set<Long> byEmail = newHashSet();
        byEmailEntities.forEach(u -> byEmail.add(u.getId()));
        return byEmail;
    }

    private List<User> getIntersectionOfAllLists(
            Set<Long> byEmail, Set<Long> byAddress,
            Set<Long> byPerson, Set<Long> byRole) {
        Set<Long> allIds = newHashSet(byEmail);
        allIds.retainAll(byAddress);
        allIds.retainAll(byPerson);
        allIds.retainAll(byRole);

        return userService.findByIdIn(allIds);
    }

    private void fillPercentSigns(UserSearchWebModel userSearchWebModel) {
        userSearchWebModel.setTelephone(StringUtils.join("%", userSearchWebModel.getTelephone(), "%"));
        userSearchWebModel.setAddress(StringUtils.join("%", userSearchWebModel.getAddress(), "%"));
        userSearchWebModel.setMail(StringUtils.join("%", userSearchWebModel.getMail(), "%"));
        userSearchWebModel.setName(StringUtils.join("%", userSearchWebModel.getName(), "%"));
        userSearchWebModel.setRole(StringUtils.join("%", userSearchWebModel.getRole(), "%"));
    }

    private Set<Long> getUserIdssFromFoundPersonList(UserSearchWebModel userSearchWebModel) {
        List<Person> list = personService
                .searchQueryOnNamesAndPhone(userSearchWebModel.getName(), userSearchWebModel.getTelephone());
        if (list.isEmpty()) {
            return newHashSet();
        }
        return getUserIdsFromPersons(list);
    }

    private Set<Long> getUserIdsFromPersons(List<Person> list) {
        Set<Long> ids = newHashSet();
        list.forEach(person -> {
            User user = person.getUserIfExists();
            if (user != null) ids.add(user.getId());
        });
        return ids;
    }

    private Set<Long> getUserIdsFromFoundRoleList(UserSearchWebModel userSearchWebModel) {
        List<Role> list = roleService
                .queryLikeName(userSearchWebModel.getRole());
        Set<Long> ids = newHashSet();
        List<User> users = newArrayList();
        list.forEach(role -> users.addAll(role.getUsers()));
        users.forEach(u -> ids.add(u.getId()));
        return ids;
    }

    private Set<Long> getUsersFromFoundAddressList(UserSearchWebModel userSearchWebModel) {
        List<Address> list = addressService
                .searchQueryOnAll(userSearchWebModel.getAddress());
        Set<Long> ids = newHashSet();
        list.forEach(address -> {
            ids.addAll(getUserIdsFromPersons(address.getPersons()));
            ids.addAll(getUserIdsFromPersons(address.getPersonsWithMailingAddress()));
        });
        return ids;
    }
}
