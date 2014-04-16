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
    public User findUserById(final Integer id) {
        log.trace("Find User with id = " + id + " ...");
        User user = userDAO.find(id);
        if (user != null) {
            log.trace("Find User with id: " + user.getId());
            return user;
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Set<User> findAllUser() {
        log.trace("Find all User in repository ...");
        Set<User> users = new HashSet<User>(userDAO.findAll());
        log.trace("Find all User in repository with countUser: " + users.size());
        return users;
    }

    @Transactional(rollbackFor = Exception.class)
    public User saveUser(final User user) throws ConstraintViolationException {
        log.trace("Save User in repository with id = " + user.getId() + " ...");
        userDAO.save(user);
        log.trace("Saved User in repository with id = " + user.getId());
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User updateUser(final User user) throws ConstraintViolationException {
        log.trace("Update User in repository with id = " + user.getId() + " ...");
        userDAO.update(user);
        log.trace("Updated User in repository with id = " + user.getId());
        return user;
    }
}