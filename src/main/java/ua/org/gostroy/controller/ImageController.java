package ua.org.gostroy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.org.gostroy.entity.Image;
import ua.org.gostroy.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.IOException;
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

//    HTTP CONTROLERS
    @RequestMapping(value = {"/{login}"}, method = RequestMethod.GET)
    public String listImages(Model model, @PathVariable String login){
        model.addAttribute("login", login);
        model.addAttribute("images", imageService.findByUserId_Login(login));
        return "image/list";
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.GET)
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
    @RequestMapping(value = {"/{login}"}, method = RequestMethod.GET, headers={"Accept=application/json"})
    @ResponseBody
    public List<Image> listImagesREST(@PathVariable String login){
        return imageService.findByUserId_Login(login);
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.GET, headers={"Accept=application/json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Image getImageREST(@PathVariable("login") String login, @PathVariable("id") String id){
        log.trace("start getImageREST ...");
        return imageService.find(Long.parseLong(id));
    }
    @RequestMapping(value = {"/{login}"}, method=RequestMethod.POST, headers = "Content-Type=application/json", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Image createImageREST(@Valid @RequestBody String jsonImage,@PathVariable("login") String login, HttpServletResponse response){
        Image image = null;
        try{
            image = new ObjectMapper().readValue(jsonImage, Image.class);
            imageService.merge(image);
        } catch (IOException e){}
        response.setHeader("Location", "/"+ login + "/"  + image.getId());
        return image;
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.PUT, headers="Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateImageREST(@Valid @RequestBody Image image,@PathVariable("login") String login, @PathVariable("id") String id) {
        imageService.merge(image);
    }
    @RequestMapping(value = {"/{login}/{id}"}, method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImageREST(@PathVariable("login") String login, @PathVariable("id") String id){
        imageService.delete(Long.parseLong(id));
    }
}
