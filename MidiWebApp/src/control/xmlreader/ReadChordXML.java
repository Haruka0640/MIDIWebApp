package control.xmlreader;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import control.dto.SoundDTO;

public class ReadChordXML extends XMLDomReader{
	String path; 
	
	public ReadChordXML(){
		this.path = "src/resource/chordnumbers.xml";
	}
	
    public ArrayList<SoundDTO> changeChordToNum(ArrayList<SoundDTO> list,int RootKeyNumber,String chordName) throws XMLException {
    	   	
    	String keyName = null;
    	int keyNumber;
    	int dynamics = 100;
    	int length = 0;

    	try {
    		Document document = buildDocument(path);
    		Element chordElement = document.getElementById(chordName);
    		//å©Ç¬Ç©ÇÁÇ»Ç©Ç¡ÇΩèÍçáNullÇï‘Ç∑
    		if (chordElement == null) {
    			return null;
    		}
        	NodeList semitoneNodeList = chordElement.getElementsByTagName("semitone");
        	
    		System.out.println(semitoneNodeList.getLength());
    		
            for (int j = 0; j < semitoneNodeList.getLength(); j++) {
        		Node semitoneNode = semitoneNodeList.item(j);
        		if(semitoneNode.getNodeType() == Node.ELEMENT_NODE) {
        			Element semitoneElement = (Element)semitoneNode;
        			keyNumber = Integer.parseInt(semitoneElement.getTextContent());
        			keyNumber = keyNumber + RootKeyNumber;
                	SoundDTO dto = new SoundDTO(keyName, keyNumber, dynamics, length);
                	list.add(dto);
    	    	}
        	}
    	}catch (NumberFormatException | NullPointerException | XMLException ex) {
    		throw new XMLException();
    	}

    	return list;   	

    }
}
