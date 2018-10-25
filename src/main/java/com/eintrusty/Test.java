package com.eintrusty;

import com.eintrusty.rest.DataRest;
import com.eintrusty.utility.StringUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String [] args){
        List<String> listString = new ArrayList();
        listString.add("OK");
        listString.add("Baik");
        listString.add("Siap");
        TypeToken type = new TypeToken<List<String>>() {};
        String listDatas = StringUtil.listToString(listString,type);
        String key = "awsedrftgyhdeR56";
        String encryptedDatas = StringUtil.encrypt(listDatas,key);
        System.out.println(encryptedDatas);
        DataRest restData = new DataRest();
        restData.setMessage("OK");
        restData.setJsonInString(encryptedDatas);

        String decrypted = StringUtil.decrypt(restData.getJsonInString(),key);
        List<String> listUpdate = StringUtil.stringToList(decrypted,type);
        for (String s: listUpdate
             ) {
            System.out.println("String s " + s );

        }





    }
}
