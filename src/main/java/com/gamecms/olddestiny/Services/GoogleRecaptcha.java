package com.gamecms.olddestiny.Services;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Scanner;


@Service
public class GoogleRecaptcha {


    public static boolean isCaptchaSolved(String secretKey, String recaptchaFieldFromFront){
        try {
            StringBuilder reqDestino = new StringBuilder();
                    reqDestino
                    .append("https://www.google.com/recaptcha/api/siteverify?secret=")
                    .append(secretKey)
                    .append("&response=")
                    .append(recaptchaFieldFromFront);

            /*
            implementar http cliente aqui
            */

            return true;
        }
        catch (Exception ex){
            return false;
        }
    }




}
