package com.mysqlconnector;
public class Server {
    public static void main(String[] args) throws Exception {
        
            Thread procedura = new Thread(new proceduraStudente(6569));
            procedura.start();
            
            Thread proceduram = new Thread(new proceduraMembro(6869));
            proceduram.start();
        }
    }

