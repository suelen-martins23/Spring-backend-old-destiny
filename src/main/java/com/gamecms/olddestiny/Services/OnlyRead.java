package com.gamecms.olddestiny.Services;

import com.gamecms.olddestiny.Dto.PlayerRanking;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static String getRankingPlayer(Path path) throws IOException {
                PlayerRanking[] rankings = {};
                List<String> linhas = Files.readAllLines(path);

                linhas.forEach(
                        n->{
                            String[] linhaPlayer = StringUtils.split(n, " ");
                            PlayerRanking player = new PlayerRanking();
                            player.posicao = linhaPlayer[0];
                            player.nome = linhaPlayer[1];
                            player.level = linhaPlayer[2];
                            player.classe = linhaPlayer[3];
                            rankings[ (int) Arrays.stream(rankings).count() ] = player;
                        }
                );

                return new Gson().toJson(rankings);
    }

}
