import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing cases
 * @author Duane
 */

public class TestCases {
	
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
	
	
	

}
