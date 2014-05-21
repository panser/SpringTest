package ua.org.gostroy.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.org.gostroy.entity.User;

import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */
public interface UserDAO {
    User findOne(Integer id);
    Set<User> findAll();
    Integer save(User user);
}
