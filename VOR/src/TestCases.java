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
		for (int i = 0; i < 10000; i++) {
			int radial = radio.getRadial();
			String id = radio.getStationID();
			assertTrue("Valid Station ID", id.matches("[A-Z]{3}"));
			assertTrue("Radial between 0 and 359", radial >= 0 && radial <=359 );
			radio.setRandom();
		}
	}
	
	/**
	 * Boundary testing for radial method.  Bounded at the extremes
	 */
	@Test
	public void testShortestRadial() {
		int[] radial = {-720, -360, -90, -1, 0, 1, 179, 180, 181, 359, 360};
		int[] expected = {0, 0, -90, -1, 0, 1, 179, 180, -179, -1, 0};
		
		for (int i = 0; i < radial.length; i++) {
			int answer = Calculations.shortestRadial(0, radial[i]);
			//System.out.println(radial[i] + ":" + expected[i] + ":" + answer);
			assertTrue("Resulting radial is " + answer,  answer == expected[i]);
		}
	}
	
	/**
	 * Boundary testing for needle angle calculation.  Bounded at the extremes.
	 */
	@Test
	public void testNeedleAngle() {
		int[] obs = {-175, -89, -81, -80, -10, -9, -8, 0, 8, 9, 10, 89, 100, 101, 188, 360};
		int[] expected = {-5, 10, 10, -10, -10, -10, -10, -10, -2, -1, 0, 10, 10, -10, -2, -10};
		
		for (int i = 0; i < obs.length; i++) {
			int answer = Calculations.needleAngle(10, obs[i]);
			System.out.println(obs[i] + ":" + expected[i] + ":" + answer);
			assertTrue("Expected radial is " + answer, answer == expected[i]);
		}
		
	}
	
	/**
	 * Test for goingTo calculation method.
	 */
	@Test
	public void testGoingTo() {
		int[] obs = {-100, -90, -89, -10, 0, 10, 89, 90, 100};
		boolean[] expected = {true, false, false, false, false, false, false, false, true};
		
		for (int i = 0; i < obs.length; i++) {
			boolean answer = Calculations.goingTo(0, obs[i]);
			assertTrue("Going to test should be: " + expected[i] + "\nAnswer was: " + answer,
					answer == expected[i]);
			System.out.println(answer);
		}
	}
	
}
