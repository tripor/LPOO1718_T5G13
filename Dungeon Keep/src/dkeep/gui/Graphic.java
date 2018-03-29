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
	
	private String[][] map_to_print = new String[][] {{}};
	private String[][] map_background = new String[][] {{}};
	
	private static int size_image=50;
	
	private HashMap<String,BufferedImage> image= new HashMap<String,BufferedImage>();
	
	public Graphic() {
	}
	
	public void loadImages() 
	{
		try {
			image.put("wall",ImageIO.read(new File("sprites/wall.png")));
			image.put("floor",ImageIO.read(new File("sprites/floor.png")));
			image.put("hero",ImageIO.read(new File("sprites/hero.png")));
			image.put("ogre",ImageIO.read(new File("sprites/ogre.png")));
			image.put("key",ImageIO.read(new File("sprites/key.png")));
			image.put("club",ImageIO.read(new File("sprites/club.png")));
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < map_to_print.length; i++) {
			for (int j = 0; j < map_to_print[i].length; j++) {
				if (map_background[i][j].equals(defenitions._wall) || map_background[i][j].equals(defenitions._door)) {
					g.drawImage(image.get("wall"), j * size_image, i * size_image, size_image, size_image, this);
				} else if (map_background[i][j].equals(defenitions._empty_cell)
						|| map_background[i][j].equals(defenitions._opened_door)) {
					g.drawImage(image.get("floor"), j * size_image, i * size_image, size_image, size_image, this);
				} else if (map_background[i][j].equals(defenitions._lever)) {
					g.drawImage(image.get("floor"), j * size_image, i * size_image, size_image, size_image, this);
					g.drawImage(image.get("key"), j * size_image, i * size_image, size_image, size_image, this);
				} else {
					g.drawImage(image.get("floor"), j * size_image, i * size_image, size_image, size_image, this);
				}
				if (map_to_print[i][j].equals(defenitions._hero)
						|| map_to_print[i][j].equals(defenitions._hero_with_arm)) {
					g.drawImage(image.get("hero"), j * size_image, i * size_image, size_image, size_image, this);
				} else if (map_to_print[i][j].equals(defenitions._guard)
						|| map_to_print[i][j].equals(defenitions._crazy_ogre)
						|| map_to_print[i][j].equals(defenitions._ogre_at_key)
						|| map_to_print[i][j].equals(defenitions._ogre_stunned)) {
					g.drawImage(image.get("ogre"), j * size_image, i * size_image, size_image, size_image, this);
				} else if (map_to_print[i][j].equals(defenitions._club_at_key)
						|| map_to_print[i][j].equals(defenitions._hero_club)
						|| map_to_print[i][j].equals(defenitions._ogre_club)) {
					g.drawImage(image.get("club"), j * size_image, i * size_image, size_image, size_image, this);
				}
			}
		}
	}
	public String[][] getMap_to_print() {
		return map_to_print;
	}
	public void setMap_to_print(String[][] map_to_print) {
		this.map_to_print = map_to_print;
	}
	public String[][] getMap_background() {
		return map_background;
	}

	public void setMap_background(String[][] map_background) {
		this.map_background = map_background;
	}

}
