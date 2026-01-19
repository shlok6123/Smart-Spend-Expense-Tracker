package com.shlok.SmartSpend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "EMail is Required.")
    @Email(message = "Please Enter Valid Email")
    private String email;
}
