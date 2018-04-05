package dkeep.gui;

import javax.swing.JFrame;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dkeep.logic.defenitions;
import dkeep.logic.character.Hero;
import dkeep.logic.character.Ogre;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MapEditor extends Graphic implements MouseListener {

	public JFrame frame;
	private WindowGame window;
	private JTextField textField;
	private JTextField textField_1;
	private int tamanho_icon=50;
	private String mouse_selected;
	Graphic painel;
	Hero hero= new Hero();
	ArrayList<Ogre> guards=new ArrayList<Ogre>();
	private JTextField textField_2;
	
	/**
	 * Create the application.
	 */
	public MapEditor(WindowGame window) {
		initialize();
		this.window=window;
	}
	
	//=======================
	//   ALL ACTION BELOW.
	//=======================

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 1111, 899);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 59, 150, 98, 93, 93, 92, 82, 127, 0};
		gbl_panel.rowHeights = new int[]{23, 30, 15, 42, 682};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1};
		gbl_panel.rowWeights = new double[]{0.0, 1, 1.0, 1.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblWeight = new JLabel("Width");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.EAST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 1;
		panel.add(lblWeight, gbc_lblWeight);
		
		textField = new JTextField();
		textField.setText("10");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateMap();
			}

			public void removeUpdate(DocumentEvent e) {
				updateMap();
			}

			public void insertUpdate(DocumentEvent e) {
				updateMap();
			}
		});
		panel.add(textField, gbc_textField);
		textField.setColumns(9);

		JLabel lblHero = new JLabel("Hero");
		GridBagConstraints gbc_lblHero = new GridBagConstraints();
		gbc_lblHero.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblHero.insets = new Insets(0, 0, 5, 5);
		gbc_lblHero.gridx = 3;
		gbc_lblHero.gridy = 1;
		panel.add(lblHero, gbc_lblHero);
		
		JLabel lblOgre = new JLabel("Ogre");
		GridBagConstraints gbc_lblOgre = new GridBagConstraints();
		gbc_lblOgre.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblOgre.insets = new Insets(0, 0, 5, 5);
		gbc_lblOgre.gridx = 4;
		gbc_lblOgre.gridy = 1;
		panel.add(lblOgre, gbc_lblOgre);
		
		JLabel lblWall = new JLabel("Wall");
		GridBagConstraints gbc_lblWall = new GridBagConstraints();
		gbc_lblWall.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblWall.insets = new Insets(0, 0, 5, 5);
		gbc_lblWall.gridx = 5;
		gbc_lblWall.gridy = 1;
		panel.add(lblWall, gbc_lblWall);
		
		JLabel lblDoor = new JLabel("Door");
		GridBagConstraints gbc_lblDoor = new GridBagConstraints();
		gbc_lblDoor.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDoor.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoor.gridx = 6;
		gbc_lblDoor.gridy = 1;
		panel.add(lblDoor, gbc_lblDoor);
		
		JLabel lblKey = new JLabel("Key");
		GridBagConstraints gbc_lblKey = new GridBagConstraints();
		gbc_lblKey.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblKey.gridx = 7;
		gbc_lblKey.gridy = 1;
		panel.add(lblKey, gbc_lblKey);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTH;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 8;
		gbc_lblName.gridy = 1;
		panel.add(lblName, gbc_lblName);
		
		JLabel lblNewLabel = new JLabel("Height");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setText("10");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(9);
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateMap();
			}

			public void removeUpdate(DocumentEvent e) {
				updateMap();
			}

			public void insertUpdate(DocumentEvent e) {
				updateMap();
			}
		});

		Graphic hero_icon = new Graphic();
		hero_icon.name=defenitions._hero_with_arm;
		GridBagConstraints gbc_hero_icon = new GridBagConstraints();
		gbc_hero_icon.gridheight = 2;
		gbc_hero_icon.insets = new Insets(0, 0, 5, 5);
		gbc_hero_icon.fill = GridBagConstraints.BOTH;
		gbc_hero_icon.gridx = 3;
		gbc_hero_icon.gridy = 2;
		panel.add(hero_icon, gbc_hero_icon);
		hero_icon.loadImages();
		hero_icon.printIcon(defenitions._hero_with_arm, this.tamanho_icon);
		
		Graphic ogre_icon = new Graphic();
		ogre_icon.name=defenitions._crazy_ogre;
		GridBagConstraints gbc_ogre_icon = new GridBagConstraints();
		gbc_ogre_icon.gridheight = 2;
		gbc_ogre_icon.insets = new Insets(0, 0, 5, 5);
		gbc_ogre_icon.fill = GridBagConstraints.BOTH;
		gbc_ogre_icon.gridx = 4;
		gbc_ogre_icon.gridy = 2;
		panel.add(ogre_icon, gbc_ogre_icon);
		ogre_icon.loadImages();
		ogre_icon.printIcon(defenitions._crazy_ogre, this.tamanho_icon);
		
		Graphic wall_icon = new Graphic();
		wall_icon.name=defenitions._wall;
		GridBagConstraints gbc_wall_icon = new GridBagConstraints();
		gbc_wall_icon.gridheight = 2;
		gbc_wall_icon.insets = new Insets(0, 0, 5, 5);
		gbc_wall_icon.fill = GridBagConstraints.BOTH;
		gbc_wall_icon.gridx = 5;
		gbc_wall_icon.gridy = 2;
		panel.add(wall_icon, gbc_wall_icon);
		wall_icon.loadImages();
		wall_icon.printIcon(defenitions._wall, this.tamanho_icon);
		
		Graphic door_icon = new Graphic();
		door_icon.name=defenitions._door;
		GridBagConstraints gbc_door_icon = new GridBagConstraints();
		gbc_door_icon.gridheight = 2;
		gbc_door_icon.insets = new Insets(0, 0, 5, 5);
		gbc_door_icon.fill = GridBagConstraints.BOTH;
		gbc_door_icon.gridx = 6;
		gbc_door_icon.gridy = 2;
		panel.add(door_icon, gbc_door_icon);
		door_icon.loadImages();
		door_icon.printIcon(defenitions._door, this.tamanho_icon);
		
		Graphic key_icon = new Graphic();
		key_icon.name=defenitions._lever;
		GridBagConstraints gbc_key_icon = new GridBagConstraints();
		gbc_key_icon.gridheight = 2;
		gbc_key_icon.insets = new Insets(0, 0, 5, 5);
		gbc_key_icon.fill = GridBagConstraints.BOTH;
		gbc_key_icon.gridx = 7;
		gbc_key_icon.gridy = 2;
		panel.add(key_icon, gbc_key_icon);
		key_icon.loadImages();
		key_icon.printIcon(defenitions._lever, this.tamanho_icon);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 8;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 8;
		gbc_btnSave.gridy = 3;
		panel.add(btnSave, gbc_btnSave);
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			
		});
		
		painel = new Graphic();
		painel.name="MAP";
		FlowLayout flowLayout = (FlowLayout) painel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		painel.setBackground(Color.WHITE);
		GridBagConstraints gbc_painel = new GridBagConstraints();
		gbc_painel.fill = GridBagConstraints.BOTH;
		gbc_painel.gridwidth = 9;
		gbc_painel.gridx = 1;
		gbc_painel.gridy = 4;
		panel.add(painel, gbc_painel);
		painel.addMouseListener(this);
		
		painel.loadImages();
		this.updateMap();
		
		hero_icon.addMouseListener(this);
		ogre_icon.addMouseListener(this);
		wall_icon.addMouseListener(this);
		door_icon.addMouseListener(this);
		key_icon.addMouseListener(this);
		
		hero.positionX=0;
		hero.positionY=0;
		
		consoleLog("Init finished.");
	}
	
	private void saveGameToFile()
	{
		
	}
	
	
	private void updateMap()
	{
		int x,y;
		if(!this.textField.getText().equals(""))
			x=Integer.parseInt(this.textField.getText());
		else
			x=0;
		if(!this.textField_1.getText().equals(""))
			y=Integer.parseInt(this.textField_1.getText());
		else
			y=0;
		if(x>1000)
			x=1000;
		if(y>1000)
			y=1000;
		painel.setBaseMap(x,y);
		painel.repaint();
	}
	
	private void consoleLog(String text) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().getName()!="MAP")
		{
			this.mouse_selected=e.getComponent().getName();
		}
		else
		{
			int j=e.getX()/this.size_image;
			int i=e.getY()/this.size_image;
			if(j>=painel.map_to_print.length || j<0 || i<0 || i>=painel.map_to_print[0].length || !painel.map_to_print[j][i].equals(defenitions._empty_cell))
			{
				return;
			}
			if(this.mouse_selected.equals(defenitions._hero_with_arm))
			{
				if(j>0 && j<painel.map_background.length-1 && i>0 && i<painel.map_background[0].length && painel.map_background[i][j].equals(defenitions._empty_cell))
				{
					if(this.guards.size()!=0)
					{
						for(Ogre g:this.guards)
						{
							if(g.positionX==i && g.positionY==j)
							{
								return;
							}
						}
						painel.map_to_print[hero.positionX][hero.positionY]=painel.map_background[hero.positionX][hero.positionY];
						hero.positionX=i;
						hero.positionY=j;
						painel.map_to_print[i][j]=this.mouse_selected;
						painel.repaint();
					}
					else
					{
						painel.map_to_print[hero.positionX][hero.positionY]=painel.map_background[hero.positionX][hero.positionY];
						hero.positionX=i;
						hero.positionY=j;
						painel.map_to_print[i][j]=this.mouse_selected;
						painel.repaint();
					}
					
				}
			}
			else if (this.mouse_selected.equals(defenitions._crazy_ogre)) {
				if (j > 0 && j < painel.map_background.length - 1 && i > 0 && i < painel.map_background[0].length && painel.map_background[i][j].equals(defenitions._empty_cell)) {
					Ogre novo=new Ogre(painel.map_to_print);
					novo.positionX=i;
					novo.positionY=j;
					this.guards.add(novo);
					painel.map_to_print[i][j]=this.mouse_selected;
					painel.repaint();
				}
			}
			else if (this.mouse_selected.equals(defenitions._wall) || this.mouse_selected.equals(defenitions._door) || this.mouse_selected.equals(defenitions._lever)) {
				if (j > 0 && j < painel.map_background.length - 1 && i > 0 && i < painel.map_background[0].length && painel.map_background[i][j].equals(defenitions._empty_cell)) {
					if(!painel.map_to_print[i][j].equals(defenitions._empty_cell))
						return;
					else
					{
						painel.map_to_print[i][j]=this.mouse_selected;
						painel.map_background[i][j]=this.mouse_selected;
						painel.repaint();
					}
				}
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
