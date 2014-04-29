package ua.org.gostroy.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
        log.trace("Find UserDAO with id = " + id + " ...");
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        if (user != null) {
            log.trace("Find UserDAO with id: " + user.getId());
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        log.trace("Find UserDAO with login = " + login + " ...");
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        List<User> users = criteria.add(Restrictions.like("login", login)).list();
        User user = users.get(0);
        if (user != null) {
            log.trace("Find UserDAO with id: " + user.getId());
        }
        return user;
    }

    @Override
    public Set<User> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
//        List results = criteria.add(Restrictions.isNull("photoName")).list();
        List results = criteria.add(Restrictions.isNull("deleteDate")).list();
//        List results = criteria.list();
        log.trace("Find all UserDAO ...");
        HashSet<User> hashSet = new HashSet<User>(results);
//        HashSet<User> hashSet = new HashSet<User>(criteria.list());
        log.trace("Find " + hashSet.size() + " UserDAO");
        return hashSet;
    }

    @Override
    public Integer save(User user) {
        log.trace("Save UserDAO with id = " + user.getId() + " ...");
        Integer idNew = (Integer) sessionFactory.getCurrentSession().save(user);
        log.trace("Saved UserDAO with id = " + idNew);
        return idNew;
    }

    @Override
    public User merge(User user) {
        log.trace("Save UserDAO with id = " + user.getId() + " ...");
        User userNew = (User)sessionFactory.getCurrentSession().merge(user);
        log.trace("Saved UserDAO with id = " + userNew.getId());
        return userNew;
    }

    @Override
    public Integer saveOrUpdate(User user) {
        log.trace("Save UserDAO with id = " + user.getId() + " ...");
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        Integer idNew =  user.getId();
        log.trace("Saved UserDAO with id = " + idNew);
        return idNew;
    }

    @Override
    public void update(User user) {
        log.trace("Update UserDAO with id = " + user.getId() + " ...");
        if (user != null) {
            sessionFactory.getCurrentSession().update(user);
        }
        log.trace("Updated UserDAO");
    }

    @Override
    public Integer delete(String login) {
        log.trace("Delete UserDAO with login = " + login + " ...");
        User delUser = findByLogin(login);
        if (delUser != null) {
            sessionFactory.getCurrentSession().delete(delUser);
/*
            delUser.setDeleteDate(new Date());
            update(delUser);
*/
            log.info("Delete UserDAO with id = " + delUser.getId());
            return delUser.getId();
        }
        log.trace("Delete UseDAO: no User find.");
        return 0;
    }
}
