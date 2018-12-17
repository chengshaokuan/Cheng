package com.csk.DesignPatterns;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @program: Cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2018-10-23 15:52
 **/
public class xmlUtil {
    public static void main (String[] args) {
        Object bean = getBean();
        System.out.println(bean.toString());
    }

    public static Object getBean(){

        String nodeValue="";
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(".\\src\\main\\resources\\config.xml"));
            NodeList csk = document.getElementsByTagName("csk");
            Node firstChild = csk.item(0).getFirstChild();
             nodeValue = firstChild.getNodeValue();


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return nodeValue;

    }
}
