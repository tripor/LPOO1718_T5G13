package dkeep.gui;

//import java.awt.EventQueue;

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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Font;

@SuppressWarnings("serial")
public class PlayArea extends Graphic implements KeyListener, MouseListener {

	public JFrame frame;
	private JTextField numberOfOgres;
	GameMap game;
	ArrayList<JButton> buttons=new ArrayList<JButton>();
	Graphic panel;
	private WindowGame window;
	
	private boolean paused=false;
	/**
	 * Create the application.
	 */
	public PlayArea(WindowGame window) {
		initialize();
		this.window=window;
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
		this.paused=false;
	}
	private void disableButtons()
	{
		for(JButton it: this.buttons)
		{
			it.setEnabled(false);
		}
		this.paused=true;
	}
	private void startGame() {
		
		// NOTE: guard_type is [ Ogre | Rookie | Suspicious | Drunken ]
		//   defined at "JComboBox" section in this java document.
		this.consoleClear();
		game=new Level1(guard_type);
		panel.setMap_to_print(game.getMap());
		panel.setMap_background(game.getCopied_map());
		panel.loadImages();
		panel.repaint();
		this.enableButtons();
		panel.addKeyListener(this);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addMouseListener(this);
	}
	private void moveHero(int movement)
	{
		this.disableButtons();
		int state=game.moveHeroTo(movement);
		if(state==1)
		{
			if(game.getCurrent_level().game_level.getValue()==1){
				if(this.guard_count==0)
				{
					game=new Level2(1);
					window.gestor.setGame(window.selected, game);
				}
				else
					game= new Level2(guard_count);
				this.enableButtons();
			}
			else{
				labelGameState("Victory");
			}
		}
		else if(state==2)
		{
			labelGameState("Defeat. You were caught!!!");
		}
		else
		{
			this.enableButtons();
		}
		panel.setMap_to_print(game.getMap());
		panel.setMap_background(game.getCopied_map());
		panel.repaint();
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
		window.playSetVisible(false);
		window.menuSetVisible(true);
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
	
	private void consoleClear() {
		lblContent.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 800, 650);
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
		numberOfOgres.setText("");
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
		
		panel = new Graphic();
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
					  if(guard_count<=5&&guard_count>=0)
						  startGame();
					  else
						  notInteger();
				  }
				  else if(numberOfOgres.getText().equals(""))
				  {
					  guard_count=0;
					  startGame();
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
		gbc_btnRight.insets = new Insets(0, 0, 5, 5);
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

	private boolean pressed=false;
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(pressed==false && !this.paused)
		{
			int key =e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
	            this.leftPressed();
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            this.rightPressed();
	        }

	        if (key == KeyEvent.VK_UP) {
	            this.upPressed();
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            this.downPressed();
	        }
		}
		pressed=true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		pressed=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Dimension i = panel.getSize();
		int x=panel.getX();
		double right_x=x+i.getWidth();
		int y=panel.getY();
		double bottom_y=y+i.getHeight();
		if(x<arg0.getX() && arg0.getX()<right_x && y<arg0.getY() && arg0.getY()<bottom_y)
		{
			panel.requestFocusInWindow();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
