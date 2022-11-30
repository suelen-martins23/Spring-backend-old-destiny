package com.gamecms.olddestiny.Controllers;

import com.gamecms.olddestiny.Dto.Conta;
import com.gamecms.olddestiny.Dto.GenericReturn;
import com.gamecms.olddestiny.Services.GoogleRecaptcha;
import com.gamecms.olddestiny.Services.OnlyRead;
import com.gamecms.olddestiny.Services.Write;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/v1/", method = RequestMethod.GET)
public class ApisController {
    @Autowired
    OnlyRead readSE;

    @Autowired
    Write writeSE;

    @Autowired
    GoogleRecaptcha recaptchaSE;


    @GetMapping(value = "register/{jsonConta}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity register(@PathVariable("jsonConta") String jsonConta){
        Gson gson = new Gson();
        Conta conta = gson.fromJson(jsonConta, Conta.class);
        GenericReturn contaSalva = writeSE.createAccount(conta);

        return ResponseEntity.ok(gson.toJson(contaSalva));


    }



}
