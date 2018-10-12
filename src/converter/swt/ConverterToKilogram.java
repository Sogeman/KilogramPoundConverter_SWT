package converter.swt;

public class ConverterToKilogram implements ConverterInterface {

	@Override
	public double convert(double pound) {
		return (pound/2.2046);
	}

	@Override
	public double setup(String pound) {
		try {
			double lb = Double.parseDouble(pound);
			if(lb <= Integer.MAX_VALUE && lb > 0) {
				double result = convert(lb);						
				return result;
			} else {
				return 0;
			}
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return 1;
	    }
	}


}
