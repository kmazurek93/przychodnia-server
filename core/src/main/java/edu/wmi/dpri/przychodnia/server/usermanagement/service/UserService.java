package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by lupus on 21.10.16.
 */
@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
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
}
