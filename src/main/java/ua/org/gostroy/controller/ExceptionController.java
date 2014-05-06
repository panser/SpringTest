package ua.org.gostroy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.gostroy.exception.CustomException;
import ua.org.gostroy.exception.DefaultException;
import ua.org.gostroy.exception.FunnyException;
import ua.org.gostroy.exception.SecondCustomException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by panser on 5/6/14.
 */
@Controller
public class ExceptionController {

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public String exception(@RequestParam(required = false) String type)
            throws NumberFormatException, IOException, DefaultException,
            CustomException, SecondCustomException, FunnyException {

        if ("catched".equals(type)) {
            throw new IOException("Hi! I am IOException!");
        } else if ("custom".equals(type)) {
            throw new CustomException("Hi! I'm CustomException!");
        } else if ("secondCustom".equals(type)) {
            throw new SecondCustomException("Hi! I'm SecondCustomException!");
        } else if ("uncatched".equals(type)) {
            throw new NumberFormatException("Hi! I am NumberFormatException!");
        } else if ("funny".equals(type)) {
            throw new FunnyException("Hi! I'm FunnyException!");
        } else if (type != null) {
            throw new DefaultException("I'm DefaultException");
        }

        return "index";
    }

    @RequestMapping(value = "/http-error", method = RequestMethod.GET)
    public String httpError(@RequestParam(required = false) int code, HttpServletResponse response)
            throws NumberFormatException, IOException, DefaultException, CustomException, SecondCustomException {
        if (code >= 400 && code <= 505) {
            response.sendError(code);
        }
        return "index";
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(IOException exception) {
        ModelAndView modelAndView = new ModelAndView("/exception/catchedException");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}
