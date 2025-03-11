package cc.whyy0u.controller.auth.request;

import lombok.Data;

@Data
public class RegisterRequest {
    String login;
    String password;
    String name;
}
