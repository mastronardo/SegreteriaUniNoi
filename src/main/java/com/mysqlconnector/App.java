package com.mysqlconnector;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBenvenuto! Digita Studente per il login da studente o digita Membro per il login da membro della commissione\n");
        String choice = sc.nextLine();
        String conf[]= xmlHandler.getServerConfiguration();
        String ip=conf[0].trim();
        String studentport=conf[1].trim();
        String memberport=conf[2].trim();
        int studentportI=Integer.parseInt(studentport);
        int memberportI=Integer.parseInt(memberport);
        if (choice.equalsIgnoreCase("Studente")) {
            Client c= new Client (ip, studentportI);
            c.inizio();
        } else if (choice.equalsIgnoreCase("Membro")) {
            ClientMembro c= new ClientMembro (ip, memberportI);
            c.inizio();
        } else {
            System.out.println("Comando non riconosciuto, riprovare\n");
        }

       
        

    }

}