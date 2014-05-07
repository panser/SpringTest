package ua.org.gostroy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by panser on 4/29/14.
 */
@Controller
@RequestMapping
public class SpringSecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(@RequestHeader(value = "referer", required = false) String referer){
        log.trace("Referer Header in /login: " + referer);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(@RequestHeader(value = "referer", required = false) String referer) {
        log.trace("Referer Header in /logout: " + referer);
        return "logout";
    }

    @RequestMapping("/timeout")
    public String timeout() {
        return "timeout";
    }
}
