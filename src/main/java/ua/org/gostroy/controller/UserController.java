package ua.org.gostroy.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.exception.ImageUploadException;
import ua.org.gostroy.service.UserService;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * Created by panser on 4/16/14.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes("user")
public class UserController implements ServletContextAware {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    ServletContext context;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "addUser";
    }
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            return "addUser";
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
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult result,
                           @RequestParam(value = "photo", required = false)MultipartFile photo){
        if(result.hasErrors()){
            return "editUser";
        }
        else
        {
            try{
                if(!photo.isEmpty()){
                    validateImage(photo);
                    saveImage(photo, user.getId());
                    user.setPhotoName(photo.getOriginalFilename());
                }
            }catch (ImageUploadException e){
                result.rejectValue("photoName","exception",e.getMessage());
                return "editUser";
            }
            userService.mergeUser(user);
            return "redirect:/user/list";
        }
    }
    private void validateImage(MultipartFile image) throws ImageUploadException{
        log.trace("start validateImage ...");
        if(!image.getContentType().equals("image/jpeg")){
            log.trace("validateImage throw Exception.");
            throw new ImageUploadException("Only JPG images accepted");
        }
        log.trace("validateImage is OK.");
    }
    private void saveImage(MultipartFile image, Integer id) throws ImageUploadException{
        log.trace("start saveImage ...");
        try{
            File file = new File(calculateDestinationPath(image, id));
            log.trace("image will save in " + file.getPath());
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            log.trace("image saved: " + image.getBytes().length + " bytes");
        }catch (IOException e){
            throw new ImageUploadException("Unable to save image", e);
        }
        log.trace("finish saveImage.");
    }
    private String calculateDestinationPath(MultipartFile multipartFile, Integer id){
//        String destinationDirectory = context.getRealPath("") + "/WEB-INF/classes/static/image/photo/" + id + "/";
        String destinationDirectory = context.getRealPath("") + "/../../staticresources" + "/image/photo/" + id + "/";
        String destinationPath = destinationDirectory + multipartFile.getOriginalFilename();
        return destinationPath;
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUser(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "listUsers";
    }
}
