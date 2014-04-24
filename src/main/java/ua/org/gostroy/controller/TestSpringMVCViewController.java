package ua.org.gostroy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.service.UserService;

import javax.validation.Valid;

/**
 * Created by panser on 4/16/14.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes("user")
public class TestSpringMVCViewController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "addUser";
    }
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "error";
        }
        else
        {
            userService.mergeUser(user);
            return "redirect:/user/list";
        }
    }

    @RequestMapping(value = {"/edit/{login}"}, method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable String login){
        model.addAttribute("user", userService.findByLogin(login));
        return "editUser";
    }
    @RequestMapping(value = {"/edit/{login}"}, method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "error";
        }
        else
        {
            userService.mergeUser(user);
            return "redirect:/user/list";
        }
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUser(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "listUsers";
    }
}
