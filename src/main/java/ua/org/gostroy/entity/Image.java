package ua.org.gostroy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by panser on 5/9/14.
 */
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;

    @JsonBackReference
//    @JsonManagedReference
//    @NotNull
    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    private String imagePath;
    private byte[] image;
//    @JsonSerialize(using=DateSerializer.class)
    @DateTimeFormat
    private Date createDate;
    @DateTimeFormat
    private Date deleteDate;

    public Image() {
    }

    public Image(User user, String imagePath) {
        this.user = user;
        this.imagePath = imagePath;
    }

    public Image(String imagePath, byte[] image) {
        this.imagePath = imagePath;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", user=" + user.getLogin() +
                ", imagePath='" + imagePath + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
