package com;
import java.util.HashMap;
import java.util.Map;

public class AutenticadorService implements Autenticador 
{
    private Map<String, Usuario> usuarios;

    public AutenticadorService() { this.usuarios = new HashMap<>(); }

    @Override
    public boolean autenticar(String userName, Token token)
    {
        Usuario usuario = usuarios.get(userName);
        if(usuario == null) return false;
        return usuario.compararToken(token);
    }

    @Override
    public boolean autenticar(String userName, String senha, Token token)
    {
        Usuario usuario = usuarios.get(userName);
        if(usuario == null) return false;
        try {
            if(usuario.compararSenha(senha))
            {
                token.setToken(usuario.getToken());
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean cadastra(String userName, String senha) 
    {
        if(usuarios.containsKey(userName)) return false;
        try {
            Usuario newUsuario = new Usuario(userName, senha);
            usuarios.put(userName, newUsuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return usuarios.toString();
    }
}
