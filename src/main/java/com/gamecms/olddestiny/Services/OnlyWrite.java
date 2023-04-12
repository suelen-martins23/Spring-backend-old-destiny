package com.gamecms.olddestiny.Services;


import com.gamecms.olddestiny.Dto.Conta;
import com.gamecms.olddestiny.Dto.GenericReturn;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class OnlyWrite {
    private String secretKey;

    private String importAccountDir = "C:/Users/Eliezer/Desktop/dirTest";



    private GenericReturn consistirConta(String accountName, String recaptchaFromFront){
        GenericReturn retorno = GenericReturn.builder().isSucess(true).descricao("Consistido com sucesso.").build();



        if(Files.exists(Paths.get(importAccountDir + "/" + accountName))){
            retorno = GenericReturn.builder()
                    .isSucess(false)
                    .descricao("Essa conta não existe.").build();

            return retorno;
        }

        if(!GoogleRecaptcha.isCaptchaSolved(secretKey, recaptchaFromFront)){
            retorno = GenericReturn.builder()
                    .isSucess(false)
                    .descricao("Marque corretamente o captcha.").build();
            return retorno;
        }

        return retorno;
    }


    public GenericReturn createAccount(Conta conta){
        String accountName = conta.login;
        String recaptchaFromFront = conta.recaptchaPublicKey;

        GenericReturn retorno = consistirConta(accountName, recaptchaFromFront);
        if(!retorno.isSucess()){
            return retorno;
        }
       try {
           File fileConta = new File(importAccountDir + "/" + accountName);
           Files.write(Paths.get(importAccountDir + "/" + accountName), conta.getTxtImportAccount().getBytes(StandardCharsets.UTF_8));
           retorno.isSucess = true;
           retorno.descricao = "Conta criada com sucesso.";
       }
       catch (Exception ex){
           if(Files.exists(Paths.get(importAccountDir + "/" + accountName))){
               retorno.isSucess = false;
               retorno.descricao = "Essa conta já existe, stack: " + ex.getMessage();
           }else {
               retorno.isSucess = false;
               retorno.descricao = ex.getMessage();
           }
       }

        return retorno;
    }
}
