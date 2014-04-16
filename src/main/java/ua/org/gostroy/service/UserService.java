package ua.org.gostroy.service;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.dao.UserDAO;
import ua.org.gostroy.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */
@Service
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    public final User findUserById(final Integer id) {
        log.info("Find User with id = " + id + " ...");
        User user = userDAO.find(id);
        if (user != null) {
            log.info("Find User with id: " + user.getId());
            return user;
        }
        return null;
    }

    @Transactional(readOnly = true)
    public final Set<User> findAllUser() {
        log.info("Find all User in repository ...");
        Set<User> users = new HashSet<User>(userDAO.findAll());
        log.info("Find all User in repository with countUser: " + users.size());
        return users;
    }

    @Transactional(rollbackFor = Exception.class)
    public final User saveUser(final User user) throws ConstraintViolationException {
        log.info("Save User in repository with id = " + user.getId() + " ...");
        userDAO.save(user);
        log.info("Saved User in repository with id = " + user.getId());
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public final User updateUser(final User user) throws ConstraintViolationException {
        log.info("Update User in repository with id = " + user.getId() + " ...");
        userDAO.update(user);
        log.info("Updated User in repository with id = " + user.getId());
        return user;
    }
}
