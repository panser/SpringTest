package ua.org.gostroy.service;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.dao.ImageDAO;
import ua.org.gostroy.dao.UserDAO;
import ua.org.gostroy.entity.Image;
import ua.org.gostroy.entity.User;

import java.util.List;

/**
 * Created by panser on 5/10/14.
 */
@Service
@Transactional
public class ImageService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageDAO imageDAO;
    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    public Image find(final Long id) {
        log.trace("Find image with id = " + id + " ...");
        Image image = imageDAO.find(id);
        if (image != null) {
            log.trace("Find image with id: " + image.getId());
            return image;
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Image> findByUserId_Login(final String login) {
        log.trace("Find images by User.login = " + login + " ...");
        User user = userDAO.findByLogin(login);
        List<Image> images = imageDAO.findByUser(user);
        if (images != null) {
            log.trace("Find " + images.size() + " images by User login = " + login);
            return images;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public Image merge(final Image image) throws ConstraintViolationException {
        log.trace("Save image with id = " + image.getId() + " ...");
        Image imageNew = imageDAO.merge(image);
        log.trace("Saved image with id = " + imageNew.getId());
        return imageNew;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long delete(final Long id) throws ConstraintViolationException {
        log.trace("Delete image with id = " + id + " ...");
        Long delId = imageDAO.delete(id);
        log.trace("Delete image with id = " + id);
        return delId;
    }
}
