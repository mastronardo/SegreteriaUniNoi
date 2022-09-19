package com.mysqlconnector;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
public class funzioniStudente {

    public static void nuovaRichiesta(int mat, int codice, PrintWriter pw, BufferedReader br) throws IOException{
        String richiesta=null;
        
        pw.println("IN: Digita qui la tua richiesta o Esci per tornare indietro");

                                                                        richiesta = br.readLine();
                                                                        if (richiesta.equalsIgnoreCase("Esci"))
                                                                        richiesta="Esci";
                                                                        switch(richiesta){
                                                                        case "Esci": break;
                                                                        default:{
                                                                        try {
                                                                                LocalDate data = LocalDate.now();
                                                                                Statement stt = dbconnection.connettiti();
                                                                                stt.executeUpdate("INSERT INTO Richieste (richiesta, matricola, codice, giorno) " + "VALUES ('" + richiesta + "', '" + mat + "', '"+codice+" ', '" + data + "')");
                                                                                pw.println("NO: Richiesta effettuata correttamente");
                                                                                pw.flush();
                                                                        
                                                                        } catch (Exception ee) {
                                                                                ee.printStackTrace();
                                                                        
                                                                               pw.println("C'è stato un problema di connessione, riavviare");
                                                                        
                                                                        }
                                                                    }
                                                                    }
                                                            
    }
    public static void visualizzaRisposte(int mat, int codice, PrintWriter pw, BufferedReader br) throws IOException{
        try {
            Statement stt = dbconnection.connettiti();
            String ottieniRichieste = "SELECT id, richiesta, giorno, stato FROM Richieste WHERE matricola='" + mat + "'and codice='"+ codice +"'";
            ResultSet rss = stt.executeQuery(ottieniRichieste);
            ResultSetMetaData rssmd= rss.getMetaData();
            int cnumber= rssmd.getColumnCount();
            if(!rss.isBeforeFirst()){
            pw.println("NO: Non hai effettuato alcuna richiesta riguardo questa sezione");
        }
            else{
                pw.println("NO: Ecco le tue richieste:  ");
        
            while (rss.next()) {
                    for (int i=1; i<=cnumber; i++){
                            String columnValue= rss.getString(i);
                            pw.println("SQL: "+rssmd.getColumnName(i).substring(0, 1).toUpperCase() + rssmd.getColumnName(i).substring(1)+ "  " + columnValue);

                    }
            }
            pw.println("IN: Digita Risposta per vedere le risposte, Rimuovi per eliminare una richiesta effettuata o Esci per tornare indietro");
            String scelta=br.readLine();
            scelta=stringConverter.convert(scelta);
            switch (scelta){
                    case "Risposta":{
            pw.println("IN: Digita l'id della richiesta per verificare se hai ricevuto una risposta");
            String ids=br.readLine();
            int id=Integer.parseInt(ids);
            Statement s = dbconnection.connettiti();
            String ottieniRisposte = "SELECT cognome, nome, risposta, giorno FROM Risposte JOIN Membri ON Risposte.utente=Membri.codice WHERE Risposte.id='" + id + "'and Risposte.codice='"+codice+"'";
            ResultSet rsr = s.executeQuery(ottieniRisposte);
            ResultSetMetaData rsrmd= rsr.getMetaData();
            cnumber=rsrmd.getColumnCount();
            if (!rsr.isBeforeFirst())
                    pw.println("NO: Non hai ricevuto nessuna risposta per la tua richiesta");
            
            else{
                    while(rsr.next()){
                            for (int i=1; i<cnumber;i++){
                            String columnValue= rsr.getString(i);
                            pw.println("SQL: "+rsrmd.getColumnName(i).substring(0, 1).toUpperCase() + rsrmd.getColumnName(i).substring(1)+ "  " + columnValue);
                            }
                    }
            }
    } break;
            case "Rimuovi":{
            pw.println("IN: Digita l'id della richiesta per rimuoverla o 0 per tornare indietro");
            String ids=br.readLine();
            int id= Integer.parseInt(ids);
            String x="l";
            if(id==0){
                x="x";
            }
            switch(x){
            case "x": break;
            default:{
            Statement s = dbconnection.connettiti();
            String eliminaRichiesta = "DELETE FROM Richieste WHERE id='" + id + "' and codice='"+codice+"'";
            s.executeUpdate(eliminaRichiesta);
            pw.println("NO: Cancellazione effettuata");

            }break;
        }break;
    }
            case "Esci": break;
    }

    }
} catch (Exception ex) {
            ex.printStackTrace();
            pw.println("NO: C'è stato un problema di connessione, riavviare");
    }

    }
    public static void datiStudente(int mat, PrintWriter pw, BufferedReader br){
                                                                                
        try {
                Statement stt = dbconnection.connettiti();
                String ottieniDati = "SELECT nome, cognome, aa FROM Studenti WHERE matricola='" + mat + "'";
                ResultSet rss = stt.executeQuery(ottieniDati);
                String name=null;
                String surname=null;
                String aa=null;
                while (rss.next()) {
                        name=rss.getString(1);
                        surname=rss.getString(2);
                        aa=rss.getString(3);
                        }
                studente s=new studente(name, surname, aa, mat);
                pw.println("NO: Ecco i tuoi dati: ");
                pw.println(s.getDati());
                


        } catch (Exception ex) {
                ex.printStackTrace();
                pw.println("NO: C'è stato un problema di connessione, riavviare");
        }

    }
}
