package converter.swt;

public class ConverterLogic {

	public double convert(double input, String type) throws Exception {
		if (type == "kg") {
			return (input*2.2046);
		} else if (type == "lb") {
			return (input/2.2046);
		};
		throw new Exception("Program Error - contact admin");
	}

}
