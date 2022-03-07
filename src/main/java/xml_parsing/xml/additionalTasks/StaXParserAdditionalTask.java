package xml_parsing.xml.additionalTasks;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class StaXParserAdditionalTask {
    public static void main(String[] args) {

        boolean bFirstName = false;
        boolean bLastName = false;
        boolean bBirthDate = false;
        boolean bPosition = false;
        boolean bSkill = false;
        boolean bManagerId = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader("./dataForXMLParsing/companyXml.xml"));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("department")) {
                            System.out.println("Start Element: department");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String name = attributes.next().getValue();
                            System.out.println("\tname: " + name);
                            String depId = attributes.next().getValue();
                            System.out.println("\tdepartment id: " + depId);
                        } else if (qName.equalsIgnoreCase("employee")) {
                            System.out.println("Start Element: employee");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String empId = attributes.next().getValue();
                            System.out.println("\temployee id: " + empId);
                        } else if (qName.equalsIgnoreCase("skills")) {
                            System.out.println("Start Element: skills");
                        } else if (qName.equalsIgnoreCase("firstName")) {
                            bFirstName = true;
                        } else if (qName.equalsIgnoreCase("lastName")) {
                            bLastName = true;
                        } else if (qName.equalsIgnoreCase("birthDate")) {
                            bBirthDate = true;
                        } else if (qName.equalsIgnoreCase("position")) {
                            bPosition = true;
                        } else if (qName.equalsIgnoreCase("skill")) {
                            bSkill = true;
                        } else if (qName.equalsIgnoreCase("managerId")) {
                            bManagerId = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (bFirstName) {
                            System.out.println("\tFirst Name: " + characters.getData());
                            bFirstName = false;
                        }
                        if (bLastName) {
                            System.out.println("\tLast Name: " + characters.getData());
                            bLastName = false;
                        }
                        if (bBirthDate) {
                            System.out.println("\tBirthDate: " + characters.getData());
                            bBirthDate = false;
                        }
                        if (bPosition) {
                            System.out.println("\tPosition: " + characters.getData());
                            bPosition = false;
                        }
                        if (bSkill) {
                            System.out.println("\tSkill: " + characters.getData());
                            bSkill = false;
                        }
                        if (bManagerId) {
                            System.out.println("\tManager id: " + characters.getData());
                            bManagerId = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("department")) {
                            System.out.println("End Element : department");
                            System.out.println();
                        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("employee")) {
                            System.out.println("End Element : employee");
                            System.out.println();
                        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("skills")) {
                            System.out.println("End Element : skills");
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
