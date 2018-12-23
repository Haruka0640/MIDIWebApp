package control.xmlreader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ReadNoteXML extends XMLDomReader{
	String path; 
	
	public ReadNoteXML(){
		this.path = "/src/resource/notenumbers.xml";
	}

    public int changeNoteToNum(String keyName,int octave) throws XMLException {
    	 
    	int keyNumber = 0;
    	//XML�t�@�C������L�[�ԍ����擾
    	try {
    		Document document = buildDocument(path);
    		Element noteElement = document.getElementById(keyName);
    		//������Ȃ��ꍇ�͂O��Ԃ�
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
