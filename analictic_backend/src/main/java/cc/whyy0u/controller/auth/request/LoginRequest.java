package cc.whyy0u.controller.auth.request;

import lombok.Data;

@Data
public class LoginRequest {
    String login;
    String password;
}
