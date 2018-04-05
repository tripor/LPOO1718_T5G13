package dkeep.gui;

import java.awt.EventQueue;

public class WindowGame {
	
	Menu menu = null;
	PlayArea play = null;
	MapEditor mapEditor = null;
	
	public void menuSetVisible(boolean state)
	{
		if(menu != null) {
			menu.frame.setVisible(state);
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
				/*WindowGame me = new WindowGame();
				me.menu = new Menu(me);
				me.menu.frame.setVisible(true);*/
				FileManager file=new FileManager();
				file.openFile();
				file.closeFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}

}
