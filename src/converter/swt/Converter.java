package converter.swt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.junit.experimental.categories.Categories.ExcludeCategory;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Converter {

	private JFrame frame;
	private JTextField inputKilogram;
	private JTextField inputPound;
	DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Converter window = new Converter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Converter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		inputKilogram = new JTextField();
		inputKilogram.setToolTipText("maximal 2.147.483.647");
		inputKilogram.setBounds(10, 75, 143, 20);
		frame.getContentPane().add(inputKilogram);
		inputKilogram.setColumns(10);

		JLabel lblKilogrammpfundRechner = new JLabel("Kilogramm/Pfund Rechner");
		lblKilogrammpfundRechner.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKilogrammpfundRechner.setBounds(127, 11, 184, 26);
		frame.getContentPane().add(lblKilogrammpfundRechner);

		JLabel lblNewLabel = new JLabel("Kilogramm");
		lblNewLabel.setBounds(159, 78, 73, 14);
		frame.getContentPane().add(lblNewLabel);

		inputPound = new JTextField();
		inputPound.setToolTipText("maximal 2.147.483.647");
		inputPound.setColumns(10);
		inputPound.setBounds(10, 106, 143, 20);
		frame.getContentPane().add(inputPound);

		JLabel lblPfund = new JLabel("Pfund");
		lblPfund.setBounds(159, 109, 60, 14);
		frame.getContentPane().add(lblPfund);

		JLabel lblErgebnis = new JLabel("Ergebnis:");
		lblErgebnis.setBounds(10, 152, 80, 14);
		frame.getContentPane().add(lblErgebnis);

		JTextPane resultBox = new JTextPane();
		resultBox.setBounds(10, 177, 387, 20);
		frame.getContentPane().add(resultBox);

		JButton buttonConvert = new JButton("Berechnen");
		buttonConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String stringKilogram = inputKilogram.getText();
				String stringPound = inputPound.getText();
				
				df.setRoundingMode(RoundingMode.UP);

				String typeOfInput = returnTypeOfInput(stringKilogram, stringPound);
				String inputToConvert = "";
				try {
					inputToConvert = returnFilledInputString(stringKilogram, stringPound);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				double parsedInput = 0;

				try {
					parsedInput = parseInputToDouble(inputToConvert);
					
					ConverterLogic converter = new ConverterLogic();

					if (checkIfInputBiggerThanZero(parsedInput)) {
						if (checkIfInputUnderMaxValue(parsedInput)) {
							try {
								double result = converter.convert(parsedInput, typeOfInput);
								resultBox.setText(df.format(parsedInput) + " " + typeOfInput + " sind " + df.format(result) + " " + returnTypeOfOutput(stringKilogram, stringPound));
							} catch (Exception e) {
								resultBox.setText(e.getMessage());
							}
						} else {
							resultBox.setText("Input muss kleiner als MaxINT (" + Integer.MAX_VALUE + ") sein");
						}
					} else {
						resultBox.setText("Input muss größer als 0 sein");
					}
				} catch (NumberFormatException nfe) {
					resultBox.setText("Input muss Zahl sein");
					throw nfe;
				}

				inputKilogram.setText("");
				inputPound.setText("");
			}
			
			private String returnTypeOfOutput(String stringKilogram, String stringPound) {
				if (returnTypeOfInput(stringKilogram, stringPound) == "kg") {
					return "lb";
				} else {
					return "kg";
				}
			}

		});

		buttonConvert.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonConvert.setBounds(259, 89, 105, 23);
		frame.getContentPane().add(buttonConvert);
	}
	
	public String returnTypeOfInput(String stringKilogram, String stringPound) {
		if (stringKilogram.length() > 0) {
			return "kg";
		} else if (stringPound.length() > 0) {
			return "lb";
		}
		return null;
	}

	public boolean checkIfInputUnderMaxValue(double input) {
		if (input <= Integer.MAX_VALUE) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkIfInputBiggerThanZero(double input) {
		if (input > 0) {
			return true;
		} else {
			return false;
		}

	}

	public String returnFilledInputString(String input1, String input2) throws Exception {
		if (input1.length() > 0 && input2.length() > 0) {
			throw new Exception("only one input allowed");
		} else if (input1.length() > 0) {
			return input1;
		} else if (input2.length() > 0) {
			return input2;
		}
		throw new Exception("program error - shouldn't be reachable");
	}

	public double parseInputToDouble(String input) {
		return Double.parseDouble(input);
	}

}
