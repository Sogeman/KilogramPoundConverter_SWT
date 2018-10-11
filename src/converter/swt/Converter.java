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
		lblNewLabel.setToolTipText("erlaubt bis Max Int");
		lblNewLabel.setBounds(159, 78, 73, 14);
		frame.getContentPane().add(lblNewLabel);
		
		inputPound = new JTextField();
		inputPound.setToolTipText("erlaubt bis Max Int");
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
		
		JButton btnBerechnen = new JButton("Berechnen");
		btnBerechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String kilogram = inputKilogram.getText();
				String pound = inputPound.getText();
				df.setRoundingMode(RoundingMode.UP);
				
				if(kilogram.length() > 0) {
					ConverterToPounds converter = new ConverterToPounds();
					double kg = Double.parseDouble(kilogram);
					if(kg <= Integer.MAX_VALUE) {
						double result = converter.convert(kg);						
						resultBox.setText(kilogram + " kg sind " + df.format(result) + " lb");
					} else {
						// throw error
					}
				}
				else if(pound.length() > 0) {
					ConverterToKilogram converter = new ConverterToKilogram();
					double lb = Double.parseDouble(pound);
					if(lb <= Integer.MAX_VALUE) {
						double result = converter.convert(lb);						
						resultBox.setText(pound + " lb sind " + df.format(result) + " kg");
					} else {
						// throw error
					}
				}
				
				inputKilogram.setText("");
				inputPound.setText("");
			}
		});
		
		btnBerechnen.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBerechnen.setBounds(259, 89, 105, 23);
		frame.getContentPane().add(btnBerechnen);
	}
	
	
}
