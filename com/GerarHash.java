package com;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GerarHash {

    public static String genHash(String senha, String algoritimo) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest encripyt = MessageDigest.getInstance(algoritimo);
        byte senhaHash[] = encripyt.digest(senha.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for(byte b : senhaHash)
        {
            hexString.append(String.format("%02X", b & 0xFF));
        }
        String result = hexString.toString();
        
        return result;
    }
    public static String genHash(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        return genHash(senha, "SHA-256");
    }
    
}
