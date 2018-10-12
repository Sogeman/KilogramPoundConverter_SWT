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
		List<Double> expectedNumbers = new ArrayList<>();
		expectedNumbers.add(-1.0);
		expectedNumbers.add(0.0);
		expectedNumbers.add(1.0);
		expectedNumbers.add(2147483646.0);
		expectedNumbers.add(2147483647.0);
		expectedNumbers.add(2147483648.0);
		//TODO add iterate over array for test
		
		double kilogram = 1.0;
		double expected = 2.21;
		// act
		double actual = converter.convert(kilogram);
		// assert
		assertEquals(expected, actual, 0.01);
	}

	@Test
	public void testSetupReturnResult() {
		// assign
		ConverterToPounds converter = new ConverterToPounds();
		String kilogram = "1";
		double expected = 2.21;
		// act
		double actual = converter.setup(kilogram);
		// assert
		assertEquals(expected, actual, 0.01);
	}

	@Test
	public void testSetupReturn0() {
		// assign
		ConverterToPounds converter = new ConverterToPounds();
		String kilogram = "2147483648";
		double expected = 0;
		// act
		double actual = converter.setup(kilogram);
		// assert
		assertEquals(expected, actual, 0.01);

	}

	@Test
	public void testSetupReturn1() {
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
