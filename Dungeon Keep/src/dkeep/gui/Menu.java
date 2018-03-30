package dkeep.gui;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

	public JFrame frame;
	private WindowGame window;
	
	public Menu(WindowGame window)
	{
		this.initialize();
		this.window=window;
	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		// Container
		JPanel container = new JPanel();
		container.setBorder(new EmptyBorder(15, 15, 15, 15));
		frame.getContentPane().add(container);
		
		GridBagLayout gbl_container = new GridBagLayout();
		gbl_container.columnWidths = new int[]{1,1,1};
		gbl_container.rowHeights = new int[] {1,1,1,1,1};
		gbl_container.columnWeights = new double[]{5,1,5};
		gbl_container.rowWeights = new double[]{3,1,1,1,3};
		container.setLayout(gbl_container);
		
		JButton btnPlay = new JButton("Play");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				window.playSetVisible(true);
				window.menuSetVisible(false);
			}
		});
		container.add(btnPlay, gbc_btnNewButton);
		
		JButton btnCreate = new JButton("Create");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		container.add(btnCreate, gbc_btnNewButton_1);
		
		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 3;
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		container.add(btnExit, gbc_btnNewButton_2);

	}
}
