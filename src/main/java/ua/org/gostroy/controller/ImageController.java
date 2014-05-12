package ua.org.gostroy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.org.gostroy.entity.Image;
import ua.org.gostroy.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

/**
 * Created by panser on 5/10/14.
 */
@Controller
@RequestMapping(value = "/image")
@SessionAttributes({"image"})
public class ImageController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageService imageService;

//    HTTP CONTROLERS
    @RequestMapping(value = {"/{login}"}, method = RequestMethod.GET)
    public String listImages(Model model, @PathVariable String login){
        model.addAttribute("images", imageService.findByUserId_Login(login));
        return "image/list";
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.GET)
    public String getImage(Model model, @PathVariable("login") String login, @PathVariable("id") String id){
        model.addAttribute("image", imageService.find(Long.parseLong(id)));
        return "image/view";
    }
    @RequestMapping(value = {"/{login}"}, method=RequestMethod.POST, headers="Accept=text/html")
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
    @RequestMapping(value = {"/{login}"}, method = RequestMethod.GET, headers="Accept=application/json")
    public @ResponseBody List<Image> listImagesREST(@PathVariable String login){
        return imageService.findByUserId_Login(login);
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.GET, headers="Accept=application/json")
    public @ResponseBody Image getImageREST(@PathVariable("login") String login, @PathVariable("id") String id){
        return imageService.find(Long.parseLong(id));
    }
    @RequestMapping(value = {"/{login}"}, method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Image createImageREST(@Valid Image image,BindingResult result, @PathVariable("login") String login, HttpServletResponse response) throws ValidationException{
        if(result.hasErrors()) {
            throw new ValidationException();
        }
        imageService.merge(image);
        response.setHeader("Location", "/"+ login + "/"  + image.getId());
        return image;
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.PUT, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateImageREST(@Valid Image image,@PathVariable("login") String login, @PathVariable("id") String id) {
        imageService.merge(image);
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImageREST(@PathVariable("login") String login, @PathVariable("id") String id){
        imageService.delete(Long.parseLong(id));
    }
}
