package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers;

import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by tiesbaltissen on 21-04-17.
 */
public class DocumentReader {

    public static String readFromFileWithTag(String filePath, String tag) throws ParserConfigurationException, IOException, SAXException {
        return DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(new File(filePath))
                .getElementsByTagName(tag)
                .item(0)
                .getTextContent();
    }
}
