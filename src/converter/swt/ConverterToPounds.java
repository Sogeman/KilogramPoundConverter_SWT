package converter.swt;

public class ConverterToPounds implements ConverterInterface {

	@Override
	public double convert(double kilogram) {
		return (kilogram*2.2046);
	}

}
