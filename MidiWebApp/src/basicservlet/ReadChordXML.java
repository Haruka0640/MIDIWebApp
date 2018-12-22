package basicservlet;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadChordXML extends ReadNoteXML{
	String path; 
	
	ReadChordXML(){
		this.path = "src/resource/chordnumbers.xml";
	}
	
    public ArrayList<SoundDTO> changeChordToNum(int RootKeyNumber,String chordName) throws XMLException {
    	
    	ArrayList<SoundDTO> list = new ArrayList<>();
    	SoundDTO dto = new SoundDTO();
    	
    	String keyName = null;
    	int keyNumber;
    	int dynamics = 100;
    	int length = 0;

    	try {
    		Document document = buildDocument(path);
    		Element chordElement = document.getElementById(chordName);
    		NodeList semitonesNodeList = chordElement.getElementsByTagName("semitone");
    		
    		if (chordElement == null) {
    			return list;
    		}
    		
            for (int j = 0; j < semitonesNodeList.getLength(); j++) {
        		Node semitoneNode = semitonesNodeList.item(j);
        		if(semitoneNode.getNodeType() == Node.ELEMENT_NODE) {
        			Element semitoneElement = (Element)semitoneNode;
        			keyNumber = Integer.parseInt(semitoneElement.getTextContent());
        			keyNumber = keyNumber + RootKeyNumber;
                	dto.setDynamics(dynamics);
                	dto.setKeyName(keyName);
                	dto.setKeyNumber(keyNumber);
                	dto.setLength(length);
                	list.add(dto);
    	    	}
        	}
    	}catch (NumberFormatException | NullPointerException | XMLException ex) {
    		throw new XMLException();
    		
    	}

    	return list;   	

    }
}
