package com.mysqlconnector;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class proceduraStudente implements Runnable{
        private ServerSocket ss;
        public proceduraStudente(int porta) throws IOException {
                ss = new ServerSocket(porta);
            }
        
        public void run(){
                PrintWriter pw;
                BufferedReader br;
                Socket s;
                boolean flag;
                while(true){
                System.out.println("\nIn attesa di connessioni, servizio Studenti \n");
                try{
                s = ss.accept();
                flag = true;
                System.out.println("\nStudente Connesso\n");
                pw = new PrintWriter(s.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                while(flag){        
                String pass="default";
                String studpass="default";
                pw.println("IN: Inserisci la tua matricola");
                String mats = br.readLine();
                int mat= Integer.parseInt(mats);
                try {
                        Statement st=dbconnection.connettiti();
                        String ottieniPass = "SELECT password FROM Studenti WHERE matricola= '" + mat + "'";
                        ResultSet rs = st.executeQuery(ottieniPass);
                        if (rs.next()){
                        pass = rs.getString(1);}
                        pw.println("IN: Inserisci la password");
                        studpass = br.readLine();
                        if (studpass.equals(pass)) {
                                String nome = "null";
                                String cognome = "null";
                                try {
                                        Statement sti = dbconnection.connettiti();
                                        String ottieniNameSurname = "SELECT Nome, Cognome FROM Studenti WHERE matricola= '" + mat + "'";
                                        ResultSet rsi = sti.executeQuery(ottieniNameSurname);
                                        if (rsi.next()){
                                        nome = rsi.getString(1);
                                        cognome = rsi.getString(2);}
                                        pw.println("NO: Ciao, " + nome + " " + cognome);
                                        String choice;
                                        do{
                                        pw.println("IN: Digita Attribuzione, Materie, Rosetta, Altro o Dati");
        
                                        choice = br.readLine();
                                        choice=stringConverter.convert(choice);
                                        String comando="null";
                                        switch (choice) {
                                                case "Attribuzione": {
                        
                                                        pw.println("IN: Ti trovi nella sezione Attribuzione CFU, digita Nuovo per effettuare una nuova richiesta, Visualizza per ottenere la lista delle richieste già presentate o Esci per tornare indietro");
                        
                                                        comando=br.readLine();                
                        
                                                        comando=stringConverter.convert(comando);
                                                        switch (comando) {
                                                                case "Nuovo": {
                                                                        funzioniStudente.nuovaRichiesta(mat, 1, pw, br);
                                                                break;
                                                        }
                                                                
                                                                case "Visualizza": {
                                                                        
                                                                     funzioniStudente.visualizzaRisposte(mat,1, pw, br);
                                                                        break;
                                                                }
                                                                
                                                                case "Esci": break;
                                                        
                                                                default:
                                        
                                                                        pw.println("Comando non riconosciuto, riprovare");
                                                        }
                                               
                                                        
                                                
                                        
                                                break;
                                        
                                }
                                
        
                                                case "Materie": {
                        
                                                        pw.println("IN: Ti trovi nella sezione Materie, digita Nuovo per effettuare una nuova richiesta, Visualizza per ottenere la lista delle richieste già presentate o Esci per tornare indietro");
                        
                                                        comando = br.readLine();
                                                        comando=stringConverter.convert(comando);
                                                        switch (comando) {
                                                                case "Nuovo": {
                                                                        funzioniStudente.nuovaRichiesta(mat, 2, pw, br);
                                                                
                                                                break;
                                                        }
                                                                
                                                        case "Visualizza": {
                                                                        
                                                           funzioniStudente.visualizzaRisposte(mat, 2, pw, br);
                                                    }
                                                    
                                                    case "Esci": break;
                                            
                                                    default:
                                
                                                            pw.println("Comando non riconosciuto, riprovare");
                                
                                            }
                                   
                                            
                                    
                            
                                    break;
                            
                    }

                                                        case "Rosetta": {
                                
                                                                pw.println("IN: Ti trovi nella sezione Rosetta Stone, digita Nuovo per effettuare una nuova richiesta, Visualizza per ottenere la lista delle richieste già presentate o Esci per tornare indietro");
                                
                                                                comando = br.readLine();
                                                                comando=stringConverter.convert(comando);
                                                                switch (comando) {
                                                                        case "Nuovo": 
                                                                        
                                                                        {
                                                                                funzioniStudente.nuovaRichiesta(mat, 3, pw, br);
                                                                        
                                                                        break;
                                                        }
                                                        case "Visualizza": {
                                                                        funzioniStudente.visualizzaRisposte(mat, 3, pw, br);
                                                            break;
                                                    }
                                                    
                                                    case "Esci": break;
                                            
                                                    default:
                        
                                                            pw.println("Comando non riconosciuto, riprovare");
                                            }
                                   
                                            
                                    
                            
                                    break;
                            
                    }
                                                                        case "Altro": {
                                                
                                                                                pw.println("IN: Ti trovi nella sezione Altre richieste, digita Nuovo per effettuare una nuova richiesta, Visualizza per ottenere la lista delle richieste già presentate o Esci per tornare indietro");
                                                
                                                                                comando = br.readLine();
                                                                        comando=stringConverter.convert(comando);
                                                                        
                                                                                switch (comando) {
                                                                                        case "Nuovo": {
                                                                                        funzioniStudente.nuovaRichiesta(mat, 4, pw, br);
                                                                                        break;
                                                                                        }
                                                                                        
                                                                                        case "Visualizza": {
                                                                        
                                                                                            funzioniStudente.visualizzaRisposte(mat, 4, pw, br);
                                                                                    
                                                                                            break;
                                                                                    }
                                                                                    
                                                                                    case "Esci": break;
                                                                            
                                                                                    default:
                                                        
                                                                                            pw.println("NO: Comando non riconosciuto, riprovare");
                                                                            }
                                                                   
                                                                            
                                                                    
                                                            
                                                                    break;
                                                                        }

                                                                        case "Dati": {
                                                                               funzioniStudente.datiStudente(mat, pw, br);
                                                                        }
                
                                                                        break;        
                                                    }
                                        //fine switch
                                    
                                    }while(choice!="Esci");
                                } catch (Exception e) {
                                        e.printStackTrace();
        
                                        pw.println("NO: C'è stato un problema di connessione, riavviare");
                                }
        
                        }
                
                        else {

                                pw.println("NO: La matricola o la password inserite non sono corrette, riavviare");}
                        
        
                        
                } catch (Exception et) {
                        et.printStackTrace();
                        pw.println("NO: C'è stato un problema di connessione, riavviare");
                }
        }
                pw.close();
                br.close();
                s.close();
}
catch(IOException i){
i.printStackTrace();
continue;
}
        
        }
      
        

}
}
