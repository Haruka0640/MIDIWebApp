package basicservlet;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

class ChordSimulatorAction implements Action{
	private SoundForm form;
	private String rootKey;
	private int accidental;
	private String chordName;
	private int octave;
	
	public boolean check(HttpServletRequest req) {
		
		//ルート音のチェック
		rootKey = req.getParameter("ROOTKEY");
		if( (rootKey == null) || (rootKey.equals("") ) ){
			return false;
		}
		
		//変化記号のチェック
		try {
			accidental = Integer.parseInt(req.getParameter("ACCIDENTAL"));
		}catch (NumberFormatException e){
			return false;
		}
		
		//コード名のチェック
		chordName = req.getParameter("CHORDNAME");
		if( (chordName == null) || (chordName.equals("") ) ){
			return false;
		}
		
		//オクターブのチェック
		try {
			octave = Integer.parseInt(req.getParameter("OCTAVE"));
		}catch (NumberFormatException e){
			return false;
		}
		
		form.setRootKey(rootKey);
		form.setAccidental(accidental);
		form.setChordName(chordName);
		form.setOctave(octave);
		
		return true;
		
	}
	
    public void execute(HttpServletRequest req) throws ActionException {

    	ArrayList<SoundDTO> list = new ArrayList<SoundDTO>();
    	SoundDTO dto = new SoundDTO();
    	int dynamics = 100;
    	int length = 0;
    	
    	//一音め（ルート音）をリストに格納
    	try {
        	String rootKeyName = form.getRootKey();
        	int octave = form.getOctave();
    		int rootKeyNumber = changeNoteToNum(keyName,octave);
        	dto.setDynamics(dynamics);
        	dto.setKeyName(rootKeyName);
        	dto.setKeyNumber(rootKeyNumber);
        	dto.setLength(length);
        	list.add(dto);
    	}catch (XMLException ex) {
    		throw new XMLException();
    	}
    	
    	//二音目以降をリストに格納
    	//listに情報を格納する処理を記述
    	
    	
        for(SoundDTO obj: list) {
        	String keyName = obj.getKeyName();
        	int keyNumber = obj.getKeyNumber();
        	int dynamics = obj.getDynamics();
        	int length = obj.getLength();
        	System.out.println(keyName);
        	playSound(keyNumber,dynamics);
        	Thread.sleep(length);
        	playSound(keyNumber,0);
       }        
        
    }
    

    
  
    private static ArrayList<Integer> invertChord(ArrayList<Integer> chordArray,int valtype) {
    	
    	int baf;
    	int baf2;
    	int arrayLength;
    	
    	arrayLength = chordArray.size();
    	
    	for (int i = 0; i < valtype; i++) {
    		baf = chordArray.get(0);
        	for (int j = 1; j < arrayLength ; j++) {
        		baf2 = chordArray.get(j);
        		chordArray.set(j - 1,baf2);
        	}
    		chordArray.set(arrayLength - 1,baf + 12);
    	}
    	
    	return chordArray;
    	
    	
    }
    
   static void playSound(int intNote , int intDynamics) throws MidiUnavailableException, InvalidMidiDataException {

        Receiver receiver = MidiSystem.getReceiver();
        ShortMessage message = new ShortMessage();
        
      	message.setMessage(ShortMessage.NOTE_ON, intNote, intDynamics);
    	receiver.send(message, -1);
        	
    }
    
}