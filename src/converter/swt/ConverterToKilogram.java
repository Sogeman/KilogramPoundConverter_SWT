package converter.swt;

public class ConverterToKilogram implements ConverterInterface {

	@Override
	public double convert(double pound) {
		return (pound/2.2046);
	}

}
