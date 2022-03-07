package xml_parsing.xml.additionalTasks;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class JDomQueryAdditionalTask {
    public static void main(String[] args) {

        String empId;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee id in next format (if your id is 1 then enter 001, if 999 then enter 999) to print information about him");

        /* в данном цикле будем проверять введенное значение на нужный формат empId документа,
         формат представляет собой трехзначное число, если оно однозначное или двух,
         недостающие значения перед числом замещает '0' */
        while (true) {
            try {
                empId = scanner.nextLine();
                if(!empId.matches("[0-9]{3}"))//проверка на соответствие требованиям
                    throw new RuntimeException();
                break;
            } catch (Exception e) {
                System.err.println("You entered wrong value, try again");
            }
        }

        try {
            File inputFile = new File("./dataForXMLParsing/companyXml.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            System.out.println("Root element :" + document.getRootElement().getName());
            Element classElement = document.getRootElement();

            List<Element> departmentList = classElement.getChildren("department");
            System.out.println("----------------------------\n");

            boolean employeeNotFound = true;

            for (int depNum = 0; depNum < departmentList.size(); depNum++) {
                List<Element> employeeList = departmentList.get(depNum).getChildren();

                for (int empNum = 0; empNum < employeeList.size(); empNum++) {
                    Element employee = employeeList.get(empNum);

                    if (employee.getAttribute("empId").getValue().equals(empId)) {

                        System.out.println("Employee with " + empId + " id");
                        System.out.println("------------------");
                        List<Element> employeeElements = employee.getChildren();

                        for (Element element : employeeElements) {

                            System.out.println(element.getName() + ": " + element.getValue());

                            if (element.getName().equals("managerId"))
                                empId = element.getValue();
                        }

                        employeeNotFound = false;
                        depNum = -1;
                        System.out.println("\n\n");
                        break;
                    }
                }
            }

            if (employeeNotFound)
                System.err.println("Employee with " + empId + " id does not exist");

        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
}
