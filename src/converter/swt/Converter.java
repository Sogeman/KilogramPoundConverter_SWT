package converter.swt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
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
				double kilogramDouble = Double.parseDouble(kilogramString);
				String poundString = inputPound.getText();
				double poundDouble = Double.parseDouble(poundString);

				df.setRoundingMode(RoundingMode.UP);

				if (kilogramDouble > 0) {
					double result = setup(kilogramDouble, "kilogram");
					if (result != 0 && result != 1) {
						resultBox.setText(kilogramDouble + " kg sind " + df.format(result) + " lb");
					} else {
						try {
							resultBox.setText(error((int) result));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else if (poundDouble > 0) {
					double result = setup(poundDouble, "pound");
					if (result != 0 && result != 1) {
						resultBox.setText(poundDouble + " lb sind " + df.format(result) + " kg");
					} else {
						try {
							resultBox.setText(error((int) result));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					try {
						resultBox.setText(error(0));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				inputKilogram.setText("");
				inputPound.setText("");
			}
		});

		buttonConvert.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonConvert.setBounds(259, 89, 105, 23);
		frame.getContentPane().add(buttonConvert);
	}

	public String error(int errorCode) throws Exception {
		if (errorCode == 0) {
			return "Error - Input größer als Max INT oder kleiner als 1";
		} else if (errorCode == 1){
			return "Input muss Zahl sein";
		} else {
			throw new Exception("Programmfehlers, bitte Admin verständigen");
		}
	}

	public double setup(double input, String type) {
		ConverterLogic converter = new ConverterLogic();
		try {
		if (input <= Integer.MAX_VALUE) {
			double result = converter.convert(input, type);
			return result;
		} else {
			return 0;
		}
		} catch (NumberFormatException | NullPointerException nfe) {
			return 1;
		}
	}

}
