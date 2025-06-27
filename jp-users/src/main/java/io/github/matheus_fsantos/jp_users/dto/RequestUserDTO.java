package io.github.matheus_fsantos.jp_users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RequestUserDTO(
    @NotBlank(message = "First name is mandatory")
    @Length(min = 4, max = 120, message = "First name must be between 4 and 120 characters")
    String firstName,

    @NotBlank(message = "Last name is mandatory")
    @Length(min = 4, max = 120, message = "Last name must be between 4 and 120 characters")
    String lastName,

    @Email(message = "E-mail is not valid")
    @NotBlank(message = "E-mail is mandatory")
    @Length(min = 4, max = 200, message = "E-mail must be between 4 and 120 characters")
    String email,

    @NotBlank(message = "Password is mandatory")
    @Length(min = 4, max = 120, message = "Password must be between 4 and 120 characters")
    String password
) { }
