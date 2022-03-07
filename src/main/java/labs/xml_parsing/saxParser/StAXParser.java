package labs.xml_parsing.saxParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXParser {
    public static void main(String[] args) {
        boolean bFirstName = false;
        boolean bLastName = false;
        boolean bNickName = false;
        boolean bMarks = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            // считываем данные XML документа
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader("./dataForXMLParsing/inputXml.xml"));

            // проходим по данным пока они не закончатся
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                // смотрим что мы считали начала тега, его содержимое либо его конец
                switch(event.getEventType()) {

                    // если это начало объекта выводим его название и атрибуты,
                    // если это филды объекта ставим значение в true там где мы
                    // сейчас находимся (что сейчас считали)
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("student")) {
                            System.out.println("Start Element : student");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String rollNo = attributes.next().getValue();
                            System.out.println("Roll No : " + rollNo);
                        } else if (qName.equalsIgnoreCase("firstname")) {
                            bFirstName = true;
                        } else if (qName.equalsIgnoreCase("lastname")) {
                            bLastName = true;
                        } else if (qName.equalsIgnoreCase("nickname")) {
                            bNickName = true;
                        }
                        else if (qName.equalsIgnoreCase("marks")) {
                            bMarks = true;
                        }
                        break;

                    // если мы считали данные тега, то просматриваем, какой филд у нас стоит
                    // в true и выводим его данные и ставим его в false для следующего считывания
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bFirstName) {
                            System.out.println("First Name: " + characters.getData());
                            bFirstName = false;
                        }
                        if(bLastName) {
                            System.out.println("Last Name: " + characters.getData());
                            bLastName = false;
                        }
                        if(bNickName) {
                            System.out.println("Nick Name: " + characters.getData());
                            bNickName = false;
                        }
                        if(bMarks) {
                            System.out.println("Marks: " + characters.getData());
                            bMarks = false;
                        }
                        break;

                        // если мы считали конец элемента тега, то выводим его
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if(endElement.getName().getLocalPart().equalsIgnoreCase("student")) {
                            System.out.println("End Element : student");
                            System.out.println();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}