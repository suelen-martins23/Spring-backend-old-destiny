package com.gamecms.olddestiny.Services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;

@Service
public class OnlyRead {
    File conta;



    public static String getInitialChar(String accountName){
        return isEtcAccount(accountName) ? "ETC" : accountName.substring(0,1);
    }

    public static boolean isEtcAccount(String accountName){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(accountName);

        return m.find();
    }

}
