package ua.org.gostroy.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.entity.Image;
import ua.org.gostroy.entity.User;

import java.util.List;

/**
 * Created by panser on 5/10/14.
 */
@Repository
public class ImageImplHibernate implements ImageDAO {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Image merge(Image image) {
        log.trace("Save image with id = " + image.getId() + " ...");
        Image imageNew = (Image)sessionFactory.getCurrentSession().merge(image);
        log.trace("Saved image with id = " + imageNew.getId());
        return imageNew;
    }

    @Override
    public List<Image> findByUser(User user) {
        log.trace("findByUser: with id = " + user.getId() + " ...");
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Image.class);
        List<Image> images = criteria.add(Restrictions.like("user", user)).list();
        log.trace("findByUser: with id = " + user.getId() + ", find " + images.size() + " images.");
        return images;
    }

    @Override
    public Image find(Long id) {
        log.trace("Find image with id = " + id + " ...");
        Image image = (Image) sessionFactory.getCurrentSession().get(Image.class, id);
        if (image != null) {
            log.trace("Find image with id: " + image.getId());
        }
        return image;
    }

    @Override
    public Long delete(Long id) {
        log.trace("Delete image with id = " + id + " ...");
        Image delImage = find(id);
        if (delImage != null) {
            sessionFactory.getCurrentSession().delete(delImage);
            log.info("Delete image with id = " + delImage.getId());
            return delImage.getId();
        }
        log.trace("Delete image: no image find.");
        return 0L;
    }
}
