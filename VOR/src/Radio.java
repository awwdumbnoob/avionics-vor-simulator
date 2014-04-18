/**
 * Simulated radio for a VOR simulator
 * @author Duane
 *
 */
public class Radio {
	
	private String stationID; //string representation of morse code
	private int radial; //radial integer between 0-360
	
	//default constructor
	public Radio() {
		this.stationID = "";
		this.radial = 0;
	}
	
	public Radio(String stationID, int radial) {
		this.stationID = stationID;
		this.radial = radial;
	}
	
	public String getStationID() {
		return stationID;
	}
	
	public int getRadial() {
		return radial;
	}
}
