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
		inputKilogram.setToolTipText(" max 100 000 000");
		inputKilogram.setBounds(10, 75, 143, 20);
		frame.getContentPane().add(inputKilogram);
		inputKilogram.setColumns(10);

		JLabel lblKilogrammpfundRechner = new JLabel("Kilogramm/Pfund Rechner");
		lblKilogrammpfundRechner.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKilogrammpfundRechner.setBounds(127, 11, 184, 26);
		frame.getContentPane().add(lblKilogrammpfundRechner);

		JLabel lblNewLabel = new JLabel("Kilogramm");
		lblNewLabel.setToolTipText("erlaubt bis Max INT");
		lblNewLabel.setBounds(159, 78, 73, 14);
		frame.getContentPane().add(lblNewLabel);

		inputPound = new JTextField();
		inputPound.setToolTipText("erlaubt bis Max INT");
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

				String kilogramString = inputKilogram.getText();
				String poundString = inputPound.getText();

				double kilogramDouble = 0;
				try {
					kilogramDouble = parseInput(kilogramString);
				} catch (Exception e2) {
					resultBox.setText(error(-2.6983497359));
				}
				double poundDouble = 0;
				try {
					poundDouble = parseInput(poundString);
				} catch (Exception e2) {
					resultBox.setText(error(-2.6983497359));
				}

				df.setRoundingMode(RoundingMode.UP);

				if (kilogramDouble == -2.6983497359 || poundDouble == -2.6983497359) {
					resultBox.setText(error(-2.6983497359));
				}

				if (kilogramDouble > 0) {
					double result;
					try {
						result = setup(kilogramDouble, "kilogram");
						if (result != 0 && result != 1) {
							resultBox.setText(kilogramDouble + " kg sind " + df.format(result) + " lb");
						} else {

							resultBox.setText(error((int) result));
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (poundDouble > 0) {
					double result;
					try {
						result = setup(poundDouble, "pound");
						if (result != 0 && result != 1) {
							resultBox.setText(poundDouble + " lb sind " + df.format(result) + " kg");
						} else {

							resultBox.setText(error((int) result));
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (kilogramDouble <= 0 || poundDouble <= 0) {
					resultBox.setText(error(0));
				}

				inputKilogram.setText("");
				inputPound.setText("");
			}
		});

		buttonConvert.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonConvert.setBounds(259, 89, 105, 23);
		frame.getContentPane().add(buttonConvert);
	}

	public String error(double errorCode) {
		if (errorCode == 0) {
			return "Error - Input größer als Max INT oder kleiner als 1";
		} else if (errorCode == -2.6983497359) {
			return "Input muss Zahl sein";
		}
		return "Program error - contact admin";
	}

	public double setup(double input, String type) throws Exception {
		ConverterLogic converter = new ConverterLogic();
		if (input <= Integer.MAX_VALUE) {
			double result = converter.convert(input, type);
			return result;
		} else {
			return 0;
		}
	}

	public double parseInput(String input) throws Exception {
		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException nfe) {
			throw new Exception();
		}
	}

}
