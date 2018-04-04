package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MapEditor extends Graphic implements MouseListener {

	public JFrame frame;
	private WindowGame window;
	public JLabel lblContent;
	
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
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[] {0, 0, 0, 0, 0, 1};	// Double.MIN_VALUE
		gridBagLayout.rowWeights = new double[]{0, 1, 0.0};
		container.setLayout(gridBagLayout);
		
		JLabel lblElement = new JLabel("Element");
		GridBagConstraints gbc_lblElement = new GridBagConstraints();
		gbc_lblElement.insets = new Insets(0, 0, 5, 5);
		gbc_lblElement.gridx = 0;
		gbc_lblElement.gridy = 0;
		container.add(lblElement, gbc_lblElement);
		
		JLabel lblWall = new JLabel("Wall");
		GridBagConstraints gbc_lblWall = new GridBagConstraints();
		gbc_lblWall.insets = new Insets(0, 0, 5, 5);
		gbc_lblWall.gridx = 1;
		gbc_lblWall.gridy = 0;
		container.add(lblWall, gbc_lblWall);
		
		JLabel lblKey = new JLabel("Key");
		GridBagConstraints gbc_lblKey = new GridBagConstraints();
		gbc_lblKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblKey.gridx = 2;
		gbc_lblKey.gridy = 0;
		container.add(lblKey, gbc_lblKey);
		
		JLabel lblOgre = new JLabel("Ogre");
		GridBagConstraints gbc_lblOgre = new GridBagConstraints();
		gbc_lblOgre.insets = new Insets(0, 0, 5, 5);
		gbc_lblOgre.gridx = 3;
		gbc_lblOgre.gridy = 0;
		container.add(lblOgre, gbc_lblOgre);
		
		JLabel lblExit = new JLabel("Exit");
		GridBagConstraints gbc_lblExit = new GridBagConstraints();
		gbc_lblExit.insets = new Insets(0, 0, 5, 5);
		gbc_lblExit.gridx = 4;
		gbc_lblExit.gridy = 0;
		container.add(lblExit, gbc_lblExit);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 6;
		gbc_panel.insets = new Insets(5, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		container.add(panel, gbc_panel);
		
		lblContent = new JLabel("Loading...");
		GridBagConstraints gbc_lblContent = new GridBagConstraints();
		gbc_lblContent.gridwidth = 6;
		gbc_lblContent.insets = new Insets(0, 0, 0, 5);
		gbc_lblContent.gridx = 0;
		gbc_lblContent.gridy = 2;
		container.add(lblContent, gbc_lblContent);

		consoleLog("Init finished.");
	}
	
	private void consoleLog(String text) {
		lblContent.setText(text);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		consoleLog("Test");
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
