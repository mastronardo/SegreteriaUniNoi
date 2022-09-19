package com.mysqlconnector;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class proceduraMembro implements Runnable{

    private ServerSocket ss;
    public proceduraMembro(int porta) throws IOException {
            ss = new ServerSocket(porta);
        }
        public void run(){
            PrintWriter pw;
            BufferedReader br;
            Socket s;
            
            boolean flag;
            while(true){
            System.out.println("\nIn attesa di connessioni, servizio Membro\n");
            try{
            s = ss.accept();
            flag = true;
            System.out.println("\nMembro Connesso\n");
            pw = new PrintWriter(s.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                while(flag){
                String pass = null;
                String mempass=null;
                pw.println("IN: Inserisca il suo codice d'accesso");
                String mats=br.readLine();
                int mat = Integer.parseInt(mats);
                try {
                        Statement st = dbconnection.connettiti();
                        String ottieniPass = "SELECT password FROM Membri WHERE codice= '" + mat + "'";
                        ResultSet rs = st.executeQuery(ottieniPass);
                        if (rs.next()){
                        pass = rs.getString(1);}
                        pw.println("IN: Inserisca la password");
                        mempass = br.readLine();
                        if (mempass.equals(pass)) {
                                String nome = null;
                                String cognome = null;
                                try {
                                        Statement sti = dbconnection.connettiti();
                                        String ottieniNameSurname = "SELECT Nome, Cognome FROM Membri WHERE codice= '" + mat + "'";
                                        ResultSet rsi = sti.executeQuery(ottieniNameSurname);
                                        if (rsi.next()){
                                        nome = rsi.getString(1);
                                        cognome = rsi.getString(2);}
                                        pw.println("NO: Buongiorno, " + nome + " " + cognome);
                                        pw.flush();
                                        String choice=null;
                                        do{
                                        pw.println("IN: Digiti Attribuzione, Materie, Rosetta, Altro o Lista");
                                        choice=br.readLine();
                                        choice=stringConverter.convert(choice);
                                        switch (choice) {
                                                case "Attribuzione": {
                                                    pw.println("NO: Si trova nella sezione Attribuzione CFU, di seguito le richieste in attesa: ");
                                                    pw.flush();
                                                        funzioniMembro.gatherRequests(mat, 1, pw, br);
                                                    
                                                    break;
                                                
                                                }

                                                    case "Materie": {
                                                        pw.println("NO: Si trova nella sezione Materie a scelta, di seguito le richieste in attesa:");
                                                        pw.flush();
                                                        funzioniMembro.gatherRequests(mat, 2, pw, br);
                                    
                                                    break;}

                                                    case "Rosetta": {
                                                        pw.println("NO: Si trova nella sezione Rosetta Stone, di seguito le richieste in attesa: ");
                                                        pw.flush();
                                                      funzioniMembro.gatherRequests(mat, 3, pw, br);
                                                      break;
                                                    }


                                                    case "Altro": {
                                                        pw.println("NO: Si trova nella sezione Altre richieste, di seguito le richieste in attesa: ");
                                                        pw.flush();
                                                        funzioniMembro.gatherRequests(mat, 4, pw, br);
                                                    break;}


                                                    
                                                    case "Lista": {
                                                        funzioniMembro.getList(pw, br);

                                                }

                                                break;    
                                    }//fine switch
                                    
                                    
                                }while(choice!="Esci");
                            }
                                catch (Exception e) {
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
        catch (IOException i){
            i.printStackTrace();
            continue;
        }     
        
}
}
}