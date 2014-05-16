package ua.org.gostroy.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.exception.ImageUploadException;
import ua.org.gostroy.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by panser on 4/16/14.
 */
@Controller
@RequestMapping(value = "/user")
//@SessionAttributes({"user"})
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


    @Autowired(required = true)
    private UserService userService;
    @Autowired(required = true)
    private MessageSource messageSource;

    ServletContext context;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/user/list";
    }

    @RequestMapping("/userInfo")
    public String userInfo() {
        return "user/userInfo";
    }

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listUser(Model model){
        log.trace("listUser start ...");
        model.addAttribute("users", userService.findAllUser());
        return "user/listUsers";
    }
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET,produces="application/json")
    @ResponseBody
    public String listUserJSON(Model model){
        return "{id:"+userService.findAllUser().toString()+"}";
    }


    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "user/addUser";
    }
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User userFromForm, BindingResult result, RedirectAttributes redirectAttributes){
        User user = new User();
//        log.trace("RECEIVED user object from EditForm: " + userFromForm);
        user.setLogin(userFromForm.getLogin());
        user.setEmail(userFromForm.getEmail());
        user.setPassword(userFromForm.getPassword());
        user.setBirthDay(userFromForm.getBirthDay());

        if(result.hasErrors()){
            return "user/addUser";
        }
        else
        {
            userService.mergeUser(user);
            redirectAttributes.addFlashAttribute("flashMessageAdd", messageSource.getMessage("flashMessageAdd", null, LocaleContextHolder.getLocale()));
//            redirectAttributes.addFlashAttribute("flashMessageAdd", messageSource.getMessage("flashMessageAdd", null, RequestContextUtils.getLocale()));
//            redirectAttributes.addFlashAttribute("flashMessageAdd",messageSource.getMessage("flashMessageAdd",null,cookieLocaleResolver.getCookieName("locale").toString()));
            return "redirect:/user/list";
        }
    }

    @RequestMapping(value = {"/edit/{login}"}, method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable String login){
        model.addAttribute("user", userService.findByLogin(login));
        return "user/editUser";
    }
    @RequestMapping(value = {"/edit/{login}"}, method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") User userFromForm, BindingResult result,
                           @RequestParam(value = "photo", required = false)MultipartFile photo,
                           RedirectAttributes redirectAttributes, @PathVariable String login){
        User user = userService.findByLogin(login);
        log.trace("RECEIVED user object from EditForm: " + userFromForm);
        user.setLogin(userFromForm.getLogin());
        user.setEmail(userFromForm.getEmail());
        user.setPassword(userFromForm.getPassword());
        user.setBirthDay(userFromForm.getBirthDay());
        log.trace("SAVED user object: " + user);

        if(result.hasErrors()){
            return "user/editUser";
        }
        else
        {
            try{
                if(!photo.isEmpty()){
                    validateImage(photo);
                    String destinationPath = saveImage(photo, user.getId());
                    user.setAvatorPath("/resources" + destinationPath);
                }
            }catch (ImageUploadException e){
                result.rejectValue("avatorPath","exception",e.getMessage());
                return "user/editUser";
            }
            userService.mergeUser(user);
            redirectAttributes.addFlashAttribute("flashMessageEdit", messageSource.getMessage("flashMessageEdit", null, LocaleContextHolder.getLocale()));
            return "redirect:/user/list";
        }
    }
    private void validateImage(MultipartFile image) throws ImageUploadException{
        log.trace("start validateImage ...");
        if(!image.getContentType().equals("image/jpeg")){
            log.trace("validateImage throw Exception.");
//            throw new ImageUploadException("Only JPG images accepted");
            throw new ImageUploadException(messageSource.getMessage("ua.org.gostroy.exception.OnlyJpeg", null, LocaleContextHolder.getLocale()));
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


    @RequestMapping(value = {"/delete/{login}"}, method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String login, RedirectAttributes redirectAttributes){
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
        redirectAttributes.addFlashAttribute("flashMessageDelete", messageSource.getMessage("flashMessageDelete", null, LocaleContextHolder.getLocale()));
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/avator/{login}", headers = "Accept=image/jpeg, image/jpg, image/png, image/gif", method = RequestMethod.GET)
    @ResponseBody
    public BufferedImage getAvator(@PathVariable String login) {
        User user = userService.findByLogin(login);
        String pathAvator = user.getAvatorPath();
        pathAvator = pathAvator.split("resources/")[1];
        try {
//            InputStream inputStream = this.getClass().getResourceAsStream("myimage.jpg");
            InputStream inputStream = new FileInputStream("C:/Users/panser/Dropbox/02.home/IdeaProjects/TEMPLATE/SpringTest/staticresources/" + pathAvator);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            Graphics g = bufferedImage.getGraphics();
            g.drawString("gostroy.org.ua",20,20);
            return bufferedImage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
