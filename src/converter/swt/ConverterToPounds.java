package converter.swt;

public class ConverterToPounds implements ConverterInterface {

	@Override
	public double convert(double kilogram) {
		return (kilogram*2.2046);
	}

	@Override
	public double setup(String kilogram) {
		try {
			double kg = Double.parseDouble(kilogram);
			if(kg <= Integer.MAX_VALUE && kg > 0) {
				double result = convert(kg);						
				return result;
			} else {
				return 0;
			}
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return 1;
	    }
		
		
	}


}
