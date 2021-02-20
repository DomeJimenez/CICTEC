/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.cicte.ws.utils;

import java.io.File;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.apache.log4j.Logger;

/**
 *
 * @author pvillamar
 */
public class Config {
    public static String hostDB="localhost";
    public static String userDB="root";
    public static String passwordDB="Cicte2018*";
    public static String nameDB="cicte";
    public static String portDB="3306";


    private static Logger logger = org.apache.log4j.Logger.getLogger(Config.class);
    
    public static boolean setInitialConfiguration()
    {
        try {

            File fXmlFile = new File("./config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            logger.info("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("configuration");

            logger.info("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    logger.info("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;

                            logger.info("hostDB : " + eElement.getElementsByTagName("hostDB").item(0).getTextContent());
                            Config.hostDB=eElement.getElementsByTagName("hostDB").item(0).getTextContent();
                            
                            logger.info("userDB : " + eElement.getElementsByTagName("userDB").item(0).getTextContent());
                            Config.userDB=eElement.getElementsByTagName("userDB").item(0).getTextContent();
                            
                            logger.info("passwordDB : " + eElement.getElementsByTagName("passwordDB").item(0).getTextContent());
                            Config.passwordDB=eElement.getElementsByTagName("passwordDB").item(0).getTextContent();
                            
                            logger.info("nameDB : " + eElement.getElementsByTagName("nameDB").item(0).getTextContent());
                            Config.nameDB=eElement.getElementsByTagName("nameDB").item(0).getTextContent();
                            
                            logger.info("locationFH : " + eElement.getElementsByTagName("portDB").item(0).getTextContent());
                            Config.portDB=eElement.getElementsByTagName("portDB").item(0).getTextContent();
      
                    }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static final String getBasePathForClass(Class<?> clazz) {
    File file;
    try {
        String basePath = null;
        file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        if (file.isFile() || file.getPath().endsWith(".jar") || file.getPath().endsWith(".zip")) {
            basePath = file.getParent();
        } else {
            basePath = file.getPath();
        }
        // fix to run inside eclipse
        if (basePath.endsWith(File.separator + "lib") || basePath.endsWith(File.separator + "bin")
                || basePath.endsWith("bin" + File.separator) || basePath.endsWith("lib" + File.separator)) {
            basePath = basePath.substring(0, basePath.length() - 4);
        }
        // fix to run inside netbean
        if (basePath.endsWith(File.separator + "build" + File.separator + "classes")) {
            basePath = basePath.substring(0, basePath.length() - 14);
        }
        // end fix
        if (!basePath.endsWith(File.separator)) {
            basePath = basePath + File.separator;
        }
        return basePath;
    } catch (URISyntaxException e) {
        throw new RuntimeException("Cannot firgue out base path for class: " + clazz.getName());
    }
}
    
}
