package control.dto;

public class SoundForm {
	
	private String rootKey;
	private int accidental;
	private String chordName;
	private int octave;
	
	public String getRootKey() {
		return this.rootKey;
	}
	
	public void setRootKey(String rootKey){
		this.rootKey = rootKey;
	}
	
	public int getAccidental() {
		return this.accidental;
	}
	
	public void setAccidental(int accidental) {
		this.accidental = accidental;
	}

	public String getChordName() {
		return this.chordName;
	}
	
	public void setChordName(String chordName) {
		this.chordName = chordName;
	}
	
	public int getOctave() {
		return this.octave;
	}
	
	public void setOctave(int octave) {
		this.octave = octave;
	}
	
}
