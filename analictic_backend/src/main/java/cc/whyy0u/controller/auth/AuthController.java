package cc.whyy0u.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.whyy0u.controller.auth.request.LoginRequest;
import cc.whyy0u.controller.auth.request.RegisterRequest;
import cc.whyy0u.entity.user.UserEntity;
import cc.whyy0u.security.auth.jwt.AuthenticationService;
import cc.whyy0u.service.user.UserService;

@Controller
@RequestMapping("/api/auth")
class AuthController {

  @Autowired
  UserService userService;

  @Autowired
  AuthenticationService authenticationService;

  @Autowired
  PasswordEncoder encoder;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
      UserEntity entity = userService.findByLogin(request.getLogin());
      if(entity == null || !encoder.matches(request.getPassword(), entity.getPassword())) return ResponseEntity.badRequest().body("Неверный логин или пароль");
      return ResponseEntity.ok(authenticationService.signIn(request));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
       UserEntity entity = userService.findByLogin(request.getLogin());
       if(entity != null) return ResponseEntity.badRequest().body("Логин уже занят");
       return ResponseEntity.ok(authenticationService.signUP(request));
  }

}
