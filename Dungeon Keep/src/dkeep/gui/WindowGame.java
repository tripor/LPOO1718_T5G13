package dkeep.gui;

import java.awt.EventQueue;

public class WindowGame {
	
	Menu menu;
	PlayArea play;
	
	public void menuSetVisible(boolean state)
	{
		menu.frame.setVisible(state);
	}
	
	public void playSetVisible(boolean state)
	{
		if(state)
		{
			play=new PlayArea(this);
		}
		play.frame.setVisible(state);
	}
	
	
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				WindowGame me=new WindowGame();
				me.menu = new Menu(me);
				me.menu.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}

}
