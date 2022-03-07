package xml_parsing.xml.additionalTasks;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;

public class JDomCreateAdditionalTask {
    public static void main(String[] args) {

        try{
            //root element
            Element companyElement = new Element("company");
            Document doc = new Document(companyElement);

            //department element
            Element departmentElement = new Element("department");
            departmentElement.setAttribute(new Attribute("name","Development"));
            departmentElement.setAttribute(new Attribute("depId","1"));

            //employee element
            Element employeeElement = new Element("employee");
            employeeElement.setAttribute(new Attribute("empId","001"));

            Element lastNameField = new Element("lastName");
            lastNameField.setText("Curchin");

            Element firstNameField = new Element("firstName");
            firstNameField.setText("Dmitrii");

            Element birthdateField = new Element("birthDate");
            birthdateField.setText("01.01.1900");

            Element positionField = new Element("position");
            positionField.setText("Department Lead");

            //skills element
            Element skillsElement = new Element("skills");

            Element skillField1 = new Element("skill");
            skillField1.setText("Communication");

            Element skillField2 = new Element("skill");
            skillField2.setText("Java");

            Element skillField3 = new Element("skill");
            skillField3.setText("Adaptable");

            //adding skills fields
            skillsElement.addContent(skillField1);
            skillsElement.addContent(skillField2);
            skillsElement.addContent(skillField3);

            Element managerIdField = new Element("managerId");
            managerIdField.setText("0");

            //adding employee fields
            employeeElement.addContent(lastNameField);
            employeeElement.addContent(firstNameField);
            employeeElement.addContent(birthdateField);
            employeeElement.addContent(positionField);
            employeeElement.addContent(skillsElement);
            employeeElement.addContent(managerIdField);

            //adding department fields
            departmentElement.addContent(employeeElement);

            //adding department to root element
            doc.getRootElement().addContent(departmentElement);

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, System.out);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
