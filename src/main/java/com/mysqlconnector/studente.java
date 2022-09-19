package com.mysqlconnector;

public class studente extends persona{
    private int matricola;
    private String password;
    private String aa;

    public studente(String nome, String cognome, int matricola, String password) {
        super(nome, cognome);
        this.matricola=matricola;
        this.password=password;
    }
    public studente(String nome, String cognome, String aa, int matricola) {
        super(nome, cognome);
        this.matricola=matricola;
        this.aa=aa;
    }
    public void setMatricola(int matricola){
        this.matricola=matricola;
    }
    public int getMatricola(){
        return this.matricola;
    }
    public String getPassword(){
        return this.getPassword();
    }
    public String getAa() {
        return aa;
    }
    
    @Override
    public String getDati(){
        String res=(super.getDati()+"   Matricola: "+ this.getMatricola() + "  Anno d'iscrizione: "+ this.getAa());
        return res;
    }

    

}
