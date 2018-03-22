package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.defenitions;

@SuppressWarnings("serial")
public class Graphic extends JPanel implements MouseListener, MouseMotionListener, KeyListener  {
	
	private String[][] map_to_print = new String[][] {{}};
	
	private BufferedImage image;
	public Graphic() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		try {
			image = ImageIO.read(new File("image name and path"));
		} catch (IOException ex) {
			// handle exception...
		}
	}
	
	
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		for (int i = 0; i < map_to_print.length; i++) {
			for (int j = 0; j < map_to_print[i].length; j++) {
				if(map_to_print[i][j].equals(defenitions._wall))
				{
					g.setColor(Color.BLUE);
				}
				else if(map_to_print[i][j].equals(defenitions._empty_cell))
				{
					g.setColor(Color.WHITE);
				}
				else if(map_to_print[i][j].equals(defenitions._hero))
				{
					g.setColor(Color.RED);
				}
				else if(map_to_print[i][j].equals(defenitions._guard))
				{
					g.setColor(Color.GREEN);
				}
				else if(map_to_print[i][j].equals(defenitions._door))
				{
					g.setColor(Color.YELLOW);
				}
				g.fillRect(j*10, i*10, 10, 10);
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
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
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public String[][] getMap_to_print() {
		return map_to_print;
	}
	public void setMap_to_print(String[][] map_to_print) {
		this.map_to_print = map_to_print;
	}

}
