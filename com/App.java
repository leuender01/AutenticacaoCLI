package com;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App{
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException 
    {
        String senha = "1234dkfdlkk";
        MessageDigest algoritimoMD5 = MessageDigest.getInstance("MD5");
        MessageDigest algoritimoSHA256 = MessageDigest.getInstance("SHA-256");
        byte messageDigestSha256[] = algoritimoSHA256.digest(senha.getBytes("UTF-8"));
        byte messageDigestMD5[] = algoritimoMD5.digest(senha.getBytes("UTF-8"));
        System.out.println(messageDigestMD5);
        System.out.println(messageDigestSha256);
        StringBuilder hexString = new StringBuilder();
        for(byte b : messageDigestSha256)
        {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhaHex = hexString.toString();
        System.out.println(senhaHex);
    }
}

