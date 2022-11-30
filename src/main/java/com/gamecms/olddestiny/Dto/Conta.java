package com.gamecms.olddestiny.Dto;

public class Conta {
    public String login;
    public String senha;
    public String email;
    public String numerica;
    public  String recaptchaPublicKey;

    public boolean liberarLogin;

    public  String getTxtImportAccount(){
        String contaTxt ="";
        contaTxt = contaTxt + this.login + "\n";
        contaTxt = contaTxt + this.senha + "\n";
        contaTxt = contaTxt + this.email + "\n";
        contaTxt = contaTxt + this.numerica + "\n";

        return contaTxt;
    }
}
