

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadChordXML extends XMLDomReader{
	String XMLPath; 
	
	public ReadChordXML(String XMLPath){
		this.XMLPath = XMLPath + "/chordnumbers.xml";
	}
	
    public ArrayList<SoundDTO> changeChordToNum(ArrayList<SoundDTO> list,int RootKeyNumber,String chordName) throws XMLException {
    	   	
    	String keyName = null;
    	int keyNumber;
    	int dynamics = 100;
    	int length = 0;

    	try {
    		Document document = buildDocument(XMLPath);
    		Element chordElement = document.getElementById(chordName);

    		if (chordElement == null) {
    			return null;
    		}
        	NodeList semitoneNodeList = chordElement.getElementsByTagName("semitone");
        	    		
            for (int j = 0; j < semitoneNodeList.getLength(); j++) {
        		Element semitoneElement = (Element)semitoneNodeList.item(j);
        		keyNumber = Integer.parseInt(semitoneElement.getTextContent());
        		keyNumber = keyNumber + RootKeyNumber;
                SoundDTO dto = new SoundDTO(keyName, keyNumber, dynamics, length);
                list.add(dto);
        	}
    	}catch (NumberFormatException | NullPointerException | XMLException ex) {
    		throw new XMLException();
    	}

    	return list;   	

    }
}
