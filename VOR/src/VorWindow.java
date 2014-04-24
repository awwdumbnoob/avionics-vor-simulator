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
 * @author Duane
 */
public class VorWindow {

	private JFrame frame;
	private JTextField idDisplay;
	private JTextField radialDisplay;

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
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Radio radio = new Radio();
				radialDisplay.setText(Integer.toString(radio.getRadial()));
				idDisplay.setText(radio.getStationID());
			}
		});
		btnNewButton.setBounds(200, 400, 100, 30);
		frame.getContentPane().add(btnNewButton);
		
		idDisplay = new JTextField();
		idDisplay.setText("ID");
		idDisplay.setBounds(178, 217, 134, 28);
		frame.getContentPane().add(idDisplay);
		idDisplay.setColumns(10);
		
		radialDisplay = new JTextField();
		radialDisplay.setText("Radial");
		radialDisplay.setBounds(178, 152, 134, 28);
		frame.getContentPane().add(radialDisplay);
		radialDisplay.setColumns(10);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setBounds(178, 201, 61, 16);
		frame.getContentPane().add(idLbl);
		
		JLabel radialLbl = new JLabel("Radial");
		radialLbl.setBounds(178, 136, 61, 16);
		frame.getContentPane().add(radialLbl);
	}
}
