package cc.whyy0u.controller.auth.response;

import lombok.Data;

@Data
public class RegisterResponse {
    String login;

    public RegisterResponse(String login) {
        this.login = login;
    }
}
