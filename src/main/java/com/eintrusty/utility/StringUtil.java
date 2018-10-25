package com.eintrusty.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author Ropandi, A.
 * 10/16/2018
 */


public class StringUtil {
    private static final Base64.Encoder encoder = Base64.getUrlEncoder();;
    private static final Base64.Decoder decoder = Base64.getUrlDecoder();

    /**
     *
     * @param list
     * @param typeToken
     * @param <E>
     * @return
     * example TypeToken token = new TypeToken<T>(){};
     */
    public static <E>  String listToString(List<E> list, TypeToken typeToken )  {
        //Class classTemp = Class.forName(className);
        //Type baseType = new TypeToken<List<E>>() {}.getType();
        Type baseType = typeToken.getType();
        Gson gson = new Gson();
        String result = gson.toJson(list, baseType);
        return result;
    }

    public static <T> List<T> stringToList(String source, TypeToken typeToken){
        Type baseType = typeToken.getType();
        Gson gson = new Gson();
        List<T> list =  gson.fromJson(source, baseType);
        return  list;
    }

    public static <R> R stringToObject(String source, TypeToken typeToken){
        Type baseType = typeToken.getType();
        Gson gson = new Gson();
        R object = gson.fromJson(source, baseType);
        return object;
    }

    public static <R> String objcetToString(R object, TypeToken typeToken){
        Type baseType = typeToken.getType();
        Gson gson = new Gson();
        String result = gson.toJson(object, baseType);
        return result;
    }

    /**
     *
     * @param plainText
     * @param key
     * @return
     * example key String key ="qwvbhKhtYhnfrtJk"; 16 digits
     */
    public static String encrypt(String plainText, String key) {
        try {
            SecretKey secretKey =  new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            // Get byte array which has to be encrypted.
            byte[] plainTextByte = plainText.getBytes();
            //toByteArray(plainText);

            // Encrypt the bytes using the secret key
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedByte = cipher.doFinal(plainTextByte);

            // Use Base64 encoder to encode the byte array
            // into Base 64 representation. Requires Java 8
            return encoder.encodeToString(encryptedByte);
            //System.out.println("Ok");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //logger.error("Failed to encrypt", e);
            return null;
        }


    }

    public static String decrypt(String encrypted, String key) {
        try {
            // Decode Base 64 String into bytes array.
            SecretKey secretKey =  new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            byte[] encryptedByte = decoder.decode(encrypted);

            //Do the decryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedByte = cipher.doFinal(encryptedByte);

            // Get hexadecimal string from the byte array.
            return new String(decryptedByte);
            //return toHexString(decryptedByte);

        } catch (Exception e) {
            //logger.error("Failed to decrypt {}", encrypted, e);
            return null;
        }

    }



}
