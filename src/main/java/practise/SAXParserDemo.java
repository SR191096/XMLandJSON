package practise;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
        saxParser.parse("src/main/student.xml",handler);

        for(Student s : handler.students)
        {
            System.out.println(s);
        }
    }
}

class SAXHandler extends DefaultHandler
{
    Collection<Student> students = new ArrayList<>();
    String content;
    Student student;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName)
        {
            case "student":
            {
                student = new Student();
                student.setRollNo(Integer.parseInt(attributes.getValue("rollNo")));
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName)
        {
            case "firstName":
            {
                student.setFirstName(content);
                break;
            }
            case "lastName":
            {
                student.setLastName(content);
                break;
            }
            case "subject":
            {
                student.getSubjects().add(content);
                break;
            }

            case "student":
            {
                students.add(student);
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content=String.copyValueOf(ch,start,length);
    }
}
