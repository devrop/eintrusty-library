package com.eintrusty.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static String notNull(String s) {
    	if(s !=null) {
    		
    		return s.trim();
    	}
    	return "";
    	
    }
    
    public static String aroundingPersen(String s) {
    	if(s !=null) {
    		
    		return "%"+s.trim()+"%";
    	}
    	return "%%";
    	
    }
    
    public static String buildingBodyTokenUsernameAndRole(String username,String regex1,List<String> roles, String regex2) {
    	try {
    		StringBuffer sb = new StringBuffer();
    		sb.append(username).append(regex1);
    		for (int i = 0; i < roles.size(); i++) {
				if(i == roles.size()-1) {
					sb.append(roles.get(i));
				}else {
					sb.append(roles.get(i)).append(regex2);
				}
			}
    		return sb.toString();
    		
    	}catch(Exception e) {
    		return null;
    	}
    	
    	
    }

    private static List<String> extactRoleFromToken(String role, String regex2){
    	try {
    		List<String> roles = null;
    		if(role.contains(regex2)) {
    			String[] arrayRole = role.split(regex2);
    			roles = Arrays.asList(arrayRole);
        		return roles;
    		}else {
    			roles = Arrays.asList(role);
    			System.out.println("masuk sini");
    			return roles;
    		}
    		
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		return null;
    		
    	}
    	
    	
    }

    public static Map<String,Object> extractUserNameAndRoleFromBodyToken(String bodyToken, String regex1, String regex2){
    	Map<String,Object> datas = new HashMap<>();
    	try {
    		String[] array1 = bodyToken.split(regex1);
    		datas.put("username", array1[0]);
    		List<String> roles = extactRoleFromToken(array1[1], regex2);
    		datas.put("roles", roles);
    		return datas;
    	}catch(Exception e) {
    		return datas;
    	}
    	
    	
    	
    }
    
}
