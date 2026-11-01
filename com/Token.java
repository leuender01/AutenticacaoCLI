package com;
import java.time.LocalDateTime;
import java.util.UUID;

public class Token
{
    private String token;
    private final int EXPIRACAOTOKEN = 2;
    private LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(EXPIRACAOTOKEN);

    public String getToken() {return this.token; }
    public void genToken() 
    {
        this.token = UUID.randomUUID().toString();
        this.localDateTime = LocalDateTime.now().plusMinutes(EXPIRACAOTOKEN);
    }
    public void setToken(String token) { this.token = token; }
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        Token token = (Token) obj;
        if(
            this.localDateTime.isBefore(LocalDateTime.now()) &&
            this.token.equals(token.getToken())
        ){
            this.genToken();
            return false;
        }

        return this.token.equals(token.getToken());
    }
    @Override
    public String toString() {
//        return "UUID: " + getToken() + " Data de Expiracao: " + this.localDateTime.toString(); 
        return this.token;
    }
}
