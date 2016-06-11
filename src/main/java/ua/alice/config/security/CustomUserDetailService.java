package ua.alice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.alice.entity.User;
import ua.alice.repository.UserJpaRepository;

/**
 * Created by Лис on 26.05.2016.
 * 2
 */
@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
        User user = userJpaRepository.findUserByLogin(s);

        if(user == null){
            throw new UsernameNotFoundException(String.format("User '%s' not found", s));
        }

        return new SecurityUser(user);
    }
}
