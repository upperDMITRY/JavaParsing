package xml_parsing.xml.domParser;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Dom4JParser {

    public static void main(String[] args) {

        try {
            // создаем объект файла для считывания с указанием в конструкторе к нему пафа
            File inputFile = new File("./dataForXMLParsing/inputXml.xml");
            // создаем объект SAXReader который поможет нам считать xml данные с файла
            SAXReader reader = new SAXReader();
            // считываем данные в объект document где они будут храниться
            Document document = reader.read(inputFile);

            // выводим root element то есть нащ основной объект с которым мы работаем
            System.out.println("Root element :" + document.getRootElement().getName());

            Element classElement = document.getRootElement();

            //достаем все ноды которые содержатся в тегах <student>, в результате все храним в списке
            List<Node> nodes = document.selectNodes("/class/student");
            System.out.println("----------------------------");

            // итерируемся по полученным нодам и выводим их значения, в нашем случае выводим
            // все данные из тегов <student> с его атрибутом
            for (Node node : nodes) {
                System.out.println("\nCurrent Element :"
                        + node.getName());
                System.out.println("Student roll no : "
                        + node.valueOf("@rollno"));
                System.out.println("First Name : "
                        + node.selectSingleNode("firstname").getText());
                System.out.println("Last Name : "
                        + node.selectSingleNode("lastname").getText());
                System.out.println("First Name : "
                        + node.selectSingleNode("nickname").getText());
                System.out.println("Marks : "
                        + node.selectSingleNode("marks").getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

