package basicservlet;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLDomReader {

	public static Document buildDocument(String path) throws XMLException{
	    try {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document document = docBuilder.parse(path);
	        return document;
	    } catch (ParserConfigurationException | SAXException | IOException ex) {
	        ex.printStackTrace();
	    	throw new XMLException();
	    }
	}
	
	public static Element getElementbyName(String name) {
		
	}

}
