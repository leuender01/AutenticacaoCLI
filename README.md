# Autenticacao

Mini sistema de autenticação em Java puro (sem frameworks), desenvolvido como parte do **Desafio 4** do roteiro de estudos de Orientação a Objetos para backend.

## Desafio 4

> Construa `Usuario`, `Senha` (com hashing simples via `MessageDigest`) e um `AutenticadorService` que verifica credenciais e gera um token simples (UUID) com tempo de expiração.
> **Conceitos:** abstração, composição, tratamento de tempo/data (`java.time`).

## Status do desafio

| Requisito | Implementado | Arquivo |
|---|---|---|
| `Usuario` | Sim | `com/Usuario.java` |
| `Senha` com hash via `MessageDigest` | Sim | `com/Senha.java` |
| `AutenticadorService` | Sim | `com/AutenticadorService.java` |
| Interface de abstração | Sim | `com/Autenticador.java` |
| Token UUID com expiração (`java.time`) | Sim | `com/Token.java` |

## Estrutura do projeto

```
com/
  App.java              # Menu de linha de comando (cadastro / login / encerrar)
  Usuario.java          # Entidade: nome de usuario + Senha + Token (composicao)
  Senha.java            # Objeto de valor: hash SHA-256 e conferencia de senha
  Token.java            # Token de sessao: UUID aleatorio com expiracao de 2 minutos
  Autenticador.java     # Interface: contrato de autenticacao (abstracao)
  AutenticadorService.java  # Implementacao em memoria do Autenticador
```

### Classes principais

- **`Senha`** — encapsula o hash SHA-256 de uma senha em texto plano. Responsável por gerar o hash (`MessageDigest`) e conferir se uma senha digitada confere com o hash armazenado.
- **`Usuario`** — compõe uma `Senha` e um `Token`. Não armazena a senha em texto plano, apenas o hash delegado ao objeto `Senha`.
- **`Token`** — imutável na prática: gera um `UUID` aleatório e define expiração de 2 minutos usando `java.time.LocalDateTime`.
- **`Autenticador`** — interface que define o contrato (`cadastra`, `autenticar`). Permite trocar a implementação (memória, banco, LDAP) sem alterar quem consome.
- **`AutenticadorService`** — implementa `Autenticador` usando um `HashMap` em memória. Cadastra usuários, verifica credenciais e associa tokens de sessão.
- **`App`** — interface de linha de comando com menu interativo.

## Como compilar e executar

```bash
# Na raiz do projeto
javac com/*.java
java com.App
```

### Menu

```
======MENu=======
(1) Cadastro:
(2) Login:
(3) Encerrar:
```

- **Cadastro:** cria um novo usuário com senha hasheada.
- **Login:** verifica usuário e senha e, se válidos, exibe mensagem de sucesso com token de 2 minutos de expiração.
- **Encerrar:** sai do programa.

## Decisões de design

- **Composição:** `Usuario` possui uma `Senha` e um `Token`, em vez de herdar ou espalhar responsabilidades.
- **Abstração:** a interface `Autenticador` separa o contrato da implementação, permitindo futuras substituições (por exemplo, persistência em banco de dados) sem modificar o `App`.
- **Sem senha em texto plano:** a senha nunca é armazenada direto; apenas o hash gerado por `MessageDigest` (SHA-256).
- **Tempo de expiração:** `java.time.LocalDateTime` controla a validade do token (2 minutos).

## Observacoes

- O `Token` na versao atual ainda possui metodos mutadores (`setToken`, `genToken`) e um efeito colateral em `equals()` (regenera o token se expirado). Isso funciona para o desafio, mas pode ser refinado em versoes futuras.
- O fluxo de login no `App` tenta autenticacao com um token vazio antes de solicitar a senha; isso e inofensivo, mas confuso. Pode ser simplificado.

---
Projeto de estudos — Orientacao a Objetos em Java para Backend