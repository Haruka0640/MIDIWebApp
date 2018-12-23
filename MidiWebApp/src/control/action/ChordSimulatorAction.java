package control.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import control.dto.*;
import control.midi.*;
import control.xmlreader.*;

public class ChordSimulatorAction implements Action{
	private SoundForm form;
	private String rootKey;
	private int accidental;
	private String chordName;
	private int octave;
	
	public boolean check(HttpServletRequest req) {
		
		//���[�g���̃`�F�b�N
		rootKey = req.getParameter("ROOTKEY");
		if( (rootKey == null) || (rootKey.equals("") ) ){
			return false;
		}
		
		//�ω��L���̃`�F�b�N
		try {
			accidental = Integer.parseInt(req.getParameter("ACCIDENTAL"));
		}catch (NumberFormatException e){
			return false;
		}
		
		//�R�[�h���̃`�F�b�N
		chordName = req.getParameter("CHORDNAME");
		if( (chordName == null) || (chordName.equals("") ) ){
			return false;
		}
		
		//�I�N�^�[�u�̃`�F�b�N
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
	
    public String execute(HttpServletRequest req) throws XMLException, MIDIException  {

    	ArrayList<SoundDTO> list = new ArrayList<SoundDTO>();
    	ReadNoteXML notereader = new ReadNoteXML();
    	ReadChordXML chordreader = new ReadChordXML();
    	PlayMIDISound player = new PlayMIDISound();
    	
    	int dynamics = 100;
    	int length = 0;
    	String rootKeyName;
    	int rootKeyNumber;
    	
    	//�ꉹ�߁i���[�g���j�����X�g�Ɋi�[
    	try {
        	rootKeyName = form.getRootKey();
    		rootKeyNumber = notereader.changeNoteToNum(rootKeyName,octave);
    		if (rootKeyNumber == 0) {
    			return "MidiWebApp/html/rootkeynotfound.html";
    		}
        	SoundDTO root = new SoundDTO(rootKeyName,rootKeyNumber,dynamics,length); 
        	list.add(root);
    	}catch (XMLException ex) {
    		throw new XMLException();
    	}
    	
    	//�񉹖ڈȍ~�����X�g�Ɋi�[
    	try {
    		list = chordreader.changeChordToNum(list,rootKeyNumber,chordName);
    		if (list == null) {
    			return "MidiWebApp/html/chordnotfound.html";
    		}
       	}catch (XMLException ex) {
    		throw new XMLException();
    	}
    	
    	//�Đ�
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
    	
    	return "MidiWebApp/index.html";
        
    }
    
}
    
    
