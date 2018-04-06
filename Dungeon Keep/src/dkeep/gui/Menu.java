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
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class Menu {

	public JFrame frame;
	private WindowGame window;
	JComboBox<String> comboBox;
	
	public void updateComboBox()
	{
		ArrayList<String> nomes=window.gestor.getNames();
		for(int i=comboBox.getItemCount();i<nomes.size();i++)
		{
			comboBox.addItem(nomes.get(i));
		}
	}
	
	public Menu(WindowGame window)
	{
		this.window=window;
		this.initialize();
	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(50, 50, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		// Container
		JPanel container = new JPanel();
		container.setBorder(new EmptyBorder(15, 15, 15, 15));
		frame.getContentPane().add(container);
		
		GridBagLayout gbl_container = new GridBagLayout();
		gbl_container.columnWidths = new int[]{0, 0, 93};
		gbl_container.rowHeights = new int[] {0};
		gbl_container.columnWeights = new double[]{0, 0.0, 0.0};
		gbl_container.rowWeights = new double[]{0};
		container.setLayout(gbl_container);
		
		JButton btnPlay = new JButton("Play");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		
		container.add(btnPlay, gbc_btnNewButton);
		
		JButton btnCreate = new JButton("Create");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				window.menuSetVisible(false);
				window.playSetVisible(false);
				window.mapEditorSetVisible(true);
			}
		});
		
		comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		container.add(comboBox, gbc_comboBox);
		container.add(btnCreate, gbc_btnNewButton_1);
		ArrayList<String> nomes=window.gestor.getNames();
		for(int i=0;i<nomes.size();i++)
		{
			comboBox.addItem(nomes.get(i));
		}
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				window.menuSetVisible(false);
				window.playSetVisible(true);
				window.mapEditorSetVisible(false);
				window.selected=comboBox.getSelectedIndex();
			}
		});
		
		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
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
