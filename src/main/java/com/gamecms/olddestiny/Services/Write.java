package com.gamecms.olddestiny.Services;


import com.gamecms.olddestiny.Dto.Conta;
import com.gamecms.olddestiny.Dto.GenericReturn;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class Write {
    private String secretKey;

    private String importAccountDir = "C:/Users/Eliezer/Desktop/dirTest";



    private GenericReturn consistirConta(String accountName, String recaptchaFromFront){
        GenericReturn retorno = new GenericReturn();
        retorno.status = true;
        retorno.descricao = "Consistido com sucesso.";


        if(Files.exists(Paths.get(importAccountDir + "/" + accountName))){
            retorno.status = false;
            retorno.descricao = "Essa conta já existe.";
            return retorno;
        }

        if(!GoogleRecaptcha.isCaptchaSolved(secretKey, recaptchaFromFront)){
            retorno.status = false;
            retorno.descricao = "Marque corretamente o captcha.";
            return retorno;
        }

        return retorno;
    }


    public GenericReturn createAccount(Conta conta){
        String accountName = conta.login;
        String recaptchaFromFront = conta.recaptchaPublicKey;

        GenericReturn retorno = consistirConta(accountName, recaptchaFromFront);
        if(!retorno.status){
            return retorno;
        }
       try {
           File fileConta = new File(importAccountDir + "/" + accountName);
           Files.write(Paths.get(importAccountDir + "/" + accountName), conta.getTxtImportAccount().getBytes(StandardCharsets.UTF_8));
           retorno.status = true;
           retorno.descricao = "Conta criada com sucesso.";
       }
       catch (Exception ex){
           if(Files.exists(Paths.get(importAccountDir + "/" + accountName))){
               retorno.status = false;
               retorno.descricao = "Essa conta já existe, stack: " + ex.getMessage();
           }else {
               retorno.status = false;
               retorno.descricao = ex.getMessage();
           }
       }

        return retorno;
    }
}
