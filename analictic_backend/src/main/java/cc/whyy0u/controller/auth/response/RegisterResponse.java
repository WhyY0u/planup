package cc.whyy0u.controller.auth.response;

import lombok.Data;

@Data
public class RegisterResponse {
    String token;

    public RegisterResponse(String token) {
        this.token = token;
    }
}
