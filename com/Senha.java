package com;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Senha
{
    private String algoritimo = "SHA-256";
    private String valor;
    public Senha(String senhaTextoPlano) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        this.valor = genHash(senhaTextoPlano);
    }
    private String genHash(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest encripyt = MessageDigest.getInstance(this.algoritimo);
        byte senhaHash[] = encripyt.digest(senha.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for(byte b : senhaHash)
        {
            hexString.append(String.format("%02X", b & 0xFF));
        }
        String result = hexString.toString();
        return result;
    }
    
    public String getHash() {return this.valor; }
    public boolean comparHash(String senhaTextoPlano ) throws NoSuchAlgorithmException , UnsupportedEncodingException
    {
        String senhaHash = genHash(senhaTextoPlano);
        return senhaHash.equals(this.valor);
    }
}
