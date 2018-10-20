package converter.swt.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import converter.swt.ConverterLogic;
import converter.swt.ConverterToPounds;

public class ConverterToKilogramsTest {

	@Test
	public void testConvert() {
		// assign
		ConverterLogic converter = new ConverterLogic();
		List<Double> inputNumbers = new ArrayList<>();
		inputNumbers.add(1.0);
		inputNumbers.add(20.6);
		inputNumbers.add(359.8);
		//TODO add iterate over array for test
		
		List<Double> expectedNumbers = new ArrayList<>();
		expectedNumbers.add(0.45);
		expectedNumbers.add(9.34);
		expectedNumbers.add(163.20);
		
		// act & assert
		for (int i = 0; i < inputNumbers.size(); i++) {
			double actual = converter.convert(inputNumbers.get(i));
			assertEquals(expectedNumbers.get(i), actual, 0.01);
		}
	}
	
	@Test
	public void testSetupReturn0() {
		// assign
		ConverterToPounds converter = new ConverterToPounds();
		String pound1 = "2147483648"; //max int +1
		String pound2 = "0";
		double expected = 0;
		// act
		double actual1 = converter.setup(pound1);
		double actual2 = converter.setup(pound2);
		// assert
		assertEquals(expected, actual1, 0.01);
		assertEquals(expected, actual2, 0.01);

	}

	@Test
	public void testSetupReturn1() {
		// assign
		ConverterToPounds converter = new ConverterToPounds();
		String pound = "abc";
		double expected = 1;
		// act
		double actual = converter.setup(pound);
		// assert
		assertEquals(expected, actual, 0.01);
	}

}
