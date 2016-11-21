package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataWebModel;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.NotFoundException;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator;
import edu.wmi.dpri.przychodnia.server.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static org.hibernate.Hibernate.initialize;

/**
 * Created by lupus on 21.10.16.
 */
@Service
public class UserService {

    @Inject
    private UserRepository userRepository;
    @Inject
    private RoleService roleService;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            initializeChildEntities(user);
        }
        return user;
    }

    private void initializeChildEntities(User user) {
        initialize(user.getRoles());
        initialize(user.getPerson());
        initialize(user.getPerson().getAddress());
        initialize(user.getPerson().getMailingAddress());
        initialize(user.getPerson().getIdType());
        initialize(user.getPerson().getSex());
        //TODO initialize rest <if you find anything>

    }

    @Transactional(readOnly = true)
    public List<User> getAllInitializedUsers() {
        List<User> all = userRepository.findByActiveTrue();
        all.forEach(this::initializeChildEntities);
        return all;
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        User byId = userRepository.findOne(id);
        if (byId == null) {
            ErrorMessage errorMessage = ErrorMessageGenerator.getNotFoundErrorMessage("USER");
            throw new NotFoundException(errorMessage);
        }
        initializeChildEntities(byId);
        return byId;
    }

    @Transactional(readOnly = true)
    public boolean emailExists(String email) {
        User byEmail = userRepository.findByEmailAddress(email);
        return (byEmail != null);
    }

    @Transactional(readOnly = true)
    public boolean usernameExists(String username) {
        User byLogin = userRepository.findByLogin(username);
        return (byLogin != null);
    }

    @Transactional
    public void archivizeUser(Long id) {
        User byId = userRepository.findOne(id);
        if (byId == null) {
            ErrorMessage errorMessage = ErrorMessageGenerator.getNotFoundErrorMessage("USER");
            throw new NotFoundException(errorMessage);
        }
        byId.setActive(false);
        userRepository.save(byId);
    }

    @Transactional
    public void updateUser(UserDataWebModel userData, boolean isAdminOrStaff) {
        User user = userRepository.findOne(userData.getId());
        if (isAdminOrStaff) {
            List<String> roles = userData.getRoles();
            if (roles != null) {
                user.setRoles(roleService.findByNameIn(roles));
            }
            user.setLogin(userData.getUsername());
            user.setActive(userData.isActive());
        }
        user.setEmailAddress(userData.getEmailAddress());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findByIdIn(Set<Long> ids) {
        List<User> byIdIn = userRepository.findByIdIn(ids);
        byIdIn.forEach(this::initializeChildEntities);
        return byIdIn;
    }

    @Transactional(readOnly = true)
    public Page<User> findByIdIn(Set<Long> ids, PageRequest pageRequest) {
        Page<User> byIdIn = userRepository.findByIdIn(ids, pageRequest);
        byIdIn.getContent().forEach(this::initializeChildEntities);
        return byIdIn;
    }

    @Transactional(readOnly = true)
    public List<User> findByEmailAddressLike(String likeEmail) {
        return userRepository.findByEmailAddressLike(likeEmail);
    }
}
