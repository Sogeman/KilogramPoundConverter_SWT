package converter.swt.test;

import static org.junit.Assert.*;

import org.junit.Test;

import converter.swt.Converter;
import converter.swt.ConverterLogic;

public class ConverterTest {

	@Test
	public void testError() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetupReturn0() throws Exception{
		// assign
		Converter converter = new Converter();
		double pound1 = 2147483648.0; // max int +1
		double pound2 = 0.0;
		double expected = 0;
		// act
		double actual1 = converter.setup(pound1, "pound");
		double actual2 = converter.setup(pound2, "pound");
		// assert
		assertEquals(expected, actual1, 0.01);
		assertEquals(expected, actual2, 0.01);
	}

	@Test
	public void testParseInput() throws Exception {
		fail("not implemented yet");
		// assign
		Converter converter = new Converter();
		String kilogram = "abc";
		double expected = 1; //exception expected Fix code!!
		// act
		double actual = converter.parseInput(kilogram);
		// assert
		assertEquals(expected, actual, 0.01);
	}

}
