package com.gamecms.olddestiny.Services;

import com.gamecms.olddestiny.Dto.Conta;
import com.gamecms.olddestiny.Dto.GenericReturn;
import com.gamecms.olddestiny.Dto.PlayerRanking;
import com.gamecms.olddestiny.Model.ContaModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.*;

@Service
public class OnlyRead {
    @Value("${wydserver.dbaccount}")
    String dirAccount;

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

    public String isCorrectPassword(String accountName, String senha) {
        try {
                Path path = Paths.get(dirAccount + getInitialChar(accountName).toUpperCase() + "\\" + accountName);
                if(!Files.exists(path)){
                    throw new FileSystemNotFoundException("Conta não encontrada");
                }

                List<String> linhas = Files.readAllLines(path);
                String linhaDaSenha = linhas.stream().filter(n -> n.contains(senha)).toString();

                if(linhaDaSenha.isEmpty()) {
                    throw new IllegalArgumentException("Senha incorreta");
                }

                Conta conta = new Conta();
                conta.login = accountName;
                conta.senha = senha;
                conta.liberarLogin = true;
                return new Gson().toJson(conta);
        }
        catch (Exception ex){
            return new Gson().toJson( GenericReturn.builder().isSucess(false).descricao("Erro").build());
        }
    }

    public  String getRankingPlayer(Path path) {
        try {
            Optional<List<String>> linhas = Optional.of(Files.readAllLines(path));

            if(linhas.isPresent()){
                return new Gson().toJson(linhas);
            }

            return null;
        }
        catch (Exception ex){
            return new Gson().toJson(GenericReturn.builder().isSucess(false).descricao(ex.getMessage()).build());
        }
    }
    public String getDadosConta(String conta){
        ContaModel model = new ContaModel(dirAccount);
        model.read("admin", "full");
        Map<String, Object> dados = model.accountChar(3);
        return new Gson().toJson(dados);
    }



}
