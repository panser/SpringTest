package ua.org.gostroy.controller;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import ua.org.gostroy.entity.Image;
import ua.org.gostroy.entity.User;
import ua.org.gostroy.service.ImageService;
import ua.org.gostroy.service.UserService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by panser on 5/16/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/etc/spring/applicationContext.xml", "classpath:/etc/spring/applicationContext.d/*",
        "classpath:/etc/spring/servletContext.xml", "classpath:/etc/spring/servletContext.d/*"})
//@ActiveProfiles("test")
public class ImageControllerTest {
    private final Logger log = LoggerFactory.getLogger(getClass());
    //    public String SERVER_URI="http://localhost:8081/SpringTest";
    @Value("${value.server_uri}")
    public String SERVER_URI;

    @Autowired(required = true)
    private ImageService imageService;
    @Autowired(required = true)
    private UserService userService;

    private User userJUnit;
    private Image imageJUnit;
    @Before
    public void before() {
        User user = new User("jUnit");
        userJUnit = userService.saveUser(user);
        Image image = new Image(userJUnit,"/j/u/n/i/t");
        imageJUnit = imageService.merge(image);
    }
    @After
    public void after(){
        userService.deleteUser(userJUnit);
    }

    @Test
    public void testListImagesREST(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Image[]> imagesFromRestEntity = restTemplate.getForEntity(SERVER_URI+"/image/{login}.json", Image[].class,userJUnit.getLogin());
        log.trace("StatusCode: " + imagesFromRestEntity.getStatusCode().toString());
        Image[] imagesFromRest = imagesFromRestEntity.getBody();
        for(Image image : imagesFromRest){
            log.trace("id=" + image.getId() + ", Path=" + image.getImagePath() + ", CreateDate=" + image.getCreateDate());
        }
    }

    @Test
    public void testCreateImageREST(){
        byte[] imageByte;
        try{
            FileInputStream image = new FileInputStream("C:/Users/panser/Dropbox/02.home/IdeaProjects/TEMPLATE/SpringTest/src/main/resources/templates/avator_default.jpg");
            imageByte = IOUtils.toByteArray(image);
        }catch (IOException e) {
            log.trace("testCreateImageREST(): can't read file");
            throw new RuntimeException(e);
        }

        Image testImage = new Image("/t/e/s/t",imageByte);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Image> response = restTemplate.postForEntity(SERVER_URI+"/image/{login}.json?test=1",testImage,Image.class,userJUnit.getLogin());
//        String responseLocation = restTemplate.postForLocation(SERVER_URI+"/image/login8.json",testImage,Image.class).toString();

        log.trace("StatusCode: " + response.getStatusCode().toString());
        Image imagesFromRest = response.getBody();
        log.trace("id=" + imagesFromRest.getId() + ", Path=" + imagesFromRest.getImagePath() + ", CreateDate=" + imagesFromRest.getCreateDate());
    }

    @Test
    public void testUpdateImageREST(){
        byte[] imageByte;
        try{
            FileInputStream image = new FileInputStream("C:/Users/panser/Dropbox/02.home/IdeaProjects/TEMPLATE/SpringTest/src/main/resources/templates/avator_default.jpg");
            imageByte = IOUtils.toByteArray(image);
        }catch (IOException e) {
            log.trace("testUpdateImageREST(): can't read file");
            throw new RuntimeException(e);
        }

        Image testImage = new Image("/u/p/d/a/t/e",imageByte);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(SERVER_URI+"/image/{login}/{id}.json?test=1",testImage,userJUnit.getLogin(),imageJUnit.getId());
    }

}
