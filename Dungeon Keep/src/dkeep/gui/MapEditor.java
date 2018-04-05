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

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dkeep.logic.GameMap;
import dkeep.logic.Level2;
import dkeep.logic.defenitions;
import dkeep.logic.character.Guard;
import dkeep.logic.character.Hero;
import dkeep.logic.character.Ogre;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MapEditor extends Graphic implements MouseListener {

	public JFrame frame;
	private WindowGame window;
	private JTextField textWidth;
	private JTextField textHeight;
	private JLabel info;
	private int tamanho_icon=50;
	private String mouse_selected = null;
	Graphic painel;
	Hero hero= new Hero();
	ArrayList<Guard> guards=new ArrayList<Guard>();
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
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{15, 0, 50, 50, 50, 50, 50, 50, 50, 0, 0, 15};
		gbl_panel.rowHeights = new int[]{15, 0, 0, 0, 0, 15};
		gbl_panel.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0, 1};
		gbl_panel.rowWeights = new double[]{0, 0, 0, 0, 1};
		panel.setLayout(gbl_panel);
		
		JLabel lblWeight = new JLabel("Width");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.EAST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 1;
		panel.add(lblWeight, gbc_lblWeight);
		
		textWidth = new JTextField();
		textWidth.setText("10");
		GridBagConstraints gbc_textWidth = new GridBagConstraints();
		gbc_textWidth.fill = GridBagConstraints.BOTH;
		gbc_textWidth.anchor = GridBagConstraints.WEST;
		gbc_textWidth.insets = new Insets(0, 0, 5, 5);
		gbc_textWidth.gridx = 2;
		gbc_textWidth.gridy = 1;
		textWidth.getDocument().addDocumentListener(new DocumentListener() {
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
		panel.add(textWidth, gbc_textWidth);
		textWidth.setColumns(9);

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
		
		JLabel lblClear = new JLabel("Clear");
		GridBagConstraints gbc_lblClear = new GridBagConstraints();
		gbc_lblClear.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblClear.insets = new Insets(0, 0, 5, 5);
		gbc_lblClear.gridx = 8;
		gbc_lblClear.gridy = 1;
		panel.add(lblClear, gbc_lblClear);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 9;
		gbc_lblName.gridy = 1;
		panel.add(lblName, gbc_lblName);
		
		info = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 10;
		gbc_label.gridy = 1;
		panel.add(info, gbc_label);
		
		JLabel lblHeight = new JLabel("Height");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 2;
		panel.add(lblHeight, gbc_lblHeight);
		
		textHeight = new JTextField();
		textHeight.setText("10");
		GridBagConstraints gbc_textHeight = new GridBagConstraints();
		gbc_textHeight.fill = GridBagConstraints.BOTH;
		gbc_textHeight.anchor = GridBagConstraints.WEST;
		gbc_textHeight.insets = new Insets(0, 0, 5, 5);
		gbc_textHeight.gridx = 2;
		gbc_textHeight.gridy = 2;
		panel.add(textHeight, gbc_textHeight);
		textHeight.setColumns(9);
		textHeight.getDocument().addDocumentListener(new DocumentListener() {
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
		hero_icon.printIcon(hero_icon.name, this.tamanho_icon);
		
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
		ogre_icon.printIcon(ogre_icon.name, this.tamanho_icon);
		
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
		wall_icon.printIcon(wall_icon.name, this.tamanho_icon);
		
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
		door_icon.printIcon(door_icon.name, this.tamanho_icon);
		
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
		key_icon.printIcon(defenitions._raw_key, this.tamanho_icon);
		
		Graphic clear_icon = new Graphic();
		clear_icon.name=defenitions._empty_cell;
		GridBagConstraints gbc_clear_icon = new GridBagConstraints();
		gbc_clear_icon.gridheight = 2;
		gbc_clear_icon.insets = new Insets(0, 0, 5, 5);
		gbc_clear_icon.fill = GridBagConstraints.BOTH;
		gbc_clear_icon.gridx = 8;
		gbc_clear_icon.gridy = 2;
		panel.add(clear_icon, gbc_clear_icon);
		clear_icon.loadImages();
		clear_icon.printIcon(clear_icon.name, this.tamanho_icon);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 9;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 9;
		gbc_btnSave.gridy = 3;
		panel.add(btnSave, gbc_btnSave);
		
		
		painel = new Graphic();
		painel.name="MAP";
		FlowLayout flowLayout = (FlowLayout) painel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		painel.setBackground(Color.WHITE);
		GridBagConstraints gbc_painel = new GridBagConstraints();
		gbc_painel.insets = new Insets(0, 0, 5, 5);
		gbc_painel.fill = GridBagConstraints.BOTH;
		gbc_painel.gridwidth = 10;
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
		clear_icon.addMouseListener(this);
		
		hero.positionX=0;
		hero.positionY=0;
		
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(hero.positionX==0 && hero.positionY==0)
				{
					InfoLog("Tem de inserir um heroi");
				}
				else if(textField_2.getText().equals(""))
				{
					InfoLog("Tem de inserir um nome para o mapa");
				}
				else
				{
					boolean key = false, door = false;
					for (int i = 0; i < painel.map_background.length; i++) {
						for (int j = 0; j < painel.map_background[i].length; j++) {
							if (painel.map_background[i][j].equals(defenitions._lever)) {
								key = true;
							} else if (painel.map_background[i][j].equals(defenitions._door)
									&& (i == 0 || i == painel.map_background.length - 1 || j == 0
											|| j == painel.map_background[i].length - 1)) {
								door = true;
							}
						}
					}
					if(key && door)
					{
						saveGameToFile();
						frame.setVisible(false);
						window.menuSetVisible(true);
					}
					else
						InfoLog("There needs to be at least one key and one door on the edge of the map");
				}
				
			}
			
		});
		consoleLog("Init finished.");
	}
	
	private void saveGameToFile()
	{
		GameMap novo=new Level2(1);
		novo.getGuards().clear();
		novo.setGuards(guards);
		novo.setCopied_map(new String[painel.map_background.length][painel.map_background[0].length]);
		for(int i=0;i<painel.map_background.length;i++)
		{
			for(int j=0;j<painel.map_background[i].length;j++)
			{
				novo.setCopied_map(painel.map_background[i][j], i, j);
			}
		}
		novo.setHero(this.hero);
		System.out.println(info.getText());
		window.gestor.writeGame(novo, this.textField_2.getText());
	}
	
	
	private void updateMap()
	{
		int x,y;
		if(!this.textWidth.getText().equals(""))
			x=Integer.parseInt(this.textWidth.getText());
		else
			x=0;
		if(!this.textHeight.getText().equals(""))
			y=Integer.parseInt(this.textHeight.getText());
		else
			y=0;
		if(x>1000)
			x=1000;
		if(y>1000)
			y=1000;
		painel.setBaseMap(x, y);
		painel.repaint();
	}
	
	private void InfoLog(String text) {
		this.info.setText(text);
	}
	private void consoleLog(String text) {
	}
	private void consolePrintMap(String[][] map) {

		int i, j;
		
		for(i=0; i<map.length; i++) {
			
			System.out.print("\n");
			
			for(j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {

		System.out.println(e.getX() + "," + e.getY());
		
		// TODO Auto-generated method stub
		if(e.getComponent().getName() != "MAP")
		{
			this.mouse_selected = e.getComponent().getName();
			// e.getComponent().getName() = defenitions.XXXXX;
		}
		else
		{
			int newX = e.getX()/this.size_image;
			int newY = e.getY()/this.size_image;
			
			if(this.mouse_selected != null) {
				System.out.println("put " + this.mouse_selected + " at (" + newX + "," + newY + ")");
				
				// _hero_with_arm => map_to_print
				// _crazy_ogre    => map_to_print
				// (other)        => map_to_print && map_background
				
				if(newX >= painel.map_to_print[0].length || newX < 0) {
					return;
					// X overflows.
				}
				if(newY >= painel.map_to_print.length || newY < 0) {
					return;
					// Y overflows.
				}
				
				if(this.mouse_selected.equals(defenitions._empty_cell)) {
					// allow at any location.
					
					
					// REMOVE HERO.
					if(newX==hero.positionX && newY==hero.positionY)
					{
						hero.positionX = 0;
						hero.positionY = 0;
					}
					
					// REMOVE OGRE.
					for(int index=0; index < this.guards.size(); index++) {
						Ogre g = (Ogre) this.guards.get(index);
						
						// NOTE: in all files, positionX is Y-on-screen; positionY is X-on-screen. That's a lovely mistake. :)
						if(g.positionX == newY
							&& g.positionY == newX) {
							
							this.guards.remove(index);
						}
					}
					
					// KEEP WALL
					if(newX == 0 || newX == painel.map_to_print[0].length-1 || newY == 0 || newY == painel.map_to_print.length-1) {

						// Cannot change `mouse_selected`, do operations here instead.
						
						painel.map_background[newY][newX] = defenitions._wall;
						
						// SET ALL VALUES TO map_to_print
						painel.map_to_print[newY][newX] = painel.map_background[newY][newX];
						painel.repaint();
						
						return;
					}
				}
				else if(this.mouse_selected.equals(defenitions._door)
					&& painel.map_to_print[newY][newX].equals(defenitions._wall)) {
					// for door, allow putting it on wall.
				}
				else if(!painel.map_to_print[newY][newX].equals(defenitions._empty_cell)) {
					return;
					// cell not empty.
				}
				
				if(this.mouse_selected.equals(defenitions._hero_with_arm)){
					
					// remove the heroes before.
					painel.map_to_print[hero.positionX][hero.positionY] = painel.map_background[hero.positionX][hero.positionY];
					
					// NOTE: in all files, positionX is Y-on-screen; positionY is X-on-screen. That's a lovely mistake. :)
					hero.positionX = newY;
					hero.positionY = newX;
					
				}
				else if(this.mouse_selected.equals(defenitions._crazy_ogre)) {
					
					Ogre novo = new Ogre(painel.map_to_print);
					novo.positionX = newY;
					novo.positionY = newX;
					this.guards.add(novo);
				}
				else {
					painel.map_background[newY][newX] = this.mouse_selected;
				}
				
				// SET ALL VALUES TO map_to_print
				painel.map_to_print[newY][newX] = this.mouse_selected;
				painel.repaint();
			}
			else {
				System.out.println("th = " + newX + "," + newY);
			}
			
			
//			if(j>=painel.map_to_print.length || j<0 || i<0 || i>=painel.map_to_print[0].length || !painel.map_to_print[j][i].equals(defenitions._empty_cell))
//			{
//				return;
//			}
//			if(this.mouse_selected.equals(defenitions._hero_with_arm))
//			{
//				if(j>0 && j<painel.map_background.length-1 && i>0 && i<painel.map_background[0].length && painel.map_background[i][j].equals(defenitions._empty_cell))
//				{
//					if(this.guards.size()!=0)
//					{
//						for(Ogre g:this.guards)
//						{
//							if(g.positionX==i && g.positionY==j)
//							{
//								return;
//							}
//						}
//						painel.map_to_print[hero.positionX][hero.positionY]=painel.map_background[hero.positionX][hero.positionY];
//						hero.positionX=i;
//						hero.positionY=j;
//						painel.map_to_print[i][j]=this.mouse_selected;
//						painel.repaint();
//					}
//					else
//					{
//						painel.map_to_print[hero.positionX][hero.positionY]=painel.map_background[hero.positionX][hero.positionY];
//						hero.positionX=i;
//						hero.positionY=j;
//						painel.map_to_print[i][j]=this.mouse_selected;
//						painel.repaint();
//					}
//					
//				}
//			}
//			else if (this.mouse_selected.equals(defenitions._crazy_ogre)) {
//				if (j > 0 && j < painel.map_background.length - 1 && i > 0 && i < painel.map_background[0].length && painel.map_background[i][j].equals(defenitions._empty_cell)) {
//					Ogre novo=new Ogre(painel.map_to_print);
//					novo.positionX=i;
//					novo.positionY=j;
//					this.guards.add(novo);
//					painel.map_to_print[i][j]=this.mouse_selected;
//					painel.repaint();
//				}
//			}
//			else if (this.mouse_selected.equals(defenitions._wall) || this.mouse_selected.equals(defenitions._door) || this.mouse_selected.equals(defenitions._lever)) {
//				if (j > 0 && j < painel.map_background.length - 1 && i > 0 && i < painel.map_background[0].length && painel.map_background[i][j].equals(defenitions._empty_cell)) {
//					if(!painel.map_to_print[i][j].equals(defenitions._empty_cell))
//						return;
//					else
//					{
//						painel.map_to_print[i][j]=this.mouse_selected;
//						painel.map_background[i][j]=this.mouse_selected;
//						painel.repaint();
//					}
//				}
//			}

//			System.out.println("\n\n>> MAP BG");
//			consolePrintMap(painel.map_background);
//			System.out.println("\n\n>> MAP PRINT");
//			consolePrintMap(painel.map_to_print);
			
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
