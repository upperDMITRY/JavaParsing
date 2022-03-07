package labs.xml_parsing.saxParser;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParser {

    public static void main(String[] args) {

        try {
            // создаем объект файла для считывания с указанием в конструкторе к нему пафа
            File inputFile = new File("./dataForXMLParsing/inputXml.xml");
            // просим factory создать нам новый истенс объекта SAXParserFactory (factory pattern)
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // создаем объект SAXParser (на основе сконфигурированной factory) который парсит наш XML файл и
            // с помощью userHandler выводит данные
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
