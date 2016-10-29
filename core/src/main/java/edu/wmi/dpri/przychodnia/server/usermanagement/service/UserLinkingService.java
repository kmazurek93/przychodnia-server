package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by lupus on 29.10.16.
 */
@Service
public class UserLinkingService {
    @Inject
    private UserRepository userRepository;

    @Transactional
    public void connectUsers(Long parentId, Long childId) {
        User parent = userRepository.findOne(parentId);
        User child = userRepository.findOne(childId);
        parent.getChildUsers().add(child);
        userRepository.save(parent);
    }

    @Transactional
    public void removeConnection(Long parentId, Long childId) {
        User parent = userRepository.findOne(parentId);
        User child = userRepository.findOne(childId);
        parent.getChildUsers().removeIf(o -> o.getId().equals(childId));
        child.getParentUsers().removeIf(o -> o.getId().equals(parentId));
        userRepository.save(parent);
        userRepository.save(child);
    }
}
