package com.example.beautySalon.domain.dto.binding;

import com.example.beautySalon.vallidation.annotation.UniqueEmail;
import com.example.beautySalon.vallidation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EditUser {
    @Size(min=2,max=20)
    @NotNull
    private String firstName;
    @Size(min=2,max=20)
    @NotNull
    private String lastName;
    @UniqueEmail
    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotBlank(message = "Username cannot be empty!")
    @UniqueUsername
    private String username;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotBlank(message = "User must have password!")
    @NotNull
    private String password;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
