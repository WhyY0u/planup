package cc.whyy0u.controller.auth.response;

import lombok.Data;

@Data
public class LoginReponse {
    String token;

    public LoginReponse(String token) {
        this.token = token;
    }
}
