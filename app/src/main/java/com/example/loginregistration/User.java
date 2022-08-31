package com.example.loginregistration;

public class User {
    public String fullnameTxt, phoneTxt, emailTxt, passwordTxt;
    public User(){

    }
    public User(String fullnameTxt, String phoneTxt, String emailTxt, String passwordTxt){
        this.fullnameTxt = fullnameTxt;
        this.phoneTxt = phoneTxt;
        this.emailTxt = emailTxt;
        this.passwordTxt = passwordTxt;
    }
}
