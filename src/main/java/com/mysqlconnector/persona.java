package com.mysqlconnector;

public class persona {
    private String nome;
    private String cognome;
    public persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getDati(){
        String res=("LT: Nome: "+ this.getNome() + "  Cognome: "+ this.getCognome());
        return res;
    }

}