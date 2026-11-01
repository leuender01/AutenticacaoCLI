package com;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Usuario
{
    private String userName;
    private String senhaHash;
    private Token token;

    public Usuario(String userName, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        this.senhaHash = GerarHash.genHash(senha);
        this.userName = userName;
        this.token = new Token();
        this.token.genToken();
    }
     
    public boolean compararToken(Token token) { return this.token.equals(token); }
    public String getToken() {return this.token.toString(); }
    public boolean compararSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException
    { return this.senhaHash.equals(GerarHash.genHash(senha)); }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        Usuario usuario = (Usuario) obj;
        return usuario.userName.equals(this.userName) &&
               usuario.senhaHash.equals(this.senhaHash);
    }
    @Override
    public String toString() {
        return userName;
    }
}
