package ua.org.gostroy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.service.UserService;

import javax.validation.Valid;

/**
 * Created by panser on 4/16/14.
 */
@Controller
@RequestMapping
public class TestSpringMVCViewController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String registrationIndex(Model model){
        model.addAttribute("User", new User());
        return "index";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public String registrationUser(@Valid @ModelAttribute("User") User user, BindingResult result,
                                   Model model){
        if(result.hasErrors()){
            return "error";
        }
        else
        {
            userService.saveUser(user);
            return "index";
        }
    }
}
