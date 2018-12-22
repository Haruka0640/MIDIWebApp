package basicservlet;

class SoundDTO {

	private String keyName;
	private int keyNumber;
	private int dynamics;
	private int length;
	
	public String getKeyName() {
		return keyName;
	}
	
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	public int getKeyNumber() {
		return keyNumber;
	}
	
	public void setKeyNumber(int keyNumber) {
		this.keyNumber = keyNumber;
	}
	
	public int getDynamics() {
		return dynamics;
	}
	
	public void setDynamics(int dynamics) {
		this.dynamics = dynamics;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
}
