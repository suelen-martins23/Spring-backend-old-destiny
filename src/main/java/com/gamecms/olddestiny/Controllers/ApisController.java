package com.gamecms.olddestiny.Controllers;

import com.gamecms.olddestiny.Dto.Conta;
import com.gamecms.olddestiny.Dto.GenericReturn;
import com.gamecms.olddestiny.Services.GoogleRecaptcha;
import com.gamecms.olddestiny.Services.OnlyRead;
import com.gamecms.olddestiny.Services.OnlyWrite;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/v1/", method = RequestMethod.GET)
public class ApisController {
    @Autowired
    OnlyRead readSE;

    @Autowired
    OnlyWrite writeSE;

    @Autowired
    GoogleRecaptcha recaptchaSE;

    @Autowired
    Gson gson;


    @GetMapping(value = "register/{jsonConta}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> register(@PathVariable("jsonConta") String jsonConta){
        try{
            Conta conta = gson.fromJson(jsonConta, Conta.class);
            GenericReturn contaSalva = writeSE.createAccount(conta);
            return ResponseEntity.ok(gson.toJson(contaSalva));
        }
       catch (Exception ex){
            GenericReturn erro = new GenericReturn();
            erro.setDescricao("Erro");
            erro.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                gson.toJson(erro)
            );
       }

    }





}
