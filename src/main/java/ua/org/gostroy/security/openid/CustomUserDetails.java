package ua.org.gostroy.security.openid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by panser on 5/7/14.
 */
public class CustomUserDetails extends User {
    private String email;
    private String name;
    private boolean newUser;

    public CustomUserDetails(String username, Collection<GrantedAuthority> authorities) {
        super(username, "unused", authorities);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }
}
