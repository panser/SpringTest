package ua.org.gostroy.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ua.org.gostroy.entity.Image;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by panser on 5/16/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/etc/spring/applicationContext.xml", "classpath:/etc/spring/applicationContext.d/*",
        "classpath:/etc/spring/servletContext.xml", "classpath:/etc/spring/servletContext.d/*"})
public class ImageControllerTest {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public final String SERVER_URI = "http://localhost:8081/SpringTest";
    public final String testLogin = "login8";

    @Test
    public void testListImagesREST(){
        RestTemplate restTemplate = new RestTemplate();
//        Image[] imagesFromRest = restTemplate.getForObject(SERVER_URI+"/image/login8.json", Image[].class);
        ResponseEntity<Image[]> imagesFromRestEntity = restTemplate.getForEntity(SERVER_URI+"/image/login8.json", Image[].class);
        log.trace("StatusCode: " + imagesFromRestEntity.getStatusCode().toString());
        Image[] imagesFromRest = imagesFromRestEntity.getBody();
        for(Image image : imagesFromRest){
            log.trace("id=" + image.getId() + ", Path=" + image.getImagePath() + ", CreateDate=" + image.getCreateDate());
        }
    }
}
