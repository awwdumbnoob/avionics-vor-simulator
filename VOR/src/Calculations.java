/**
 * Consists of static methods used for calculations in a simulated VOR
 * @author Duane Leong
 */
public class Calculations {
	/**
	 * Normalizes the angle between 0-359
	 * ex. an angle of 400 degrees is the same as 40
	 * @return integer value of normalized angle
	 */
	public static int normalizeAngle(int angle) {
		int normalized = angle % 360;
		while (normalized < 0) {
			normalized += 360;
		}
		return normalized;
	}
	
	/**
	 * Returns the difference between angle x and y
	 * @param x first angle
	 * @param y second angle
	 * @return difference between the two angles
	 */
	public static int difference(int x, int y) {
		return x-y;
	}
	
	/**
	 * Shortest rotation to get from current to desired
	 * @param current angle
	 * @param desired angle
	 * @return int shortest radial to get from current to desired
	 */
	public static int shortestRadial(int current, int desired) {
		int difference = difference(desired, current);
		
		while (difference < -180) {
			difference += 360;
		}
		while (difference > 180 ) {
			difference -= 360;
		}
		return difference;
		
	}
	
	/**
	 * Calculates the angle of the needle for the GUI
	 * @param obs angle of the obs
	 * @param intercepted angle from the radio
	 * @return int radial value of the needle
	 */
	public static int needleAngle(int intercepted, int obs) {
		
		int rotation = shortestRadial(intercepted, obs);;
		
		//checks for the case of going TO
		if (goingTo(intercepted, obs)) {
			rotation = shortestRadial(intercepted-180, obs);
		}

		//clamps rotation between -10 and 10 degrees
		int clampedRotation = Math.max(-10, Math.min(10, rotation));
		
		return clampedRotation;		
	}
	
	/**
	 * Tests if the angle is going TO the obs radial or coming FROM obs radial
	 * 
	 * Logic: If the rotation value is within +/- 90, we should get a FROM signal, otherwise
	 * we should get a TO signal.
	 * 
	 * @param intercepted radial intercepted from radio
	 * @param obs radial that is desired
	 * @return True if the radial is going TO, false if coming FROM
	 */
	public static boolean goingTo(int intercepted, int obs){
		int rotation = Calculations.shortestRadial(intercepted, obs);
		return (Math.abs(rotation) > 90);
	}
	
	/**
	 * Checks if the VOR is "abeam" which causes a bad signal.
	 * "Abeam" is defined as within 1 degree of 90 degrees of either side of the
	 * desired radial (obs).
	 * @param intercepted radial intercepted from radio
	 * @param obs radial that is desired
	 * @return True if the VOR is abeam, false otherwise
	 */
	public static boolean checkAbeam(int intercepted, int obs) {
		int firstRadial = Calculations.shortestRadial(obs, intercepted);
		int oppositeRadial = Calculations.shortestRadial(obs+180, intercepted);
		
		int firstDifferenceTo90 = Math.abs(Calculations.difference(90, firstRadial));
		int oppositeDifferenceTo90 = Math.abs(Calculations.difference(90, oppositeRadial));
		
		return (firstDifferenceTo90 <= 1) || (oppositeDifferenceTo90 <= 1);
	}
 }


