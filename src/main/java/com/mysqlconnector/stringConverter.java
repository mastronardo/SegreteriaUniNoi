package com.mysqlconnector;
public class stringConverter {
 
    public static String convert(String choice){
        String res=null;
        if (choice.equalsIgnoreCase("Attribuzione")){
        res="Attribuzione";
    }
    else if (choice.equalsIgnoreCase("Materie")){
            res="Materie";
    }
    else if (choice.equalsIgnoreCase("Rosetta")){
            res="Rosetta";
    }
    else if (choice.equalsIgnoreCase("Altro")){
            res="Altro";
    }
    else if (choice.equalsIgnoreCase("Lista")){
            res="Lista";
    }
    else if (choice.equalsIgnoreCase("Esci")){
            res="Esci";
    }
    else if (choice.equalsIgnoreCase("Altro")){
        res="Altro";
    }
    if (choice.equalsIgnoreCase("Rispondi"))
        res="Rispondi";
        if (choice.equalsIgnoreCase("Rifiuta"))
        res="Rifiuta";
        if(choice.equalsIgnoreCase("Integrazione"))
        res="Integrazione";
        if(choice.equalsIgnoreCase("Visualizza"))
        res="Visualizza";
        if(choice.equalsIgnoreCase("Esci"))
        res="Esci";
        if(choice.equalsIgnoreCase("Nuovo"))
        res="Nuovo";
        if(choice.equalsIgnoreCase("Visualizza"))
        res="Visualizza";
        if (choice.equalsIgnoreCase("Risposta"))
        res="Risposta";
        if(choice.equalsIgnoreCase("Rimuovi"))
        res="Rimuovi";
        if(choice.equalsIgnoreCase("Dati"))
        res="Dati";
    return res;
    }
    
}
