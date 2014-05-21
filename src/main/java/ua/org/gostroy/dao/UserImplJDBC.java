package ua.org.gostroy.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.entity.User;

import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */
//@Repository
public class UserImplJDBC extends NamedParameterJdbcDaoSupport implements UserDAO {
    @Override
    public User findOne(Integer id) {
//        getNamedParameterJdbcTemplate().
        return null;
    }

    @Override
    public Set<User> findAll() {
        return null;
    }

    @Override
    public Integer save(User user) {
        return null;
    }
}
