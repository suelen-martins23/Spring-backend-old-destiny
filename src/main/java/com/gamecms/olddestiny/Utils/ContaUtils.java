package com.gamecms.olddestiny.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ContaUtils {

    public static Map<String, Object> read(String dirConta, String account, String mode) throws IOException {
        Map<String, Object> retorno = new HashMap<String, Object>();

        String dir = dirConta + "/" + (mode != "mob" ? account.charAt(0) + "/" : "") + account;
        Path pathConta = Paths.get(dir);
        if (!Files.exists(pathConta)) {
            throw new IOException("Conta não existe");
        }
        retorno.put("account", account);
        try {
            retorno.put("bytesAccount", Files.readAllBytes(pathConta));
            retorno.put("stringAccount", new String((byte[]) retorno.get("bytesAccount"), "Cp1252"));
            return retorno;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hex2bin(String hex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            sb.append((char) Integer.parseInt(str, 16));
        }
        return sb.toString();
    }

    public static int hex2num(String data) {
        return hexdec(invhex(data));
    }

    public static String invhex(String data) {
        int t = data.length();
        if (t % 2 != 0) {
            data = "0" + data;
            t++;
        }
        String d = "";
        for (int i = 0; i < t; i += 2) {
            d += data.substring((t - i - 2), (t - i));
        }
        return d;
    }

    public static int hexdec(String hexString) {
        int dec = 0;
        hexString = hexString.toLowerCase();
        int len = hexString.length();
        for (int i = 0; i < len; i++) {
            char c = hexString.charAt(i);
            int digit = Character.digit(c, 16);
            dec += digit * Math.pow(16, len - i - 1);
        }
        return dec;
    }
}