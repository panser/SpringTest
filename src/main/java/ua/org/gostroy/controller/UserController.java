package ua.org.gostroy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.service.UserService;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */
@Controller
@Transactional
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/testJSP/findAllResume"})
    public String testJSPFindAllResume(Model model){
        Set<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "testJSPFindAllResume";
    }
}
