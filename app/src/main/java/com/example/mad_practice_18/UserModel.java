package com.example.mad_practice_18;

public class UserModel {
    public String Login;
    public String Password;
    public Boolean IsAdmin;

    public UserModel(String login, String password, Boolean isAdmin) {
        Login = login;
        Password = password;
        IsAdmin = isAdmin;
    }
}
