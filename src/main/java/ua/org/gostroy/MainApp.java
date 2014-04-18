package ua.org.gostroy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.service.UserService;

/**
 * Created by panser on 4/16/14.
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/etc/spring/applicationContext.xml");
        UserService userService = context.getBean(UserService.class);
        for(User user : userService.findAllUser()){
            System.out.println(user);
        }
    }
}
