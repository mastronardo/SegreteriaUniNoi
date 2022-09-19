package com.mysqlconnector;

public class membro extends persona{
    private int codice;
    private String mansione;
    public membro (String nome, String cognome, int codice, String mansione){
        super(nome, cognome);
        this.codice=codice;
        this.mansione=mansione;
    }
    public membro (String nome, String cognome, int codice){
        super(nome, cognome);
        this.codice=codice;
    }
    public int getCodice() {
        return codice;
    }
    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getMansione() {
        return mansione;
    }
    public void setMansione(String mansione) {
        this.mansione = mansione;
    }

    @Override
    public String getDati(){
        String res=(super.getDati()+"   Codice: "+ this.codice);
        return res;
    }

}
