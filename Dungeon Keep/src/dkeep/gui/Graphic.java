package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.defenitions;

@SuppressWarnings("serial")
public class Graphic extends JPanel  {
	
	protected String[][] map_to_print = new String[][] {{}};
	protected String[][] map_background = new String[][] {{}};
	
	protected String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected int size_image=50;
	
	private HashMap<String,BufferedImage> image= new HashMap<String,BufferedImage>();
	
	public Graphic() {
	}
	
	public void loadImages() 
	{
		try {
			image.put(defenitions._wall,ImageIO.read(new File("sprites/wall.png")));
			image.put(defenitions._empty_cell,ImageIO.read(new File("sprites/floor.png")));
			image.put(defenitions._door,ImageIO.read(new File("sprites/door_locked.png")));
			image.put(defenitions._opened_door,ImageIO.read(new File("sprites/floor.png")));
			image.put(defenitions._lever,ImageIO.read(new File("sprites/key.png")));
			image.put(defenitions._raw_key,ImageIO.read(new File("sprites/key_raw.png")));
			image.put(defenitions._hero,ImageIO.read(new File("sprites/hero.png")));
			image.put(defenitions._hero_at_key,ImageIO.read(new File("sprites/hero_with_key.png")));
			image.put(defenitions._guard,ImageIO.read(new File("sprites/guard.png")));
			image.put(defenitions._guard_sleep,ImageIO.read(new File("sprites/guard_sleep.png")));
			image.put(defenitions._crazy_ogre,ImageIO.read(new File("sprites/ogre.png")));
			image.put(defenitions._ogre_stunned,ImageIO.read(new File("sprites/ogre_stunned.png")));
			image.put(defenitions._ogre_at_key,ImageIO.read(new File("sprites/ogre.png")));
			image.put(defenitions._ogre_club,ImageIO.read(new File("sprites/club.png")));
			image.put(defenitions._club_at_key,ImageIO.read(new File("sprites/club.png")));
			image.put(defenitions._hero_club,ImageIO.read(new File("sprites/club.png")));
			image.put(defenitions._hero_with_arm,ImageIO.read(new File("sprites/hero.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < map_to_print.length; i++) {
			for (int j = 0; j < map_to_print[i].length; j++) {
				g.drawImage(image.get(map_background[i][j]), j * size_image, i * size_image, size_image, size_image, this);
				g.drawImage(image.get(map_to_print[i][j]), j * size_image, i * size_image, size_image, size_image, this);
			}
		}
	}
	public String[][] getMap_to_print() {
		return map_to_print;
	}
	public void setMap_to_print(String[][] map_to_print) {
		this.map_to_print= new String[map_to_print.length][map_to_print[0].length];
		for(int i=0;i<map_to_print.length;i++)
		{
			for(int j=0;j<map_to_print[0].length;j++)
			{
				this.map_to_print[i][j]=map_to_print[i][j];
			}
		}
	}
	public String[][] getMap_background() {
		return map_background;
	}

	public void setMap_background(String[][] map_background) {
		this.map_background = map_background;
	}

	public void setBaseMap(int width, int height)
	{
		this.map_to_print=new String[height][width];
		this.map_background=new String[height][width];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				if(i==0 || i == height-1)
				{
					map_to_print[i][j]="X";
					map_background[i][j]="X";
				}
				else if(j==0 || j == width-1)
				{
					map_to_print[i][j]="X";
					map_background[i][j]="X";
				}
				else
				{
					map_to_print[i][j] = " ";
					map_background[i][j] = " ";
				}
			}
		}
	}
	
	public void printIcon(String tipo,int tamanho)
	{
		this.size_image=tamanho;
		String[][] set = new String[1][1];
		set[0][0]=tipo;
		this.map_background=set;
		this.map_to_print=set;
		this.repaint();
	}
}
