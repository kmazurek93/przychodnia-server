package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.model.SetsForSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.STAFF_OR_ADMIN;
import static jersey.repackaged.com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.join;
import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * Created by lupus on 19.11.16.
 */
@Component
public class UserSearchService {

    public static final Sort SORT_BY_ID_ASCENDING = new Sort(ASC, "id");
    @Inject
    private AddressService addressService;
    @Inject
    private UserService userService;
    @Inject
    private PersonService personService;
    @Inject
    private RoleService roleService;

    public Page<User> queryForUsers(UserSearchWebModel model) {
        fillPercentSigns(model);
        SetsForSearch setsForSearch = getSetsForSearchBase(model);
        setsForSearch.setByRole(getDoctorsAndStaffIds());
        return getIntersectionOrEmptyList(setsForSearch, model);
    }

    public Page<User> queryAll(UserSearchWebModel model) {
        fillPercentSigns(model);
        SetsForSearch setsForSearch = getSetsForSearchBase(model);
        setsForSearch.setByRole(getUserIdsFromFoundRoleList(model));
        return getIntersectionOrEmptyList(setsForSearch, model);

    }

    private SetsForSearch getSetsForSearchBase(UserSearchWebModel model) {
        SetsForSearch setsForSearch = new SetsForSearch();
        setsForSearch.setByEmail(getUserIdsFromFoundUsersByMail(model));
        setsForSearch.setByAddress(getUsersFromFoundAddressList(model));
        setsForSearch.setByPerson(getUserIdsFromFoundPersonList(model));
        return setsForSearch;
    }

    private Page<User> getIntersectionOrEmptyList(SetsForSearch setsForSearch, UserSearchWebModel model) {
        return getIntersectionOfAllLists(setsForSearch, model);
    }

    private Page<User> getIntersectionOfAllLists(SetsForSearch setsForSearch, UserSearchWebModel model) {
        Set<Long> allIds = newHashSet(setsForSearch.getByEmail());
        allIds.retainAll(setsForSearch.getByAddress());
        allIds.retainAll(setsForSearch.getByPerson());
        allIds.retainAll(setsForSearch.getByRole());
        PageRequest pageRequest = new PageRequest(model.getPage(), model.getSize(), SORT_BY_ID_ASCENDING);
        return userService.findByIdIn(allIds, pageRequest);
    }

    private Set<Long> getUserIdsFromFoundUsersByMail(UserSearchWebModel userSearchWebModel) {
        List<User> byEmailEntities = userService
                .findByEmailAddressLike(userSearchWebModel.getMail());
        Set<Long> byEmail = newHashSet();
        byEmailEntities.forEach(u -> byEmail.add(u.getId()));
        return byEmail;
    }

    private void fillPercentSigns(UserSearchWebModel userSearchWebModel) {
        userSearchWebModel.setTelephone(join("%", userSearchWebModel.getTelephone(), "%"));
        userSearchWebModel.setAddress(join("%", userSearchWebModel.getAddress(), "%"));
        userSearchWebModel.setMail(join("%", userSearchWebModel.getMail(), "%"));
        userSearchWebModel.setName(join("%", userSearchWebModel.getName(), "%"));
        userSearchWebModel.setRole(join("%", userSearchWebModel.getRole(), "%"));
    }

    private Set<Long> getUserIdsFromFoundPersonList(UserSearchWebModel userSearchWebModel) {
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
        return getUserIdsFromRoles(list);
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

    private Set<Long> getDoctorsAndStaffIds() {
        List<Role> list = roleService.findByNameIn(STAFF_OR_ADMIN);
        return getUserIdsFromRoles(list);
    }

    private Set<Long> getUserIdsFromRoles(List<Role> list) {
        Set<Long> ids = newHashSet();
        List<User> users = newArrayList();
        list.forEach(role -> users.addAll(role.getUsers()));
        users.forEach(u -> ids.add(u.getId()));
        return ids;
    }
}
