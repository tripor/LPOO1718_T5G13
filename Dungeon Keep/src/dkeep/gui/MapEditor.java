package dkeep.gui;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
//import javax.swing.JLabel;
//import java.awt.GridBagConstraints;
//import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import dkeep.logic.GameMap;
//import dkeep.logic.Level1;
//import dkeep.logic.Level2;

//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.util.ArrayList;

//import javax.swing.JComboBox;
//import javax.swing.JButton;
import javax.swing.JPanel;
//import java.awt.Color;
//import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
//import java.awt.FlowLayout;
//import javax.swing.JTextArea;
//import java.awt.Font;

@SuppressWarnings("serial")
public class MapEditor extends Graphic implements MouseListener {

	public JFrame frame;
	private WindowGame window;
	
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
		gridBagLayout.columnWeights = new double[] {0, 0, 0, 0, 0, 1};	// Double.MIN_VALUE
		gridBagLayout.rowWeights = new double[]{0, 1};
		container.setLayout(gridBagLayout);
		
		JLabel lblElement = new JLabel("Element");
		GridBagConstraints gbc_lblElement = new GridBagConstraints();
		gbc_lblElement.insets = new Insets(0, 0, 5, 5);
		gbc_lblElement.gridx = 0;
		gbc_lblElement.gridy = 0;
		container.add(lblElement, gbc_lblElement);
		
		JButton btnWall = new JButton("Wall");
		GridBagConstraints gbc_btnWall = new GridBagConstraints();
		gbc_btnWall.insets = new Insets(0, 0, 5, 5);
		gbc_btnWall.gridx = 1;
		gbc_btnWall.gridy = 0;
		container.add(btnWall, gbc_btnWall);
		
		JButton btnOgre = new JButton("Ogre");
		GridBagConstraints gbc_btnOgre = new GridBagConstraints();
		gbc_btnOgre.insets = new Insets(0, 0, 5, 5);
		gbc_btnOgre.gridx = 2;
		gbc_btnOgre.gridy = 0;
		container.add(btnOgre, gbc_btnOgre);
		
		JButton btnKey = new JButton("Key");
		GridBagConstraints gbc_btnKey = new GridBagConstraints();
		gbc_btnKey.insets = new Insets(0, 0, 5, 5);
		gbc_btnKey.gridx = 3;
		gbc_btnKey.gridy = 0;
		container.add(btnKey, gbc_btnKey);
		
		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 4;
		gbc_btnExit.gridy = 0;
		container.add(btnExit, gbc_btnExit);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 6;
		gbc_panel.insets = new Insets(5, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		container.add(panel, gbc_panel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
