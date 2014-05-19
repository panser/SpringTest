package ua.org.gostroy.controller;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.service.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by panser on 4/29/14.
 */
@Controller
@RequestMapping
public class SpringSecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = true)
    UserService userService;

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    VelocityEngine velocityEngine;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(@RequestHeader(value = "referer", required = false) String referer){
        log.trace("Referer Header in /login: " + referer);
        return "login";
    }

    @RequestMapping("/timeout")
    public String timeout() {
        return "timeout";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(@Valid @ModelAttribute("user") User userFromForm, BindingResult result) throws MessagingException{
        User user = new User();
        user.setLogin(userFromForm.getLogin());
        user.setPassword(userFromForm.getPassword());

        String viewName;
        if(result.hasErrors()){
            viewName = "register";
        }
        else
        {
            userService.mergeUser(user);
            viewName = "redirect:/login";
        }
        this.sendEmailOfRegistration(user);
        return viewName;
    }


    public void sendEmailOfRegistration(User user) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("noreply@gostroy.org.ua");
        helper.setTo("gostroy@gmail.com");
        helper.setSubject("Account data");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("login", user.getLogin());
        model.put("password", user.getPassword());
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/emailRegisterTemplate.vm", "UTF-8",model);

        helper.setText(emailText, true);
        ClassPathResource image = new ClassPathResource("templates/avator_default.jpg");
        helper.addInline("accountLogo", image); // Встроенное изображение
        mailSender.send(message);
    }

}
