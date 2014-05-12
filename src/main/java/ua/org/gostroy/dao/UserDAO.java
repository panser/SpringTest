package ua.org.gostroy.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.org.gostroy.entity.User;

import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */
public interface UserDAO {
    User find(Integer id);
    User findByLogin(String str);
    Set<User> findAll();
    Integer save(User user);
    User merge(User user);
    Integer saveOrUpdate(User user);
    void update(User user);
    Integer delete(String login);
    public Integer delete(User user);
}
