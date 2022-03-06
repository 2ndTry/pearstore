package com.amiroshnikov.PearStore.dto.user;

public class SighInDto {

    private String email;
    private String password;

    public SighInDto() {
    }

    public SighInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
