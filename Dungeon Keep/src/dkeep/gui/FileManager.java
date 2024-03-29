package dkeep.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import dkeep.logic.GameMap;
import dkeep.logic.character.Club;
import dkeep.logic.character.Guard;

public class FileManager {
	private File map_file = new File("maps.txt");
	private FileInputStream fis;
	private FileOutputStream fos;
	private BufferedWriter bw;
	private BufferedReader br;
	private boolean fileopened=false;
	private ArrayList<String[][]> maps= new ArrayList<String[][]>();
	private ArrayList<String> names= new ArrayList<String>();
	private ArrayList<ArrayList<Integer>> position_x=new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> position_y=new ArrayList<ArrayList<Integer>>();

	public ArrayList<String> getNames() {
		return names;
	}
	public String[][] getMaps(int i) {
		return maps.get(i);
	}
	public void openFile()
	{
		try {
			fis = new FileInputStream(map_file);
			br = new BufferedReader(new InputStreamReader(fis));
			this.readAllMaps();
			fos = new FileOutputStream(map_file);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			setFileopened(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeGame(GameMap jogo,String name)
	{
		this.maps.add(jogo.getCopied_map());
		this.names.add(name);
		ArrayList<Integer> add_x=new ArrayList<Integer>();
		ArrayList<Integer> add_y=new ArrayList<Integer>();
		add_x.add(jogo.getHero().positionX);
		add_y.add(jogo.getHero().positionY);
		for(Guard g:jogo.getGuards())
		{
			add_x.add(g.positionX);
			add_y.add(g.positionY);
		}
		this.position_x.add(add_x);
		this.position_y.add(add_y);
	}
	private void readAllMaps() throws IOException
	{
		String line = null;
		ArrayList<Integer> add_x= new ArrayList<Integer>();
		ArrayList<Integer> add_y= new ArrayList<Integer>();
		while ((line = br.readLine()) != null) {
			if(line.equals("Name"))
			{
				line = br.readLine();
				names.add(line);
			}
			else if(line.equals("Map"))
			{
				int altura,comprimento;
				line = br.readLine();
				String[] part=line.split(" ");
				comprimento=Integer.parseInt(part[0]);
				altura=Integer.parseInt(part[1]);
				String[][] new_map=new String[comprimento][altura];
				int j;
				for(int i=0;i<altura;i++)
				{
					j=0;
					line = br.readLine();
					for(String divided:line.split(";"))
					{
						new_map[i][j]=divided;
						j++;
					}
				}
				this.maps.add(new_map);
			}
			else if(line.equals("Hero"))
			{
				line = br.readLine();
				String[] part=line.split(" ");
				add_x.add(Integer.parseInt(part[0]));
				add_y.add(Integer.parseInt(part[1]));
			}
			else if(line.equals("Ogre"))
			{
				line = br.readLine();
				int quantity=Integer.parseInt(line);
				for(int i=0;i<quantity;i++)
				{
					line = br.readLine();
					String[] part=line.split(" ");
					add_x.add(Integer.parseInt(part[0]));
					add_y.add(Integer.parseInt(part[1]));
				}
				this.position_x.add(add_x);
				this.position_y.add(add_y);
				add_x= new ArrayList<Integer>();
				add_y= new ArrayList<Integer>();
			}
		}
	}
	
	public void setGame(int number,GameMap game)
	{
		game.setCopied_map(new String[this.maps.get(number).length][this.maps.get(number)[0].length]);
		game.setMap(new String[this.maps.get(number).length][this.maps.get(number)[0].length]);
		for(int i=0;i<this.maps.get(number).length;i++)
		{
			for(int j=0;j<this.maps.get(number)[0].length;j++)
			{
				game.setCopied_map(this.maps.get(number)[i][j], i, j);
				game.setMap(this.maps.get(number)[i][j], i, j);
			}
		}
		game.getHero().positionX=this.position_x.get(number).get(0);
		game.getHero().positionY=this.position_y.get(number).get(0); 
		game.getHero().clubs.get(0).clubNextPosition(game, game.getHero());
		game.getGuards().clear();
		for(int i=1;i<this.position_x.get(number).size();i++)
		{
			game.getRandomGuard();
			game.getGuards().get(i-1).positionX=this.position_x.get(number).get(i);
			game.getGuards().get(i-1).positionY=this.position_y.get(number).get(i);
			for(Club c:game.getGuards().get(i-1).clubs)
			{
				c.clubNextPosition(game, game.getGuards().get(i-1));
			}
		}
		game.markPositions();
	}
	public void closeFile() throws IOException
	{
		for(int i=0;i<this.names.size();i++)
		{
			bw.write("Name");
			bw.newLine();
			bw.write(this.names.get(i));
			bw.newLine();
			bw.write("Map");
			bw.newLine();
			bw.write(this.maps.get(i).length + " " + this.maps.get(i)[0].length);
			bw.newLine();
			for(int k=0;k<this.maps.get(i).length;k++)
			{
				for(int m=0;m<this.maps.get(i)[0].length;m++)
				{
					bw.write(this.maps.get(i)[k][m]);
					if(m!=this.maps.get(i)[0].length-1)
						bw.write(";");
				}
				bw.newLine();
			}
			bw.write("Hero");
			bw.newLine();
			bw.write(this.position_x.get(i).get(0)+ " " +this.position_y.get(i).get(0));
			bw.newLine();
			bw.write("Ogre");
			bw.newLine();
			bw.write((this.position_x.get(i).size()-1) +"");
			bw.newLine();
			for(int j=1;j<this.position_x.get(i).size();j++)
			{
				bw.write(this.position_x.get(i).get(j)+ " " +this.position_y.get(i).get(j));
				bw.newLine();
			}
			
		}
		try {
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isFileopened() {
		return fileopened;
	}
	public void setFileopened(boolean fileopened) {
		this.fileopened = fileopened;
	}
}
