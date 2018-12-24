

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class ChordSimulatorAction implements Action{
	private SoundForm form = new SoundForm();
	private String rootKey;
	private int accidental;
	private String chordName;
	private int octave;
	
	public boolean check(HttpServletRequest req) {
		
		rootKey = req.getParameter("ROOTKEY");
		if( (rootKey == null) || (rootKey.equals("") ) ){
			return false;
		}
		
		try {
			accidental = Integer.parseInt(req.getParameter("ACCIDENTAL"));
		}catch (NumberFormatException e){
			return false;
		}
		
		chordName = req.getParameter("CHORDNAME");
		if( (chordName == null) || (chordName.equals("") ) ){
			return false;
		}
		
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
	
    public String execute(HttpServletRequest req,String WEBINFPath) throws XMLException, MIDIException  {

    	ArrayList<SoundDTO> list = new ArrayList<SoundDTO>();
    	ReadNoteXML notereader = new ReadNoteXML(WEBINFPath);
    	ReadChordXML chordreader = new ReadChordXML(WEBINFPath);
    	PlayMIDISound player = new PlayMIDISound();

    	
    	int dynamics = 100;
    	int length = 500;
    	String rootKeyName;
    	int rootKeyNumber;
    	
    	try {
        	rootKeyName = form.getRootKey();
    		rootKeyNumber = notereader.changeNoteToNum(rootKeyName,octave);
    		if (rootKeyNumber == 0) {
    			return "HTML/rootkeynotfound.html";
    		}
        	SoundDTO root = new SoundDTO(rootKeyName,rootKeyNumber,dynamics,length); 
        	list.add(root);
    	}catch (XMLException ex) {
    		throw new XMLException();
    	}
    	
    	try {
    		list = chordreader.changeChordToNum(list,rootKeyNumber,chordName);
    		if (list == null) {
    			return "HTML/chordnotfound.html";
    		}
       	}catch (XMLException ex) {
    		throw new XMLException();
    	}
    	
    	//çƒê∂
    	try {
	        for(SoundDTO dto: list) {
	        	int keyNumber = dto.getKeyNumber();
	        	player.playSingleSound(keyNumber,dynamics);
	        	Thread.sleep(length);
	        	player.playSingleSound(keyNumber,0);
	        }
    	}catch(MIDIException | InterruptedException ex) {
	    	throw new MIDIException();
	    }
    	
    	return "index.html";
        
    }
    
}
    
    
