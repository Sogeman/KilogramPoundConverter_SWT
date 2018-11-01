package converter.swt.test;

import static org.junit.Assert.*;

import org.junit.Test;

import converter.swt.Converter;

public class ConverterTest {

	Converter converter = new Converter();
	
	@Test
	public void testReturnTypeOfInput() {
		// assign
		String inputKilogram = "234";
		String inputPound = "";
		String expected = "kg";
		// act
		String actual = converter.returnTypeOfInput(inputKilogram, inputPound);
		// assert
		assertEquals(expected, actual);
	}

	@Test
	public void testReturnFilledInputString() throws Exception {
		// assign
		String input1 = "";
		String input2 = "123";
		String expected = "123";
		// act
		String actual = converter.returnFilledInputString(input1, input2);
		// assert
		assertEquals(expected, actual);
	}
	
	@Test(expected = Exception.class) // Test remains but function is depracated
	public void testReturnFilledInputStringDoubleInput() throws Exception {
		// assign
		String input1 = "456";
		String input2 = "123";
		// act & assert
		converter.returnFilledInputString(input1, input2);
		fail("exception not thrown");		
	}

	@Test
	public void testParseInputToDouble() {
		// assign
		String input = "245.45";
		double expected = 245.45;
		// act
		double actual = converter.parseInputToDouble(input);
		// assert
		assertEquals(expected, actual, 0.01);
	}

	@Test
	public void testCheckIfInputBiggerThan0() {
		// assign
		double input = 245.45;
		boolean expected = true;
		// act
		boolean actual = converter.checkIfInputBiggerThanZero(input);
		// assert
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckIfInputUnderMaxValue() {
		// assign
		double input = 2147483648.0;
		boolean expected = false;
		// act
		boolean actual = converter.checkIfInputUnderMaxValue(input);
		// assert
		assertEquals(expected, actual);
	}

	@Test(expected = NumberFormatException.class)
	public void testNumberFormatExceptionThrown() {
		// assign
		String input = "abc";
		// act & assert
		converter.parseInputToDouble(input);
		fail("no number format exception thrown");
	}

}
