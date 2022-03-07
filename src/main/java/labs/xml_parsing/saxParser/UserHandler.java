package labs.xml_parsing.saxParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bNickName = false;
    boolean bMarks = false;

    // выводим данные начала элементов, то есть открывающиеся теги и их атрибуты,
    // если эти теги представляют собой поля объектов, то обозначаем что мы находимся
    // на соответсвующем поле установив значение true к его параментру
    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("student")) {
            String rollNo = attributes.getValue("rollno");
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
    }

    // считав конец элемента его закрытие тега, выводим что элемент был закрыт
    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("student")) {
            System.out.println("End Element :" + qName);
        }
    }

    // когда мы считываем данные поля объекта с документа, прохоидимся по нашим переменным и смотрим
    // какое из них значение стоит в true, значи те данные мы и считываем и выводим их
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bFirstName) {
            System.out.println("First Name: "
                    + new String(ch, start, length));
            bFirstName = false;
        } else if (bLastName) {
            System.out.println("Last Name: " + new String(ch, start, length));
            bLastName = false;
        } else if (bNickName) {
            System.out.println("Nick Name: " + new String(ch, start, length));
            bNickName = false;
        } else if (bMarks) {
            System.out.println("Marks: " + new String(ch, start, length));
            bMarks = false;
        }
    }
}
