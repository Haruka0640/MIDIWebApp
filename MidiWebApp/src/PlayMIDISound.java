

import javax.sound.midi.*;


public class PlayMIDISound {

   public void playSingleSound(int keyNumber,int dynamics) throws MIDIException {
        
        try {
            Receiver receiver;
			receiver = MidiSystem.getReceiver();
	        ShortMessage message = new ShortMessage();
        	message.setMessage(ShortMessage.NOTE_ON, keyNumber, dynamics);
        	receiver.send(message, -1);
        }catch(MidiUnavailableException | InvalidMidiDataException ex) {
        	ex.printStackTrace();
        	throw new MIDIException();
        }
        	
    }
	    
}
