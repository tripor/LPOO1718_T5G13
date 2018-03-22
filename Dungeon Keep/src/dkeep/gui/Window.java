package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dkeep.logic.GameMap;
import dkeep.logic.Level1;
import dkeep.logic.Level2;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Font;

public class Window {

	private JFrame frame;
	private JTextField numberOfOgres;
	GameMap game;
	ArrayList<JButton> buttons=new ArrayList<JButton>();

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
	
	JTextArea lblContent;
	JLabel game_state_text;
	
	private String guard_type;
	int guard_count;
	
	// CUSTOM FUNCTIONs
	private boolean isNumeric(String s) {  
	    return s != null && s.matches("\\d+");  
	}
	
	
	//=======================
	//   ALL ACTION BELOW.
	//=======================
	
	private void enableButtons()
	{
		for(JButton it: this.buttons)
		{
			it.setEnabled(true);
		}
	}
	private void disableButtons()
	{
		for(JButton it: this.buttons)
		{
			it.setEnabled(false);
		}
	}
	private void startGame() {
		
		// NOTE: guard_type is [ Ogre | Rookie | Suspicious | Drunken ]
		//   defined at "JComboBox" section in this java document.
		game=new Level1(guard_type);
		game.printMap(lblContent);
		this.enableButtons();
	}
	private void moveHero(int movement)
	{
		this.disableButtons();
		int state=game.moveHeroTo(movement);
		if(state==1)
		{
			if(game.getCurrent_level().game_level.getValue()==1){
				game= new Level2(guard_count);
				game.printscreen();
				game.printMap(lblContent);
				this.enableButtons();
			}
			else{
				labelGameState("Victory");
			}
		}
		else if(state==2)
		{
			game.printscreen();
			labelGameState("Defeat. You were caught!!!");
		}
		else
		{
			this.enableButtons();
		}
		game.printMap(lblContent);
	}
	private void upPressed() {
		labelGameState("Hero moves UP");
		moveHero(1);
	}
	private void downPressed() {
		labelGameState("Hero moves DOWN");
		moveHero(2);
	}
	private void leftPressed() {
		labelGameState("Hero moves LEFT");
		moveHero(3);
	}
	private void rightPressed() {
		labelGameState("Hero moves RIGHT");
		moveHero(4);
	}
	private void exitPressed() {
		System.exit(0);
	}
	
	private void notInteger()
	{
		consoleLog("The number introduced is not a integer or it's not between 1 and 5.");
	}
	
	private void labelGameState(String text)
	{
		game_state_text.setText(text);
	}
	
	private void consoleLog(String text) {
		lblContent.setText(lblContent.getText() + "\n" + text);
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
		
		JComboBox<String> guardPersonality = new JComboBox<String>();
		guardPersonality.addItem("Rookie");
		guardPersonality.addItem("Drunken");
		guardPersonality.addItem("Suspicious");

//		guardPersonality.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				if(ItemEvent.SELECTED == e.getStateChange()){
//					// e.getItem().toString();
//                }
//			}
//        });
		
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
		
		lblContent = new JTextArea();
		lblContent.setFont(new Font("Courier New", Font.PLAIN, 13));
		panel.add(lblContent);
		
		JButton btnNewGame = new JButton("New Game");
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.anchor = GridBagConstraints.NORTH;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewGame.gridwidth = 2;
		gbc_btnNewGame.gridx = 3;
		gbc_btnNewGame.gridy = 2;
		
		btnNewGame.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e)
			  {
				  guard_type  = guardPersonality.getSelectedItem().toString();
				  if(isNumeric(numberOfOgres.getText()))
				  {
					  guard_count=Integer.parseInt(numberOfOgres.getText());
					  if(guard_count<=5&&guard_count>=1)
						  startGame();
					  else
						  notInteger();
				  }
				  else
				  {
					  notInteger();
				  }
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
		btnUp.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){upPressed();}});
		container.add(btnUp, gbc_btnUp);
		
		JButton btnLeft = new JButton("Left");
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.insets = new Insets(0, 0, 0, 5);
		gbc_btnLeft.gridx = 3;
		gbc_btnLeft.gridy = 5;
		btnLeft.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){leftPressed();}});
		container.add(btnLeft, gbc_btnLeft);
		
		JButton btnRight = new JButton("Right");
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.gridx = 4;
		gbc_btnRight.gridy = 5;
		btnRight.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){rightPressed();}});
		container.add(btnRight, gbc_btnRight);
		
		JButton btnDown = new JButton("Down");
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.fill = GridBagConstraints.CENTER;
		gbc_btnDown.gridwidth = 2;
		gbc_btnDown.gridx = 3;
		gbc_btnDown.gridy = 6;
		btnDown.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){downPressed();}});
		container.add(btnDown, gbc_btnDown);
		
		this.buttons.add(btnUp);
		this.buttons.add(btnDown);
		this.buttons.add(btnRight);
		this.buttons.add(btnLeft);
		this.disableButtons();
		
		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.anchor = GridBagConstraints.SOUTH;
		gbc_btnExit.insets = new Insets(0, 0, 5, 0);
		gbc_btnExit.gridwidth = 2;
		gbc_btnExit.gridx = 3;
		gbc_btnExit.gridy = 8;
		btnExit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){exitPressed();}});

		container.add(btnExit, gbc_btnExit);
		
		JLabel lblYouCanStart = new JLabel("You can start a new game.");
		GridBagConstraints gbc_lblYouCanStart = new GridBagConstraints();
		gbc_lblYouCanStart.anchor = GridBagConstraints.WEST;
		gbc_lblYouCanStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblYouCanStart.gridwidth = 5;
		gbc_lblYouCanStart.gridx = 0;
		gbc_lblYouCanStart.gridy = 9;
		game_state_text=lblYouCanStart;
		container.add(lblYouCanStart, gbc_lblYouCanStart);
	}

}
