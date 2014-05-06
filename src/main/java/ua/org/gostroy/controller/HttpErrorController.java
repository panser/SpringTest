package ua.org.gostroy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by panser on 5/5/14.
 */
@Controller
@RequestMapping(value = "/errors")
public class HttpErrorController {
    private static final String GENERAL_ERROR_VIEW = "/error/general";

    @RequestMapping(value = "/400.html")
    public ModelAndView handle400(Model model) {
        ModelAndView modelAndView = new ModelAndView(GENERAL_ERROR_VIEW);
        modelAndView.addObject("errorCode", "400");
        modelAndView.addObject("message", "Error 400 happens");
        return modelAndView;
    }

    @RequestMapping(value = "/404.html")
    public ModelAndView handle404(Model model) {
        ModelAndView modelAndView = new ModelAndView(GENERAL_ERROR_VIEW);
        modelAndView.addObject("errorCode", "404");
        modelAndView.addObject("message", "Error 404 happens");
        return modelAndView;
    }

    @RequestMapping(value = "/405.html")
    public ModelAndView handle405(Model model) {
        ModelAndView modelAndView = new ModelAndView(GENERAL_ERROR_VIEW);
        modelAndView.addObject("errorCode", "405");
        modelAndView.addObject("message", "Error 405 happens");
        return modelAndView;
    }

    @RequestMapping(value = "/406.html")
    public ModelAndView handle406(Model model) {
        ModelAndView modelAndView = new ModelAndView(GENERAL_ERROR_VIEW);
        modelAndView.addObject("errorCode", "406");
        modelAndView.addObject("message", "Error 406 happens");
        return modelAndView;
    }

    @RequestMapping(value = "/408.html")
    public ModelAndView handle408(Model model) {
        ModelAndView modelAndView = new ModelAndView(GENERAL_ERROR_VIEW);
        modelAndView.addObject("errorCode", "408");
        modelAndView.addObject("message", "Error 408 happens");
        return modelAndView;
    }

    @RequestMapping(value = "/415.html")
    public ModelAndView handle415(Model model) {
        ModelAndView modelAndView = new ModelAndView(GENERAL_ERROR_VIEW);
        modelAndView.addObject("errorCode", "415");
        modelAndView.addObject("message", "Error 415 happens");
        return modelAndView;
    }

    @RequestMapping(value = "/500.html")
    public ModelAndView handle500(Model model) {
        ModelAndView modelAndView = new ModelAndView(GENERAL_ERROR_VIEW);
        modelAndView.addObject("errorCode", "500");
        modelAndView.addObject("message", "Error 500 happens");
        return modelAndView;
    }

    @RequestMapping(value = "/503.html")
    public ModelAndView handle503(Model model) {
        ModelAndView modelAndView = new ModelAndView(GENERAL_ERROR_VIEW);
        modelAndView.addObject("errorCode", "503");
        modelAndView.addObject("message", "Error 503 happens");
        return modelAndView;
    }
}