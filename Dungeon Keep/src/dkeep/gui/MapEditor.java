package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class MapEditor extends Graphic {

	public JFrame frame;
	private WindowGame window;
	public JLabel lblContent;
	public JLabel draggingElement;
	
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
	
	private class CustomMouseListener implements MouseListener {

		private String _text = "";
		
		public CustomMouseListener(String text) {
			_text = text;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			if(!draggingElement.getText().equals(_text)) {
				draggingElement.setText(_text);
				draggingElement.setBounds(e.getComponent().getBounds());
				draggingElement.setVisible(true);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
			draggingElement.setVisible(false);
			consoleLog("");
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
	
	private class CustomMouseMotionAdapter extends MouseMotionAdapter {
		
		private CustomMouseMotionAdapter() {
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			int elWidth  = draggingElement.getWidth();
			int elHeight = draggingElement.getHeight();

			int gestureX = e.getComponent().getX() + e.getX();
			/* e.getComponent().getX()
			   = return the clicked element position
			 * e.getX()
			   = return the X relative to the clicked element
			 */

			int gestureY = e.getComponent().getY() + e.getY();
			
			draggingElement.setBounds((gestureX - elWidth/2), (gestureY - elHeight/2), elWidth, elHeight);
			consoleLog("dragging - " + gestureX + "," + gestureY);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		draggingElement = new JLabel("Dragging");
		draggingElement.setHorizontalAlignment(SwingConstants.CENTER);
		draggingElement.setBounds(0, 0, 50, 50);
		draggingElement.setVisible(false);
		frame.getContentPane().add(draggingElement);
		
		// Container
		JPanel container = new JPanel();
		container.setBounds(0, 0, 800, 628);
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
		
		JLabel lblWall = new JLabel("Wall");
		lblWall.addMouseMotionListener(new CustomMouseMotionAdapter());
		lblWall.addMouseListener(new CustomMouseListener("Wall"));
		GridBagConstraints gbc_lblWall = new GridBagConstraints();
		gbc_lblWall.insets = new Insets(0, 0, 5, 5);
		gbc_lblWall.gridx = 1;
		gbc_lblWall.gridy = 0;
		container.add(lblWall, gbc_lblWall);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.addMouseMotionListener(new CustomMouseMotionAdapter());
		lblKey.addMouseListener(new CustomMouseListener("Key"));
		GridBagConstraints gbc_lblKey = new GridBagConstraints();
		gbc_lblKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblKey.gridx = 2;
		gbc_lblKey.gridy = 0;
		container.add(lblKey, gbc_lblKey);
		
		JLabel lblOgre = new JLabel("Ogre");
		lblOgre.addMouseMotionListener(new CustomMouseMotionAdapter());
		lblOgre.addMouseListener(new CustomMouseListener("Ogre"));
		GridBagConstraints gbc_lblOgre = new GridBagConstraints();
		gbc_lblOgre.insets = new Insets(0, 0, 5, 5);
		gbc_lblOgre.gridx = 3;
		gbc_lblOgre.gridy = 0;
		container.add(lblOgre, gbc_lblOgre);
		
		JLabel lblExit = new JLabel("Exit");
		lblExit.addMouseMotionListener(new CustomMouseMotionAdapter());
		lblExit.addMouseListener(new CustomMouseListener("Exit"));
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
		
		JPanel bottombar = new JPanel();
		bottombar.setLayout(new BoxLayout(bottombar, BoxLayout.X_AXIS));
		
		GridBagConstraints gbc_bottombar = new GridBagConstraints();
		gbc_bottombar.anchor = GridBagConstraints.WEST;
		gbc_bottombar.insets = new Insets(0, 0, 0, 0);
		gbc_bottombar.gridwidth = 6;
		gbc_bottombar.gridx = 0;
		gbc_bottombar.gridy = 2;
		container.add(bottombar, gbc_bottombar);

		lblContent = new JLabel("Loading...");
		bottombar.add(lblContent);

		consoleLog("Init finished.");
	}
	
	private void consoleLog(String text) {
		lblContent.setText(text);
	}
}
