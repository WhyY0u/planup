package cc.whyy0u.service.user.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cc.whyy0u.details.CustomUserDetails;
import cc.whyy0u.entity.user.UserEntity;
import cc.whyy0u.service.user.UserService;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByLogin(username);
        return new CustomUserDetails(userEntity);
    }

    public UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }

} 
