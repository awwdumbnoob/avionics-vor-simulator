import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JLabel;

/**
 * Just playing around with WindowBuilder
 * Tested out display of the radio's radial and station ID
 * 
 * @author Duane Leong
 */

public class VorWindow {

	private JFrame frame;
	private JTextField idDisplay;
	private JTextField radialDisplay;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VorWindow window = new VorWindow();
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
	public VorWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(31, 6, 134, 151);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setBounds(21, 121, 100, 30);
		panel.add(btnNewButton);
		
		idDisplay = new JTextField();
		idDisplay.setBounds(0, 81, 134, 28);
		panel.add(idDisplay);
		idDisplay.setText("ID");
		idDisplay.setColumns(10);
		
		radialDisplay = new JTextField();
		radialDisplay.setBounds(0, 16, 134, 28);
		panel.add(radialDisplay);
		radialDisplay.setText("Radial");
		radialDisplay.setColumns(10);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setBounds(0, 65, 61, 16);
		panel.add(idLbl);
		
		JLabel radialLbl = new JLabel("Radial");
		radialLbl.setBounds(0, 0, 61, 16);
		panel.add(radialLbl);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Radio radio = new Radio();
				radialDisplay.setText(Integer.toString(radio.getRadial()));
				idDisplay.setText(radio.getStationID());
			}
		});
	}
}
