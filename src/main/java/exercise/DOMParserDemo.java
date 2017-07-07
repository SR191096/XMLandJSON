package exercise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import practise.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DOMParserDemo {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        DomParser domParser = new DomParser();
        Collection<Employee> employees = domParser.parsing();

        for(Employee e : employees)
        {
            System.out.println(e);
        }

    }

}

class DomParser {
    Collection<Employee> employees = new ArrayList<>();
    Employee employee;
    String content;
    Collection<Employee> parsing() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("src/main/employee.xml");
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("employee");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            employee = new Employee();
            if (node instanceof Element) {
                employee.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);


                    if (cNode instanceof Element) {

                        content = cNode.getLastChild().
                                getTextContent().trim();
                        switch (cNode.getNodeName()) {
                            case "firstName":
                                employee.setFirstName(content);
                                break;
                            case "lastName":
                                employee.setLastName(content);
                                break;
                            case "designation":
                            {
                                employee.setDesignation(content);
                                break;
                            }
                        }
                    }
                }

            }
            employees.add(employee);
        }
        return employees;
    }
}
