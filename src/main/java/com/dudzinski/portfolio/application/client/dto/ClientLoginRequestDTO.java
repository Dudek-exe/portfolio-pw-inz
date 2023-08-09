package com.dudzinski.portfolio.application.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ClientLoginRequestDTO {

    @NotBlank(message = "login can not be only whitespaces")
    @NotNull(message = "login can not be null")
    @Size(min = 4, max = 20)
    private String login;

    @NotBlank(message = "password can not be only whitespaces")
    @NotNull(message = "password can not be null")
    @Size(min = 4, max = 20)
    private String password;

}
