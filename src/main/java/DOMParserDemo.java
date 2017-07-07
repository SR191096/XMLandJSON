import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DOMParserDemo {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        DomParser domParser = new DomParser();
        Collection<Student> students = domParser.parsing();

        for(Student s : students)
        {
            System.out.println(s);
        }

    }

}

class DomParser {
    Collection<Student> students = new ArrayList<>();
    Student student;
    String content;
    Collection<Student> parsing() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("src/main/student.xml");
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("student");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            student = new Student();
            if (node instanceof Element) {
                student.setRollNo(Integer.parseInt(node.getAttributes().getNamedItem("rollNo").getNodeValue()));
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);


                    if (cNode instanceof Element) {

                        content = cNode.getLastChild().
                                getTextContent().trim();
                        switch (cNode.getNodeName()) {
                            case "firstName":
                                student.setFirstName(content);
                                break;
                            case "lastName":
                                student.setLastName(content);
                                break;
                            case "subjects":
                            {   NodeList sChildNodes = cNode.getChildNodes();

                                for (int k = 0; k < sChildNodes.getLength(); k++) {
                                Node scNode = sChildNodes.item(k);

                                if (scNode instanceof Element)
                                {
                                    student.getSubjects().add(scNode.getLastChild().getTextContent().trim());
                                }
                                }

                                break;
                            }
                        }
                    }
                }

            }
            students.add(student);
        }
        return students;
    }
}
