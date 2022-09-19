package com.mysqlconnector;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
public class funzioniMembro {
    public static void rriFunctions(int id, int mat, int codice, String valutazione, PrintWriter pw, BufferedReader br) throws IOException{
        pw.println("IN: Digiti la risposta:");
        String risposta=br.readLine();
        LocalDate data = LocalDate.now();
        try{
        Statement s = dbconnection.connettiti();
        String insertRisposta = "INSERT INTO Risposte (id, risposta, codice, utente, giorno, stato) VALUES ('"+ id + "','"+ risposta + "', '"+codice+"', '" + mat +"', '" + data+ "', '"+valutazione+"')" ;
        s.executeUpdate(insertRisposta);
        Statement s2= dbconnection.connettiti();
        String updateRichieste= "UPDATE Richieste SET stato='"+valutazione+"' WHERE id= "+ id +"";
        s2.executeUpdate(updateRichieste);
    }  catch (Exception e) {
        e.printStackTrace();
        pw.println("C'è stato un problema di connessione, riavviare");
    }
    finally{pw.println("Risposta inviata correttamente");}
    }
    public static void memberOperations(int mat, int codice, PrintWriter pw, BufferedReader br) throws IOException{
        pw.println("IN: Digiti Visualizza per prendere in carico una delle pratiche o Esci per tornare indietro");
        String richiesta=br.readLine();
        richiesta=stringConverter.convert(richiesta);
        switch(richiesta){
            case "Visualizza":{
            pw.println("IN: Digiti l'id della richiesta per prenderla in carico o 0 per tornare indietro");
            String ids=br.readLine();
            int id=Integer.parseInt(ids);
            pw.println("IN: Digiti Rispondi, Rifiuta, Integrazione o Esci per tornare indietro");
            String funzione=br.readLine();
            funzione=stringConverter.convert(funzione);
            if(funzione.equalsIgnoreCase("Esci"))
            funzione="Esci";
                if(id==0){
                    funzione="Esci";
                }
            switch(funzione){
                case "Rispondi":{
               rriFunctions(id, mat, codice, "Accettata", pw, br);
            break;} 
        case "Rifiuta":{
            rriFunctions(id, mat, codice, "Rifiutata", pw, br);
        break;} 
    case "Integrazione":{
     rriFunctions(id, mat, codice, "Integrazione", pw, br);
    break;} 
case "Esci": break;
               
            }// fine funzione
        }//fine visualizza
        case "Esci": break;
        }

    }
      
    public static void gatherRequests(int mat, int codice,PrintWriter pw, BufferedReader br) throws IOException{
                                                        String sezione="null";
                                                        if (codice==1)
                                                        sezione="Attribuzione CFU";
                                                        else if (codice==2)
                                                        sezione="Materie a scelta";
                                                        else if (codice==3)
                                                        sezione="Rosetta Stone";
                                                        else if (codice==4)
                                                        sezione="Altre richieste";
                                                        try {
                                                            Statement stt = dbconnection.connettiti();
                                                            String ottieniRichieste = "SELECT nome, cognome, id, richiesta, giorno FROM Richieste JOIN Studenti on Studenti.matricola=Richieste.matricola WHERE stato='In attesa'and codice='"+codice+"'";
                                                            ResultSet rss = stt.executeQuery(ottieniRichieste);
                                                            ResultSetMetaData rssmd= rss.getMetaData();
                                                            int cnumber= rssmd.getColumnCount();
                                                            String x="d";
                                                            if(!rss.isBeforeFirst()){
                                                            pw.println("NO: Non ci sono richieste in attesa per la sezione "+sezione);
                                                            pw.flush();
                                                            x="x";
                                                            }
                                                                switch(x){
                                                                case "x":break;
                                                                default:{
                                                                while (rss.next()) {
                                                                        for (int i=1; i<=cnumber; i++){
                                                                                String columnValue= rss.getString(i);
                                                                                pw.println("SQL: "+rssmd.getColumnName(i).substring(0, 1).toUpperCase() + rssmd.getColumnName(i).substring(1)+ "  " + columnValue);
                                                                                pw.flush();
                                                                        }
                                                                }
                                                                    memberOperations(mat, codice, pw, br);
                                                           
                                                            }
                                                            break;//fine default
                                                        }
                                                    
                                                    
                                                    }
                                                        
                                                    
                                                    catch (Exception ee) {
                                                            ee.printStackTrace();
                                                            pw.println("NO: C'è stato un problema di connessione, riavviare");
                                                    }
                                                    
                                                    
        
    }
    public static void getList(PrintWriter pw, BufferedReader br){
        pw.println("NO: Ecco la lista di tutti i componenti del Db:");
                                                        
                                                        try {
                                                                Statement stt = dbconnection.connettiti();
                                                                String ottieniDatiStudenti = "SELECT nome, cognome, aa, matricola FROM Studenti";
                                                                ResultSet rss = stt.executeQuery(ottieniDatiStudenti);
                                                                Statement stm=dbconnection.connettiti();
                                                                Statement ns=dbconnection.connettiti();
                                                                Statement nm=dbconnection.connettiti();
                                                                String ottieniDatiMembri="SELECT nome, cognome, codice FROM Membri";
                                                                ResultSet rns=ns.executeQuery("SELECT COUNT(*) FROM Studenti");
                                                                ResultSet rnm=nm.executeQuery("SELECT COUNT(*) FROM Membri");
                                                                ResultSet rsm=stm.executeQuery(ottieniDatiMembri);
                                                                int numeroStudenti=0;
                                                                int numeroMembri=0;
                                                                while(rns.next()){
                                                                    numeroStudenti=rns.getInt(1);
                                                                }
                                                                while(rnm.next()){
                                                                    numeroMembri=rnm.getInt(1);
                                                                }
                                                                String name=null;
                                                                String surname=null;
                                                                String aa=null;
                                                                int matricola=0;
                                                                studente [] studenti;
                                                                studenti= new studente[numeroStudenti];
                                                                membro [] membri;
                                                                membri= new membro[numeroMembri];
                                                                int l=0;
                                                                persona[] persone;
                                                                persone= new persona[numeroStudenti+numeroMembri];
                                                                while (rss.next()) {
                                                                    for (int i=0; i<numeroStudenti; i++){
                                                                        name=rss.getString(1);
                                                                        surname=rss.getString(2);
                                                                        aa=rss.getString(3);
                                                                        matricola=rss.getInt(4);
                                                                        studenti[i]=new studente(name, surname, aa, matricola);
                                                                        persone[l]=studenti[i];
                                                                        }
                                                                        l++;
                                                                    }
                                                                    while (rsm.next()) {
                                                                        for (int i=0; i<numeroMembri; i++){
                                                                            name=rsm.getString(1);
                                                                            surname=rsm.getString(2);
                                                                            matricola=rsm.getInt(3);
                                                                            membri[i]=new membro(name, surname, matricola);
                                                                            persone[l]=membri[i];
                                                                        }
                                                                        l++;
                                                                    }
                                                                   
                                                                for (int i=0; i<persone.length; i++){
                                                                    pw.println(persone[i].getDati());
                                                                }
                                                                
                                                        } catch (Exception ex) {
                                                                ex.printStackTrace();
                                                                pw.println("NO: C'è stato un problema di connessione, riavviare");
                                                        }

                                                }

    }
