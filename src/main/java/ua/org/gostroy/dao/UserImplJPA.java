package ua.org.gostroy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */
//@Repository
public class UserImplJPA implements UserDAO {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findOne(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public Set<User> findAll() {
        log.trace("RUN JPA!!!!/n/n/n");
        Query query = em.createQuery("SELECT e FROM User e");
        List users =  (List<User>) query.getResultList();
        return new HashSet<User>(users);
    }

    @Override
    public Integer save(User user) {
        em.merge(user);
        return user.getId();
    }
}
