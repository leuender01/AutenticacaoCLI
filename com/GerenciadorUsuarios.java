package com;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorUsuarios 
{
    private Map<String, Usuario> usuarios;
    public GerenciadorUsuarios()
    {
        this.usuarios = new HashMap<>();
    }
    public boolean login(String userName, Token token)
    {
        Usuario usuario = usuarios.get(userName);
        if(usuario == null) return false;
        return usuario.compararToken(token);
    }
    public boolean login(String userName, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        Usuario usuario = usuarios.get(userName);
        if(usuario == null) return false;
        return usuario.compararSenha(senha);
    }
    public boolean cadastra(String userName, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        if(usuarios.containsKey(userName)) return false;
        Usuario newUsuario = new Usuario(userName, senha);
        usuarios.put(userName, newUsuario);
        return true;
    }
    @Override
    public String toString() {
        return usuarios.toString();
    }
}
