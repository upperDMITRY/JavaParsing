package xml_parsing.xml.domParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomParserTask3 {
    public static void main(String[] args) {

        try {
            File inputFile = new File("./dataForXMLParsing/emp.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("department");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength() - 1; temp++) {
                NodeList nodeList = doc.getElementsByTagName("employee");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node nNode = nodeList.item(i);
                    System.out.println("\nCurrent Element :" + nNode.getNodeName());
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        System.out.println("Last name: "
                                + eElement
                                .getElementsByTagName("lastName")
                                .item(0)
                                .getTextContent());
                        System.out.println("First Name : "
                                + eElement
                                .getElementsByTagName("firstName")
                                .item(0)
                                .getTextContent());
                        System.out.println("Birth date : "
                                + eElement
                                .getElementsByTagName("birthDate")
                                .item(0)
                                .getTextContent());
                        System.out.println("Position : "
                                + eElement
                                .getElementsByTagName("position")
                                .item(0)
                                .getTextContent());
                        System.out.println("Skills : "
                                + eElement
                                .getElementsByTagName("skills")
                                .item(0)
                                .getTextContent());
                        System.out.println("Manager id : "
                                + eElement
                                .getElementsByTagName("managerId")
                                .item(0)
                                .getTextContent().trim());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
