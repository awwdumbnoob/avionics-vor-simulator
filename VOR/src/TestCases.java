import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing cases
 * @author Duane
 */

public class TestCases {
	/**
	 * Random testing for the test radio
	 */
	@Test
	public void testRadio() {
		Radio radio = new Radio();
		for (int i = 0; i < 1000; i++) {
			int radial = radio.getRadial();
			String id = radio.getStationID();
			assertTrue("Radial is within 0-359", radial >= 0 && radial <=359 );
			assertTrue("Station ID is valid", id.matches("[A-Z]{3}"));
			radio.setRandom();
		}
	}
	
	/**
	 * Test for calculation.  Bounded at the extremes
	 */
	@Test
	public void testCalculations() {
		int[] radial = {0, 180, 181, 359, 360};
		int[] expected = {0, 180, -179, -1, 0};
		
		for (int i = 0; i < radial.length; i++) {
			int answer = Calculations.shortestRotation(0, radial[i]);
			System.out.println(i + ":" + radial[i] + ":" + answer);
			assertTrue("Resulting radial is " + radial[i],  answer == expected[i]);
		}
	}
	
	
	

}
