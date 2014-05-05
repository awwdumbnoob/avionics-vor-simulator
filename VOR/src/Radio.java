import java.util.Random;

/**
 * Simulated radio for a VOR simulator
 * @author Duane Leong
 */

public class Radio {
	
	private String stationID; //string representation of morse code
	private int radial; //radial integer between 0-360
	
	/**
	 * Default Constructor, sets the ID and radial as random values
	 */
	public Radio() {
		this.setRandom();
	}
	
	/**
	 * Used to reset stationID and radial to random values
	 */
	public void setRandom() {
		this.randomStationID();
		this.randomRadial();
	}
	
	/**
	 * Used for testing to set the values to specific values
	 * @param stationID
	 * @param radial
	 */
	public Radio(String stationID, int radial) {
		this.stationID = stationID;
		this.radial = radial;
	}
	
	/**
	 * Sets the ID as a random 3 character ID
	 */
	public void randomStationID() {
		Random random = new Random();
		StringBuilder id = new StringBuilder();
		id.append((char)(random.nextInt(26) + 'A'));
		id.append((char)(random.nextInt(26) + 'A'));
		id.append((char)(random.nextInt(26) + 'A'));
		this.stationID = id.toString();
	}
	
	/**
	 * Sets the radial as a random radial between 0-359
	 */
	public void randomRadial() {
		Random random = new Random();
		this.radial = random.nextInt(359);
	} 

	/**
	 * Gives the station ID as a String
	 * @return Station ID as a String
	 */
	public String getStationID() {
		return this.stationID;
	}
	
	/**
	 * Gives the radial as an integer
	 * @return radial as an integer between 0-359
	 */
	public int getRadial() {
		return this.radial;
	}
}
