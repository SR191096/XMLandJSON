package exercise;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import practise.Student;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SAXParserDemo {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        SAXHandler handler = new SAXHandler();
        saxParser.parse("src/main/employee.xml",handler);

        for(Employee e : handler.employees)
        {
            System.out.println(e);
        }
    }
}

class SAXHandler extends DefaultHandler
{
    Collection<Employee> employees = new ArrayList<>();
    String content;
    Employee employee;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName)
        {
            case "employee":
            {
                employee = new Employee();
                employee.setId(Integer.parseInt(attributes.getValue("id")));
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName)
        {
            case "firstName":
            {
                employee.setFirstName(content);
                break;
            }
            case "lastName":
            {
               employee.setLastName(content);
                break;
            }
            case "designation":
            {
                employee.setDesignation(content);
                break;
            }

            case "employee":
            {
                employees.add(employee);
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content=String.copyValueOf(ch,start,length);
    }
}
