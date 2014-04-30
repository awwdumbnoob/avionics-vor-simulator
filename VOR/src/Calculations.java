/**
 * Consists of static methods used for calculations in the VOR
 * @author Duane Leong
 */
public class Calculations {
	/**
	 * Normalizes the angle between 0-359
	 * ex. an angle of 400 degrees is the same as 40
	 * @return integer value of normalized angle
	 */
	public static int normalizeAngle(int angle) {
		return angle % 360;
	}
	
	/**
	 * Returns the difference between angle x and y
	 * @param x first angle
	 * @param y second angle
	 * @return difference between the two angles
	 */
	public static int difference(int x, int y) {
		return normalizeAngle(x-y);
		
	}
	
	/**
	 * Shortest rotation to get from current to desired
	 * @param current angle
	 * @param desired angle
	 * @return int shortest radial to get from current to desired
	 */
	public static int shortestRotation(int current, int desired) {
		int difference = difference(desired, current);
		while (difference < -180) {
			difference += 360;
		}
		while (difference > 180 ) {
			difference -= 360;
		}
		return difference;
	}
}


