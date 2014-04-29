package ua.org.gostroy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by panser on 4/29/14.
 */
@Controller
@RequestMapping
public class SpringSecurityController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/error403", method = RequestMethod.GET)
    public String error403(){
        return "error403";
    }
}
