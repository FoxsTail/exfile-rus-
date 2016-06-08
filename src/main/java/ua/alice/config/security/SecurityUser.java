package ua.alice.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.alice.entity.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Лис on 26.05.2016.
 */
public class SecurityUser extends User implements UserDetails {

    public SecurityUser(User user){
        this.setLogin(user.getLogin());
        this.setPassword(user.getPassword());
        this.setRole(user.getRole());
        this.setEmail(user.getEmail());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(getRole().toString().toUpperCase()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
