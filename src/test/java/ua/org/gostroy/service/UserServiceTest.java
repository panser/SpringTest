package ua.org.gostroy.service;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.org.gostroy.entity.User;

import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/etc/spring/applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testDaoWork(){
        Assert.assertFalse(1 == userService.findAllUser().size());
    }

    @Test
    public void testMergeUser(){
        Set<User> usersAtBegin = userService.findAllUser();
        User oneOfUser = usersAtBegin.iterator().next();
        userService.mergeUser(oneOfUser);
        Assert.assertEquals(usersAtBegin.size(), userService.findAllUser().size());
    }

    @Test
    public void testSaveOrUpdateUser(){
        Set<User> usersAtBegin = userService.findAllUser();
        User oneOfUser = usersAtBegin.iterator().next();
        userService.saveOrUpdate(oneOfUser);
        Assert.assertEquals(usersAtBegin.size(), userService.findAllUser().size());
    }
}
