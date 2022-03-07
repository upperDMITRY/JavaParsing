package xml_parsing.xml.XPathParser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XPathParser {

    public static void main(String[] args) {

        try {
            // создаем объект файла для считывания с указанием в конструкторе к нему пафа
            File inputFile = new File("./dataForXMLParsing/inputXml.xml");
            // просим factory создать нам новый истенс объекта DocumentBuilderFactory (factory pattern)
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            // создаем объекта на основе сконфигурированной dbFactory
            // который превртит нам XML файл в объект document с которым мы будем работать
            dBuilder = dbFactory.newDocumentBuilder();

            // парсим нам XML документ java Document
            Document doc = dBuilder.parse(inputFile);
            // нормализируем ноды в документе, т.е выстраиваем данные поддерева
            // под этим узлами с исключением пустых строк и так далее
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            //достаем все ноды которые содержатся в тегах <student>, в результате все храним в списке
            String expression = "/class/student";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

            // итерируемся по нодам, достаем каждую из них и кастим их к объекту Element
            // который помогает нам взаимодействовать с информацией в ноде
            // и после выводим информацию хранящююся в элементе, достаем значения по названию тэга
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no :" + eElement.getAttribute("rollno"));
                    System.out.println("First Name : "
                            + eElement
                            .getElementsByTagName("firstname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Last Name : "
                            + eElement
                            .getElementsByTagName("lastname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Nick Name : "
                            + eElement
                            .getElementsByTagName("nickname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Marks : "
                            + eElement
                            .getElementsByTagName("marks")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
