package com.mysqlconnector;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.XMLConstants;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class xmlHandler{
    private static final String FILENAMEDB = "src/main/databaseConfiguration.xml";
    private static final String FILENAMESV = "src/main/serverConfiguration.xml";

    public static String[] getDbConfiguration() throws JDOMException, IOException{
        SAXBuilder sax = new SAXBuilder();
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        Document doc = sax.build(new File(FILENAMEDB));

        Element rootNode = doc.getRootElement();
        List<Element> list = rootNode.getChildren("infos");
        String[] res;
        res= new String[5];
        for (Element target : list) {
            String address = target.getChildText("address");
            String port = target.getChildText("port");
            String dbname = target.getChildText("databasename");
            String user = target.getChildText("username");
            String pass = target.getChildText("password");
            res[0]=address;
            res[1]=port;
            res[2]=dbname;
            res[3]=user;
            res[4]=pass;
    }
    return res;
    }


    public static String[] getServerConfiguration() throws JDOMException, IOException{
        SAXBuilder sax = new SAXBuilder();
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        Document doc = sax.build(new File(FILENAMESV));

        Element rootNode = doc.getRootElement();
        List<Element> list = rootNode.getChildren("infos");
        String[] res;
        res= new String[3];
        for (Element target : list) {
            String ip = target.getChildText("ip");
            String studentport = target.getChildText("studentport");
            String memberport = target.getChildText("memberport");
            res[0]=ip;
            res[1]=studentport;
            res[2]=memberport;
    }
    return res;
    }

}
