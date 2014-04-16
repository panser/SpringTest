package ua.org.gostroy.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */
@Repository
public class UserImplHibernate implements UserDAO{
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public User find(Integer id) {
        log.info("Find UserDAO with id = " + id + " ...");
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        if (user != null) {
            log.info("Find UserDAO with id: " + user.getId());
        }
        return user;
    }

    @Override
    public Set<User> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        log.info("Find all UserDAO ...");
        HashSet<User> hashSet = new HashSet<User>(criteria.list());
        log.info("Find " + hashSet.size() + " UserDAO");
        return hashSet;
    }

    @Override
    public Integer save(User user) {
        log.info("Save UserDAO with id = " + user.getId() + " ...");
        Integer idNew = (Integer) sessionFactory.getCurrentSession().save(user);
        log.info("Saved UserDAO with id = " + idNew);
        return idNew;
    }

    @Override
    public void update(User user) {
        log.info("Update UserDAO with id = " + user.getId() + " ...");
        if (user != null) {
            sessionFactory.getCurrentSession().update(user);
        }
        log.info("Updated UserDAO");
    }
}
