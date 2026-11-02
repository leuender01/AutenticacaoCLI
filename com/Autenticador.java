package com;

public interface Autenticador
{
    public boolean cadastra(String userName, String senha);
    public boolean autenticar(String userName, Token token);
    public boolean autenticar(String userName, String senha, Token token);
}
