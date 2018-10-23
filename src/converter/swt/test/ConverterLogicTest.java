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
		inputNumbers.add(2147483646.0); // maxInt-1
		inputNumbers.add(2147483647.0); // maxInt
		
		List<Double> expectedNumbers = new ArrayList<>();
		expectedNumbers.add(2.2046);
		expectedNumbers.add(4734342445.97);
		expectedNumbers.add(4734342448.18);
		
		// act & assert
		for (int i = 0; i < inputNumbers.size(); i++) {
			double actual = converter.convert(inputNumbers.get(i), "kg");
			assertEquals(expectedNumbers.get(i), actual, 0.1);
		}
	}
	
	@Test
	public void testConvertPoundToKilo() throws Exception {
		// assign
		ConverterLogic converter = new ConverterLogic();
		List<Double> inputNumbers = new ArrayList<>();
		inputNumbers.add(1.0);
		inputNumbers.add(2147483646.0); // maxInt-1
		inputNumbers.add(2147483647.0); // maxInt
		
		List<Double> expectedNumbers = new ArrayList<>();
		expectedNumbers.add(0.45);
		expectedNumbers.add(974092191.781);
		expectedNumbers.add(974092192.234);
		
		// act & assert
		for (int i = 0; i < inputNumbers.size(); i++) {
			double actual = converter.convert(inputNumbers.get(i), "lb");
			assertEquals(expectedNumbers.get(i), actual, 0.01);
		}
	}
	
	@Test
	public void testConvertException() {
		try {
			ConverterLogic converter = new ConverterLogic();
			converter.convert(42, null);
			fail("Exception not thrown, test failed");
		} catch (Exception e) {
			String expectedMessage = "Program Error - contact admin";
			assertEquals(expectedMessage, e.getMessage());
		}
	}

}
