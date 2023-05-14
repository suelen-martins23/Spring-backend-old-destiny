package com.gamecms.olddestiny.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.gamecms.olddestiny.Utils.ContaUtils;
import static com.gamecms.olddestiny.Utils.ContaUtils.hex2num;


/*
    TODO: classe convertida apartir da base dadalto, necessário ainda validar todos os métodos e passos da mesma
    PS: ainda lembrando que a mesma apesar de ser um Model ela é de um banco de dados mais antigo feito na mão e os
    dados vem de um compilado que roda runtime, ou seja ela não é persistida por nada nesse momento, por isso não é
    um objeto - modelo - relacional para ORM até o momento
*/
public class ContaModel {
    private String dirAccount = "./";
    public String account;
    public String stringAccount;

    public byte[] bytesAccount;

    public ContaModel(String dirAccount) {
        if (!new java.io.File(dirAccount).isDirectory()) {
            return;
        }
        this.dirAccount = dirAccount;
    }



    // nao entendi essa parte
    public Map<String, Object> getAccountChar(int number) {
        return readMob(this.stringAccount.substring(416 + (1512 * number), 416 + (1512 * number) + 1512));
    }

    // TODO: REVISAR TEM ALGO ERRADO NA LEITURA
    // ISSO E PARA CADA PERSONAGEM
    protected Map<String, Object> readMob(String data) {
        if (data.length() != 1512) {
            return null;
        }

        //String name = hex2bin(data.substring(0, 24));
        //if (name.equals("")) {
      //      return null;
       // }
        Map<String, Object> attr = new HashMap<>();
        //attr.put("name", name);
        attr.put("race", hex2num(data.substring(32, 34)));
        attr.put("merchant", hex2num(data.substring(34, 36)));
        attr.put("class", hex2num(data.substring(40, 42)));
        attr.put("gold", hex2num(data.substring(48, 56)));
        attr.put("exp", hex2num(data.substring(56, 64)));
        attr.put("cordx", hex2num(data.substring(64, 68)));
        attr.put("cordy", hex2num(data.substring(68, 72)));
        attr.put("level", hex2num(data.substring(72, 76)) + 1);
        attr.put("defence", hex2num(data.substring(76, 80)));
        attr.put("atack", hex2num(data.substring(80, 84)));
        attr.put("str", hex2num(data.substring(104, 108)));
        attr.put("int", hex2num(data.substring(108, 112)));
        attr.put("dex", hex2num(data.substring(112, 116)));
        attr.put("con", hex2num(data.substring(116, 120)));
        int[] skill = {hex2num(data.substring(120, 122)), hex2num(data.substring(122, 124)), hex2num(data.substring(124, 126)), hex2num(data.substring(126, 128))};
        attr.put("skill", skill);
        attr.put("hp_max", hex2num(data.substring(144, 148)));
        attr.put("hp_now", hex2num(data.substring(148, 152)));
        attr.put("mp_max", hex2num(data.substring(152, 156)));
        attr.put("mp_now", hex2num(data.substring(156, 160)));
        attr.put("face", get_item(data.substring(184, 200)));
        attr.put("helmet", get_item(data.substring(200, 216)));
        attr.put("chest", get_item(data.substring(216, 232)));
        attr.put("legs", get_item(data.substring(232, 248)));
        attr.put("gloves", get_item(data.substring(248, 264)));
        attr.put("boots", get_item(data.substring(264, 280)));
        attr.put("hand1", get_item(data.substring(280, 296)));
        attr.put("hand2", get_item(data.substring(296, 312)));
        attr.put("ring", get_item(data.substring(312, 328)));
        attr.put("neck", get_item(data.substring(328, 344)));
        attr.put("jewel", get_item(data.substring(344, 360)));
        attr.put("medal", get_item(data.substring(360, 376)));
        attr.put("guild", get_item(data.substring(376, 392)));
        attr.put("fairy", get_item(data.substring(392, 408)));
        attr.put("mount", get_item(data.substring(408, 424)));
        attr.put("cape", get_item(data.substring(424, 440)));
        attr.put("frag_now", hex2num(data.substring(1454, 1456)));
        attr.put("frag_max",hex2num(data.substring(1458, 1460)));
        attr.put("pt_attr", hex2num(data.substring(1472, 1476)));
        attr.put("pt_espec", hex2num(data.substring(1476, 1480)));
        attr.put("pt_skill", hex2num(data.substring(1480, 1484)));

        attr.put("hp_regen", hex2num(data.substring(1500, 1502)));
        attr.put("mp_regen", hex2num(data.substring(1502, 1504)));

        attr.put("res1", hex2num(data.substring(1504, 1506)));
        attr.put("res2", hex2num(data.substring(1506, 1508)));
        attr.put("res3", hex2num(data.substring(1508, 1510)));
        attr.put("res4", hex2num(data.substring(1510, 1512)));

        Map<String, Object> char_ready = new HashMap<>();
        char_ready.put("attr", attr);

        return char_ready;
    }
    public Map<String, Integer> get_item(String data) {
        String item = data.substring(0, 4);
        if (item.equals("0000")) {
            return null;
        }
        Map<String, Integer> itemMap = new HashMap<>();
        itemMap.put("item", hex2num(item));
        itemMap.put("att1", hex2num(data.substring(4, 6)));
        itemMap.put("val1", hex2num(data.substring(6, 8)));
        itemMap.put("att2", hex2num(data.substring(8, 10)));
        itemMap.put("val2", hex2num(data.substring(10, 12)));
        itemMap.put("att3", hex2num(data.substring(12, 14)));
        itemMap.put("val3", hex2num(data.substring(14, 16)));
        return itemMap;
    }


    // acho que fica mais fácil converter dos bytes direto


    // REVISADO OK
    public void read(String account, String full) {
        Map<String, Object> dados = null;
        try {
            dados = ContaUtils.read(dirAccount, account,full);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.bytesAccount = (byte[]) dados.get("bytesAccount");
        this.stringAccount = (String) dados.get("stringAccount");
    }
}