package com.gamecms.olddestiny.Services;

import com.gamecms.olddestiny.Dto.Conta;
import com.gamecms.olddestiny.Dto.GenericReturn;
import com.gamecms.olddestiny.Dto.PlayerRanking;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

@Service
public class OnlyRead {

    public String getInitialChar(String accountName){
        return isEtcAccount(accountName) ? "ETC" : accountName.substring(0,1);
    }

    public boolean isEtcAccount(String accountName){
        char primeiraLetra = accountName.charAt(0);
        String regex = "[^a-zA-Z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Character.toString(primeiraLetra));

        if (matcher.find() || Character.isDigit(primeiraLetra)) {
            return true;
        }
        return false;
    }

    private String isCorrectPassword(String accountName, String senha) {
        try {
            Path path = Paths.get("root" + "/DBSRV/run/Account/" + getInitialChar(accountName) + accountName);
            List<String> linhas = Files.readAllLines(path);

            String linhaDaSenha = linhas.stream().filter(n -> n.contains(senha)).toString();
            if(!linhaDaSenha.isEmpty()){
                Conta conta = new Conta();
                conta.login = accountName;
                conta.senha = senha;
                conta.liberarLogin = true;
                return new Gson().toJson(conta);

            }

            throw new Exception("Senha incorreta");
        }
        catch (Exception ex){
            return new Gson().toJson( GenericReturn.builder().isSucess(false).descricao("Erro").build());
        }
    }

    private  String getRankingPlayer(Path path) {
        try {
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
                        rankings[ (int) Arrays.stream(rankings).count() + 1] = player;
                    }
            );
            return new Gson().toJson(rankings);
        }
        catch (Exception ex){
            return new Gson().toJson(ex);
        }
    }


}
