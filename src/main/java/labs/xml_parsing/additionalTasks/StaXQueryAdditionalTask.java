package labs.xml_parsing.additionalTasks;

import java.util.Scanner;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class StaXQueryAdditionalTask {
    public static void main(String[] args) {
        String inputEmpId;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee id in next format (if your id is 1 then enter 001, if 999 then enter 999) to print information about him");

        /* в данном цикле будем проверять введенное значение на нужный формат empId документа,
         формат представляет собой трехзначное число, если оно однозначное или двух,
         недостающие значения перед числом замещает '0' */
        while (true) {
            try {
                inputEmpId = scanner.nextLine();
                if (!inputEmpId.matches("[0-9]{3}"))//проверка на соответствие требованиям
                    throw new RuntimeException();
                break;
            } catch (Exception e) {
                System.err.println("You entered wrong value, try again");
            }
        }

        boolean bFirstName = false;
        boolean bLastName = false;
        boolean bBirthDate = false;
        boolean bPosition = false;
        boolean bSkill = false;
        boolean bManagerId = false;
        boolean employeeFound = false;
        String depName = "";
        String depId = "";
        String docEmpId = "";

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
                            System.out.println();
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            depName = attributes.next().getValue();
                            depId = attributes.next().getValue();
                        } else if (qName.equalsIgnoreCase("employee")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String empId = attributes.next().getValue();
                            if (empId.equals(inputEmpId)) {
                                employeeFound = true;
                                System.out.println("Start Element: department");
                                System.out.println("\tname: " + depName);
                                System.out.println("\tdepartment id: " + depId);
                                System.out.println("Start Element: employee");
                                System.out.println("\temployee id: " + empId);
                            }
                        } else if (employeeFound) {
                            if (qName.equalsIgnoreCase("skills")) {
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

                        if (employeeFound) {
                          if (endElement.getName().getLocalPart().equalsIgnoreCase("employee")) {
                                System.out.println("End Element : employee");
                                employeeFound = false;
                                System.out.println("End Element : department");
                                System.out.println();
                            } else if (endElement.getName().getLocalPart().equalsIgnoreCase("skills")) {
                                System.out.println("End Element : skills");
                            }
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
