package com.example.foodstore.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static String sha256 (String mensaje){
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte [] digest = sha.digest(mensaje.getBytes());

            StringBuilder hexString = new StringBuilder();

            for(byte b : digest){
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
