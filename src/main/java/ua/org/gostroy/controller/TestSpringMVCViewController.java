package ua.org.gostroy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * Created by panser on 4/16/14.
 */
@Controller
@Transactional
@RequestMapping("/testViews")
public class TestSpringMVCViewController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String indexPage(Model model){
        return "index";
    }

    @RequestMapping(value = {"/jsp"})
    public String testJSPFindAllResume(Model model){
        Set<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "testJSPFindAllResume";
    }

    @RequestMapping(value = {"/jspx"})
    public String testJSPXFindAllResume(Model model){
        Set<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "testJSPXFindAllResume";
    }

    @RequestMapping(value = {"/tiles"})
    public String testTilesFindAllResume(Model model){
        Set<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "testTilesFindAllResume";
    }

    @RequestMapping(value = {"/velocity"})
    public String testVelocityFindAllResume(Model model){
        Set<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "testVelocityFindAllResume";
    }

    @RequestMapping(value = {"/freeMaker"})
    public String testFreeMakerFindAllResume(Model model){
        Set<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "testFreeMakerFindAllResume";
    }

    @RequestMapping(value = {"/xslt"})
    public ModelAndView testXSLTFindAllResume(HttpServletRequest request,
                                        HttpServletResponse response){
        // builds absolute path of the XML file
        String xmlFile = "/WEB-INF/view/xsl//citizens.xml";
        String contextPath = request.getServletContext().getRealPath("");
        String xmlFilePath = contextPath + File.separator + xmlFile;

        Source source = new StreamSource(new File(xmlFilePath));

        // adds the XML source file to the model so the XsltView can detect
        ModelAndView model = new ModelAndView("XSLTView");
        model.addObject("testXSLT", source);

        return model;
    }

    @RequestMapping(value = {"/jasper"})
    public String testJasperFindAllResume(HttpServletRequest request,
                                                HttpServletResponse response, Model model) throws Exception {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);

        model.addAttribute("format", format);

        Set<User> users = userService.findAllUser();
        model.addAttribute("users", users);

        return "testJasperFindAllResume";
    }
}
