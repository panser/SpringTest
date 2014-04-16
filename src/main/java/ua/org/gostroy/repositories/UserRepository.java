package ua.org.gostroy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.entity.User;

/**
 * Created by panser on 4/16/14.
 */

//@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
}
