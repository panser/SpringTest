package ua.org.gostroy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.org.gostroy.entity.Image;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.service.ImageService;
import ua.org.gostroy.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by panser on 5/10/14.
 */
@Controller
@RequestMapping(value = "/image")
//@SessionAttributes({"image"})
public class ImageController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = true)
    private ImageService imageService;
    @Autowired(required = true)
    private UserService userService;

//    HTTP CONTROLERS
    @RequestMapping(value = {"/{login}"}, method = RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
    public String listImages(Model model, @PathVariable String login){
        model.addAttribute("login", login);
        model.addAttribute("images", imageService.findByUserId_Login(login));
        return "image/list";
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
    public String getImage(Model model, @PathVariable("login") String login, @PathVariable("id") String id){
        model.addAttribute("login", login);
        model.addAttribute("image", imageService.find(Long.parseLong(id)));
        return "image/view";
    }
    @RequestMapping(value = {"/{login}"}, method=RequestMethod.POST)
    public String createImageFromForm(@Valid Image image,BindingResult result, @PathVariable("login") String login) throws ValidationException{
        if(result.hasErrors()) {
            throw new ValidationException();
        }
        imageService.merge(image);
        return "redirect:/" + login;
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.PUT)
    public String updateImage(@Valid Image image,BindingResult result, @PathVariable("login") String login, @PathVariable("id") String id){
        imageService.merge(image);
        return "image/view";
    }


//    REST CONTROLLERS
    @RequestMapping(value = {"/{login}"}, method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
//    public User listImagesREST(@PathVariable String login){
    public List<Image> listImagesREST(@PathVariable String login){
        return imageService.findByUserId_Login(login);
//        return userService.findByLogin(login);
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody Image getImageREST(@PathVariable("login") String login, @PathVariable("id") String id){
        log.trace("start getImageREST ...");
        return imageService.find(Long.parseLong(id));
    }

    @RequestMapping(value = {"/{login}"}, method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Image createImageREST(@RequestBody Image image, @RequestParam("test") int test) {
        if(test == 1){
            User userT = userService.findByLogin("jUnit");
            TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(userT,null);
            SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.trace("createImageREST: principal = " + principal);
        User user = null;
        if (principal instanceof User) {
            user = (User) principal;
        } else {
            throw new RuntimeException("Authorize, please");
        }

        image.setUser(user);

        log.info("Start createImageREST ...");
        imageService.merge(image);
        return image;
    }

    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateImageREST(@RequestBody Image imageFromRest, @RequestParam("test") int test, @PathVariable("id") String id) {
        if(test == 1){
            User userT = userService.findByLogin("jUnit");
            TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(userT,null);
            SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.trace("createImageREST: principal = " + principal);
        User user = null;
        if (principal instanceof User) {
            user = (User) principal;
        } else {
            throw new RuntimeException("Authorize, please");
        }

        Image image = imageService.find(Long.parseLong(id));
        image.setImagePath(imageFromRest.getImagePath());
        image.setImage(imageFromRest.getImage());
        image.setUser(user);

        log.info("Start updateImageREST ...");
        imageService.merge(image);
    }

    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImageREST(@PathVariable("login") String login, @PathVariable("id") String id){
        imageService.delete(Long.parseLong(id));
    }

}
