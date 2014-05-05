import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.border.LineBorder;

/**
 * Bult basic layout using WindowBuilder and adjusted accordingly
 * VOR GUI interface.  Simulates a VOR using a radio that generates
 * a random radial to be intercepted.
 * @author Duane Leong
 */

public class VorWindow {

	private JFrame frame;
	private JTextField obs;
	private Vor vor;
	private JTextField vorOutput;
	private JTextField stationID;
	private JTextField signalOutput;
	private JLabel toLabel;
	private JLabel fromLabel;
	private BufferedImage background;
	

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
		vor = new Vor();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 601, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Left Panel Code
		 */
		//Add left panel
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(Color.BLACK));
		leftPanel.setBounds(0, 20, 201, 378);
		frame.getContentPane().add(leftPanel);
		leftPanel.setLayout(null);
		
		//OBS text field
		obs = new JTextField();
		obs.setHorizontalAlignment(SwingConstants.CENTER);
		obs.setText(Integer.toString(vor.getObs()));
		obs.setBounds(0, 150, 200, 30);
		obs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int radial = Integer.parseInt(obs.getText());
					vor.setObs(radial);
					updateValues();
				} catch (Exception e) {
					updateValues();
				}
			}
		});
		leftPanel.add(obs);
		obs.setColumns(10);
		
		//OBS label
		JLabel obsLabel = new JLabel("OBS");
		obsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		obsLabel.setBounds(70, 135, 61, 16);
		leftPanel.add(obsLabel);
		
		//left -5
		JButton left5 = new JButton("<<");
		left5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vor.rotateObs(-5);
				updateValues();
			}
		});
		left5.setBounds(0, 180, 50, 30);
		leftPanel.add(left5);
		
		//left -1
		JButton left1 = new JButton("<");
		left1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vor.rotateObs(-1);
				updateValues();
			}
		});
		left1.setBounds(50, 180, 50, 30);
		leftPanel.add(left1);
		
		//right +5
		JButton right5 = new JButton(">>");
		right5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vor.rotateObs(5);
				updateValues();
			}
		});
		right5.setBounds(150, 180, 50, 30);
		leftPanel.add(right5);
		
		//right +1
		JButton right1 = new JButton(">");
		right1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vor.rotateObs(1);
				updateValues();
			}
		});
		right1.setBounds(100, 180, 50, 30);
		leftPanel.add(right1);
		
		/**
		 * Right panel code
		 */
		//right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightPanel.setBounds(201, 20, 400, 378);
		frame.getContentPane().add(rightPanel);
		rightPanel.setLayout(null);
		
		//temporary output
		vorOutput = new JTextField();
		vorOutput.setHorizontalAlignment(SwingConstants.CENTER);
		vorOutput.setText(Integer.toString(vor.getNeedleAngle()));
		vorOutput.setBounds(129, 275, 150, 30);
		vorOutput.setEditable(false);
		rightPanel.add(vorOutput);
		vorOutput.setColumns(10);
		
		//StationID label
		JLabel lblStationId = new JLabel("Station ID");
		lblStationId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStationId.setBounds(50, 35, 67, 16);
		rightPanel.add(lblStationId);
		
		//StationID display
		stationID = new JTextField();
		stationID.setHorizontalAlignment(SwingConstants.CENTER);
		stationID.setText(vor.getStationID());
		stationID.setEditable(false);
		stationID.setColumns(10);
		stationID.setBounds(129, 28, 150, 30);
		rightPanel.add(stationID);
		
		//Signal label
		JLabel lblSignal = new JLabel("Signal");
		lblSignal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSignal.setBounds(56, 323, 61, 16);
		rightPanel.add(lblSignal);
		
		//Signal display
		signalOutput = new JTextField();
		signalOutput.setHorizontalAlignment(SwingConstants.CENTER);
		if (vor.getSignal()) {
			signalOutput.setText("Good");
		}
		else {
			signalOutput.setText("Bad");
		}
		signalOutput.setEditable(false);
		signalOutput.setColumns(10);
		signalOutput.setBounds(129, 317, 150, 30);
		rightPanel.add(signalOutput);
		
		//TO label
		toLabel = new JLabel("TO");
		toLabel.setHorizontalAlignment(SwingConstants.CENTER);
		toLabel.setBounds(312, 94, 61, 16);
		rightPanel.add(toLabel);
		//FROM label
		fromLabel = new JLabel("FROM");
		fromLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fromLabel.setBounds(312, 197, 61, 16);
		rightPanel.add(fromLabel);
		//Displays correct information
		if(vor.getSignal()) {
			if (vor.goingTo()) {
				toLabel.setEnabled(true);
				fromLabel.setEnabled(false);
			}
			else {
				toLabel.setEnabled(false);
				fromLabel.setEnabled(true);
			}
		}
		else {
			toLabel.setEnabled(false);
			fromLabel.setEnabled(false);
		}


		
		/**
		 * Draws Needle in it's own panel
		 */
		//loads bg image
		try {
			background = ImageIO.read(new File ("Grid.png"));
		} catch (IOException e) {
			//e.printStackTrace();
		}
		//draws the background and needle
		JPanel needlePanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background, 0, 0, null);
				g.setColor(Color.green);
				g.drawLine(100, 0, 100+(vor.getNeedleAngle()*10), 200);
			}
		};
		needlePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		needlePanel.setBounds(100, 63, 200, 200);
		rightPanel.add(needlePanel);

		/**
		 * Menu Bar Code
		 */
		//Menu Bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 600, 20);
		frame.getContentPane().add(menuBar);
		
		//File Menu
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		//New Button
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vor = new Vor();
				updateValues();
			}
		});
		mnFile.add(mntmNew);
		
		//Quit Button
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
		
	}
	
	
	public void updateValues() {
		obs.setText(Integer.toString(vor.getObs()));
		vorOutput.setText(Integer.toString(vor.getNeedleAngle()));
		stationID.setText(vor.getStationID());
		
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		
		//checks for signal
		if (vor.getSignal()) {
			signalOutput.setText("Good");
		}
		else {
			signalOutput.setText("Bad");
		}
		
		//TO and FROM label check
		if(vor.getSignal()) {
			if (vor.goingTo()) {
				toLabel.setEnabled(true);
				fromLabel.setEnabled(false);
			}
			else {
				toLabel.setEnabled(false);
				fromLabel.setEnabled(true);
			}
		}
		else {
			toLabel.setEnabled(false);
			fromLabel.setEnabled(false);
		}
	}
}
