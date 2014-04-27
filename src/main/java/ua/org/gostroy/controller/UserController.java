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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

//    for test bean Life Circle
    @PostConstruct
    protected void beforeConstruct(){
        System.out.println(getClass() + " :post construct method invoked");
    }
    @PreDestroy
    protected void beforeDestroy() {
        System.out.println(getClass() + "before destroy method invoked");
    }
//    for test bean Life Circle


    @Autowired
    private UserService userService;

    ServletContext context;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/user/list";
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
            return "redirect:/user/";
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
                    String destinationPath = saveImage(photo, user.getId());
                    user.setPhotoName("/resources" + destinationPath);
                }
            }catch (ImageUploadException e){
                result.rejectValue("photoName","exception",e.getMessage());
                return "editUser";
            }
            userService.mergeUser(user);
            return "redirect:/user/";
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
    private String saveImage(MultipartFile image, Integer id) throws ImageUploadException{
        log.trace("start saveImage ...");
        String destinationPath = "";
        try{
            String destinationDirectory = context.getRealPath("") + "/../../staticresources";
            destinationPath = calculateDestinationPath(image, id);
            File file = new File(destinationDirectory + destinationPath);
            log.trace("image will save in " + file.getPath());
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            log.trace("image saved: " + image.getBytes().length + " bytes");
        }catch (IOException e){
            throw new ImageUploadException("Unable to save image", e);
        }
        log.trace("finish saveImage.");
        return destinationPath;
    }
    private String calculateDestinationPath(MultipartFile multipartFile, Integer id){
//        String destinationDirectory = context.getRealPath("") + "/WEB-INF/classes/static/image/photo/" + id + "/";
        String destinationPath = "/image/photo/" + id + "/" + multipartFile.getOriginalFilename();
        return destinationPath;
    }


    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listUser(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "listUsers";
    }
    @RequestMapping(value = {"/delete/{login}"}, method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String login){
        log.trace("start deleteUser with login: " + login + "...");
        Integer delId = userService.deleteUser(login);
        if (delId != 0){
            try{
                String delFile = context.getRealPath("") + "/../../staticresources" + "/image/photo/" + delId;
                log.trace("try del Used directory: " + delFile);
                FileUtils.deleteDirectory(new File(delFile));
            }catch (IOException e){
                log.trace("Exception with del Used directory: " + e.toString());
            }
        }
        log.trace("finish deleteUser with login: " + login + ".");
        return "redirect:/user/";
    }
}
