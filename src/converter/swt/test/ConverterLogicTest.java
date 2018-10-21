package converter.swt.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import converter.swt.ConverterLogic;

public class ConverterLogicTest {

	@Test
	public void testConvertKiloToPound() throws Exception {
		// assign
		ConverterLogic converter = new ConverterLogic();
		List<Double> inputNumbers = new ArrayList<>();
		inputNumbers.add(1.0);
		inputNumbers.add(20.6);
		inputNumbers.add(359.8);
		//TODO add iterate over array for test
		
		List<Double> expectedNumbers = new ArrayList<>();
		expectedNumbers.add(2.21);
		expectedNumbers.add(45.42);
		expectedNumbers.add(793.22);
		
		// act & assert
		for (int i = 0; i < inputNumbers.size(); i++) {
			double actual = converter.convert(inputNumbers.get(i), "kilogram");
			assertEquals(expectedNumbers.get(i), actual, 0.01);
		}
	}
	
	@Test
	public void testConvertPoundToKilo() throws Exception {
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
			double actual = converter.convert(inputNumbers.get(i), "pound");
			assertEquals(expectedNumbers.get(i), actual, 0.01);
		}
	}
	
	@Test
	public void testConvertException() {
		fail("not implemented yet");
	}
}
