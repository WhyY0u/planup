package cc.whyy0u.security.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cc.whyy0u.controller.auth.request.LoginRequest;
import cc.whyy0u.controller.auth.request.RegisterRequest;
import cc.whyy0u.controller.auth.response.LoginReponse;
import cc.whyy0u.controller.auth.response.RegisterResponse;
import cc.whyy0u.details.CustomUserDetails;
import cc.whyy0u.entity.user.UserEntity;
import cc.whyy0u.service.user.UserService;

@Service
public class AuthenticationService {

    private final UserService userService;
    

    private final JwtService jwtService;

    
    private final AuthenticationManager authenticationManager;

     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthenticationService(UserService userService, 
                                 JwtService jwtService, 
                                 AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse signUP(RegisterRequest request) {
        UserEntity entity = new UserEntity();
        entity.setLogin(request.getLogin());
        entity.setName(request.getName());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(entity);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        String jwt = jwtService.generateToken(new CustomUserDetails(entity));
        return new RegisterResponse(jwt); 
    }

    public LoginReponse signIn(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        UserEntity entity = userService.findByLogin(request.getLogin()); 
        String jwt = jwtService.generateToken(new CustomUserDetails(entity));
        return new LoginReponse(jwt); 
    }
}