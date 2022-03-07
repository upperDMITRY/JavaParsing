package xml_parsing.xml.domParser;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomParser {

    public static void main(String[] args) {

        try {
            // создаем объект файла для считывания с указанием в конструкторе к нему пафа
            File inputFile = new File("./dataForXMLParsing/inputXml.xml");
            // просим factory создать нам новый истенс объекта DocumentBuilderFactory (factory pattern)
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // создаем объекта на основе сконфигурированной dbFactory
            // который превртит нам XML файл в объект document с которым мы будем работать
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // делаем из нашего файла XML -> document object
            Document doc = dBuilder.parse(inputFile);
            // нормализируем ноды в документе, т.е выстраиваем данные поддерева
            // под этим узлами с исключением пустых строк и так далее
            doc.getDocumentElement().normalize();
            // выводим главный элемент то есть class
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            // достаем список элементов <student>
            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("----------------------------");

            // итерируемся по списку и выводим данные элементов и их атрибуты
            for (int temp = 0; temp < nList.getLength(); temp++) {
                // достаем по одной ноде из списка и обращаемся к ее значениям
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    // кастим ноду в эелемент для того чтоб вывести с него данные о студенте
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no : "
                            + eElement.getAttribute("rollno"));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
