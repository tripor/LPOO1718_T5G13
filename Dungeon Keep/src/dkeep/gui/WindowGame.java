package dkeep.gui;

import java.awt.EventQueue;
import java.io.IOException;

public class WindowGame {
	
	Menu menu = null;
	PlayArea play = null;
	MapEditor mapEditor = null;
	FileManager gestor;
	int selected=1;
	
	public void menuSetVisible(boolean state)
	{
		if(menu != null) {
			menu.frame.setVisible(state);
			menu.updateComboBox();
		}
	}
	
	public void playSetVisible(boolean state)
	{
		if(state) {
			play = new PlayArea(this);
		}		
		if(play != null) {
			play.frame.setVisible(state);	
		}
	}
	
	public void mapEditorSetVisible(boolean state)
	{
		if(state) {
			mapEditor = new MapEditor(this);
		}
		if(mapEditor != null) {
			mapEditor.frame.setVisible(state);
		}
	}
	
	
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				WindowGame me = new WindowGame();
				me.gestor=new FileManager();
				me.gestor.openFile();
				me.menu = new Menu(me);
				me.menu.frame.setVisible(true);
				Runtime.getRuntime().addShutdownHook(new Thread()
				{
				    @Override
				    public void run()
				    {
				        try {
							me.gestor.closeFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}
	

}
