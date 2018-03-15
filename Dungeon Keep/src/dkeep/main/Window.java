package dkeep.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class Window {

	private JFrame frame;
	private JTextField numberOfOgres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
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
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		// Container
		JPanel container = new JPanel();
		container.setBorder(new EmptyBorder(15, 15, 15, 15));
		frame.getContentPane().add(container);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0,0,1};	// Double.MIN_VALUE
		gridBagLayout.rowWeights = new double[]{0,0,0,1,0,0,0,1,0};
		container.setLayout(gridBagLayout);
		
		// Items
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		GridBagConstraints gbc_lblNumberOfOgres = new GridBagConstraints();
		gbc_lblNumberOfOgres.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfOgres.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfOgres.gridx = 0;
		gbc_lblNumberOfOgres.gridy = 0;
		container.add(lblNumberOfOgres, gbc_lblNumberOfOgres);
		
		numberOfOgres = new JTextField();
		GridBagConstraints gbc_numberOfOgres = new GridBagConstraints();
		gbc_numberOfOgres.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfOgres.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberOfOgres.gridx = 1;
		gbc_numberOfOgres.gridy = 0;
		container.add(numberOfOgres, gbc_numberOfOgres);
		numberOfOgres.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		GridBagConstraints gbc_lblGuardPersonality = new GridBagConstraints();
		gbc_lblGuardPersonality.anchor = GridBagConstraints.WEST;
		gbc_lblGuardPersonality.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuardPersonality.gridx = 0;
		gbc_lblGuardPersonality.gridy = 1;
		container.add(lblGuardPersonality, gbc_lblGuardPersonality);
		
		JComboBox guardPersonality = new JComboBox();
		GridBagConstraints gbc_guardPersonality = new GridBagConstraints();
		gbc_guardPersonality.insets = new Insets(0, 0, 5, 5);
		gbc_guardPersonality.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardPersonality.gridx = 1;
		gbc_guardPersonality.gridy = 1;
		container.add(guardPersonality, gbc_guardPersonality);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 7;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		container.add(panel, gbc_panel);
		
		JLabel lblContent = new JLabel("Content");
		panel.add(lblContent);
		
		JButton btnNewGame = new JButton("New Game");
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.anchor = GridBagConstraints.NORTH;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewGame.gridwidth = 2;
		gbc_btnNewGame.gridx = 3;
		gbc_btnNewGame.gridy = 2;
		btnNewGame.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  lblContent.setText("New Game Started.");
		  }
		});
		container.add(btnNewGame, gbc_btnNewGame);
		
		JButton btnUp = new JButton("Up");
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.insets = new Insets(0, 0, 5, 0);
		gbc_btnUp.fill = GridBagConstraints.CENTER;
		gbc_btnUp.gridwidth = 2;
		gbc_btnUp.gridx = 3;
		gbc_btnUp.gridy = 4;
		container.add(btnUp, gbc_btnUp);
		
		JButton btnLeft = new JButton("Left");
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.insets = new Insets(0, 0, 0, 5);
		gbc_btnLeft.gridx = 3;
		gbc_btnLeft.gridy = 5;
		container.add(btnLeft, gbc_btnLeft);
		
		JButton btnRight = new JButton("Right");
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.gridx = 4;
		gbc_btnRight.gridy = 5;
		container.add(btnRight, gbc_btnRight);
		
		JButton btnDown = new JButton("Down");
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.fill = GridBagConstraints.CENTER;
		gbc_btnDown.gridwidth = 2;
		gbc_btnDown.gridx = 3;
		gbc_btnDown.gridy = 6;
		container.add(btnDown, gbc_btnDown);
		
		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.anchor = GridBagConstraints.SOUTH;
		gbc_btnExit.insets = new Insets(0, 0, 5, 0);
		gbc_btnExit.gridwidth = 2;
		gbc_btnExit.gridx = 3;
		gbc_btnExit.gridy = 8;
		container.add(btnExit, gbc_btnExit);
		
		JLabel lblYouCanStart = new JLabel("You can start a new game.");
		GridBagConstraints gbc_lblYouCanStart = new GridBagConstraints();
		gbc_lblYouCanStart.anchor = GridBagConstraints.WEST;
		gbc_lblYouCanStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblYouCanStart.gridwidth = 5;
		gbc_lblYouCanStart.gridx = 0;
		gbc_lblYouCanStart.gridy = 9;
		container.add(lblYouCanStart, gbc_lblYouCanStart);
	}

}
