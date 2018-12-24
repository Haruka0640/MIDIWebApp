

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ReadNoteXML extends XMLDomReader{
	private String XMLPath;
	
	
	public ReadNoteXML(String XMLPath){
		this.XMLPath = XMLPath + "/notenumbers.xml";
	}
	
	
    public int changeNoteToNum(String keyName,int octave) throws XMLException {
    	
    	int keyNumber = 0;
    	try {
    		Document document = buildDocument(XMLPath);
    		Element noteElement = document.getElementById(keyName);
      		if(noteElement == null) {
    			return 0;
    		}
    		keyNumber = Integer.parseInt(noteElement.getElementsByTagName("number").item(0).getTextContent());
    	}catch (NumberFormatException | NullPointerException | XMLException ex) {
    		throw new XMLException();
    		
    	}
    		
    	keyNumber = keyNumber + octave * 15;
    	
        return keyNumber;
        
    }
  
}
