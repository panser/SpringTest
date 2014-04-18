package ua.org.gostroy.service;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by panser on 4/16/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/etc/spring/applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void findAllUser(){
        Assert.assertEquals(9, userService.findAllUser().size());
    }
}
