package com.mysqlconnector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMembro {
    private Socket s;
    public ClientMembro (String ip, int porta) throws IOException{
        s =new Socket(ip, porta);
        System.out.println(("Connesso"));
    }

 public void inizio() throws Exception{
    PrintStream ps;
    BufferedReader br;
    Scanner sc = new Scanner(System.in);
    String msg=null;
    String comando;
    boolean flag= true;
    
    ps = new PrintStream(s.getOutputStream(),true);
    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    while(flag){
    msg=null;
        msg=br.readLine();
    if (msg.startsWith("NO: ")){
            msg=msg.substring(4);
            while (br.ready()){
                String tmp= br.readLine();
               if (tmp!=null){
                msg+="\n"+tmp;
               }
            }
            String ml[]=msg.split("\n");
            for (int i=0; i<ml.length;i++){
                if (ml[i].startsWith("IN: ")){
                    ml[i]=ml[i].substring(4);
                    System.out.println(ml[i]);
                    comando=sc.nextLine();
                    ps.println(comando);
                    }
                
                    else{
                    System.out.println(ml[i]);}
            }
            continue;
        }
    else if (msg.startsWith("IN: ")){
        msg=msg.substring(4);
        System.out.println(msg);
        comando=sc.nextLine();
        ps.println(comando);
        }
        else if (msg.startsWith("SQL: ")){
            msg=msg.substring(5);
            System.out.println(msg);
        }    
        else if(msg.startsWith("LT: ")){
           msg=msg.substring(4);
            System.out.println(msg);}
    }


    ps.close();
    br.close();
    s.close();

 }
}