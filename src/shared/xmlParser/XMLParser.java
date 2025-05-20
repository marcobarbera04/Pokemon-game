package shared.xmlParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class XMLParser {
    private Document document;

    public XMLParser(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String parentTag, String childTag) {
        Element parentElement = (Element) document.getElementsByTagName(parentTag).item(0);
        return parentElement.getElementsByTagName(childTag).item(0).getTextContent();
    }
}