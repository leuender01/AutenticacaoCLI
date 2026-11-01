package com;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class App{
    private static GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
    private static Token token = new Token();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        boolean rodando = true;
        while (rodando)
        {
            menu();
            System.out.println(token);
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        scanner.nextLine();
                        cadastro();
                        break;
                    case 2:
                        scanner.nextLine();
                        login();
                        break;
                    case 3:
                        rodando = false;
                        break;
                    default:
                        System.out.println("invalido");
                        break;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
            
        }
        scanner.close();
    }

    public static void menu()
    {
        System.out.println(
                "======MENu=======\n"
                + "(1) Cadastro: \n"
                + "(2) Login:    \n"
                + "(3) Encerrar: \n"
                );
    }

    public static  void login() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        System.out.println("Digite o nome do Usuario: ");
        String userName = scanner.nextLine();
        if(!gerenciadorUsuarios.login(userName, token))
        {
            System.out.println("Digite a senha: ");
            String senha = scanner.nextLine();
            if(!gerenciadorUsuarios.login(userName,senha, token))
            {
                System.out.println("Usuario ou senha invalidos!");
                return;
            }
        }
        System.out.println("Login Realizado com Sucesso!");
    }

    public static  void cadastro() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        System.out.println("Digite um nome para usuario: ");
        String userName = scanner.nextLine();
        System.out.println("Digite uma senha para usuario: ");
        String senha = scanner.nextLine();
        if (!gerenciadorUsuarios.cadastra(userName, senha))
        {
            System.out.println("Usuario ja existe!");
            return;
        }
        System.out.println("Usuario criado com sucesso!");

    }
}

