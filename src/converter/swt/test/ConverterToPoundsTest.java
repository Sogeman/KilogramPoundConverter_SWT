package converter.swt.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import converter.swt.ConverterToPounds;

public class ConverterToPoundsTest {

	@Test
	public void testConvert() {
		// assign
		ConverterToPounds converter = new ConverterToPounds();
		List<Double> inputNumbers = new ArrayList<>();
		inputNumbers.add(1.0);
		inputNumbers.add(20.6);
		inputNumbers.add(359.8);
		
		List<Double> expectedNumbers = new ArrayList<>();
		expectedNumbers.add(2.21);
		expectedNumbers.add(45.42);
		expectedNumbers.add(793.22);
		
		// act & assert
		for (int i = 0; i < inputNumbers.size(); i++) {
			double actual = converter.convert(inputNumbers.get(i));
			assertEquals(expectedNumbers.get(i), actual, 0.01);
		}
	}

	@Test
	public void testSetupReturnResult() {
		// assign
		ConverterToPounds converter = new ConverterToPounds();
		String kilogram1 = "1";
		String kilogram2 = "2147483646.0";
		String kilogram3 = "2147483647.0";
		double expected1 = 2.21;
		double expected2 = 4734391026.022;
		double expected3 = 4734391028.227;
		// act
		double actual1 = converter.setup(kilogram1);
		double actual2 = converter.setup(kilogram2);
		double actual3 = converter.setup(kilogram3);
		// assert
		assertEquals(expected1, actual1, 0.01);
		assertEquals(expected2, actual2, 0.01);
		assertEquals(expected3, actual3, 0.01);
	}

	@Test
	public void testSetupReturn0() { //TODO 0, -1, maxint +1
		// assign
		ConverterToPounds converter = new ConverterToPounds();
		String kilogram1 = "2147483648"; //max int +1
		String kilogram2 = "0";
		double expected = 0;
		// act
		double actual1 = converter.setup(kilogram1);
		double actual2 = converter.setup(kilogram2);
		// assert
		assertEquals(expected, actual1, 0.01);
		assertEquals(expected, actual2, 0.01);

	}

	@Test
	public void testSetupReturn1() { // TODO change to exception test?
		// assign
		ConverterToPounds converter = new ConverterToPounds();
		String kilogram = "abc";
		double expected = 1;
		// act
		double actual = converter.setup(kilogram);
		// assert
		assertEquals(expected, actual, 0.01);
	}

}
