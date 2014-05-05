package ua.org.gostroy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by panser on 4/30/14.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/testHeaders"}, method = RequestMethod.GET)
    public String login(HttpEntity<byte[]> requestEntity, Model model){
        Map<String,String> requestHeaderMap = requestEntity.getHeaders().toSingleValueMap();
//        log.trace("requestHeaderMap: " + requestHeaderMap);
        model.addAttribute("requestHeaderMap",requestHeaderMap);
        return "test/testHeaders";
    }
}
