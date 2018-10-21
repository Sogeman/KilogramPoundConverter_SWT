package converter.swt;

public class ConverterLogic {

	public double convert(double input, String type) throws Exception {
		if (type == "kilogram") {
			return (input*2.2046);
		} else if (type == "pound") {
			return (input/2.2046);
		};
		throw new Exception("Program Error - contact admin");
	}

}
