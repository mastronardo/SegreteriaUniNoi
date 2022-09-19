package com.mysqlconnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class dbconnection {

    public static Statement connettiti() throws Exception{
    String[] conf;
    conf=new String[5];
    conf=xmlHandler.getDbConfiguration();
    String address=conf[0].trim();
    String portS=conf[1].trim();
    int port = Integer.parseInt(portS);
    String dbname=conf[2].trim();
    String user=conf[3].trim();
    String pass=conf[4].trim();
    Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+dbname+"", ""+user+"", ""+pass+"");
                        Statement st = conn.createStatement();
                        return st;
                    }
    
}
