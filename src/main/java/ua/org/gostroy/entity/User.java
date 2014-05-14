package ua.org.gostroy.entity;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by panser on 4/15/14.
 */
@Entity
@Table(name = "users")
public class User {
    private transient final Logger log = LoggerFactory.getLogger(getClass());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Long version;
    @Size(min = 5, max = 20)
    private String login;
    @Email
    private String email;
    private String password;
    private String avatorPath;
    @DateTimeFormat
    private Date createDate;
    @DateTimeFormat
    private Date deleteDate;
//    @Valid
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Image> imagesCollection = new HashSet<Image>();

    public User() {
//        log.trace("create new User ...");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatorPath() {
        return avatorPath;
    }

    public void setAvatorPath(String photo) {
        this.avatorPath = photo;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Image> getImagesCollection() {
        return imagesCollection;
    }

    public void setImagesCollection(Set<Image> imagesCollection) {
        this.imagesCollection = imagesCollection;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
