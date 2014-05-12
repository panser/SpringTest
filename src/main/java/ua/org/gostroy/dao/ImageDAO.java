package ua.org.gostroy.dao;

import ua.org.gostroy.entity.Image;
import ua.org.gostroy.entity.User;

import java.util.List;

/**
 * Created by panser on 5/10/14.
 */
public interface ImageDAO {
    public List<Image> findByUser(User user);
    Image find(Long id);
    Image merge(Image image);
    Long delete(Long id);
}
