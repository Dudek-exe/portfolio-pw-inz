package com.dudzinski.portfolio.domain.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewClientDTO {

    @NotNull(message = "username cannot be null")
    @Size(min = 6, max = 20, message = "Username number should be from 6 to 20 characters long")
    private String username;

    @NotNull(message = "password cannot be null")
    @Size(min = 6, max = 20, message = "Password number should be at least 6 characters long")
    private String password;


    @NotNull(message = "name cannot be null")
    private String externalId;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "surname cannot be null")
    private String surname;

    @NotNull(message = "phoneNumber cannot be null")
    @Size(min = 3, max = 20, message = "Phone number should be from 3 to 20 characters long")
    private String phoneNumber;

    @NotNull(message = "email cannot be null")
    @Email
    private String email;
}
