package ua.org.gostroy.security.openid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Custom UserDetailsService which accepts any OpenID user, "registering" new users in a map so they can be welcomed
 * back to the site on subsequent logins.
 *
 */

public class CustomUserDetailsService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    private final Map<String, CustomUserDetails> registeredUsers = new HashMap<String, CustomUserDetails>();
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = AuthorityUtils.createAuthorityList("ROLE_USER");

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        String id = token.getIdentityUrl();

        CustomUserDetails user = registeredUsers.get(id);

        if (user != null) {
            return user;
        }

        String email = null;
        String firstName = null;
        String lastName = null;
        String fullName = null;

        List<OpenIDAttribute> attributes = token.getAttributes();

        for (OpenIDAttribute attribute : attributes) {
            if (attribute.getName().equals("email")) {
                email = attribute.getValues().get(0);
            }

            if (attribute.getName().equals("firstname")) {
                firstName = attribute.getValues().get(0);
            }

            if (attribute.getName().equals("lastname")) {
                lastName = attribute.getValues().get(0);
            }

            if (attribute.getName().equals("fullname")) {
                fullName = attribute.getValues().get(0);
            }
        }

        if (fullName == null) {
            StringBuilder fullNameBldr = new StringBuilder();

            if (firstName != null) {
                fullNameBldr.append(firstName);
            }

            if (lastName != null) {
                fullNameBldr.append(" ").append(lastName);
            }
            fullName = fullNameBldr.toString();
        }

        user = new CustomUserDetails(id, DEFAULT_AUTHORITIES);
        user.setEmail(email);
        user.setName(fullName);

        registeredUsers.put(id, user);

        user = new CustomUserDetails(id, DEFAULT_AUTHORITIES);
        user.setEmail(email);
        user.setName(fullName);
        user.setNewUser(true);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserDetails user = registeredUsers.get(id);

        if (user == null) {
            throw new UsernameNotFoundException(id);
        }

        return user;
    }
}
