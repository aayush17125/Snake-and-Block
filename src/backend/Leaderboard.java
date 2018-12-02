package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Leaderboard implements Serializable  {

	private ArrayList<String> data;
	
	public Leaderboard()
	{
		data=new ArrayList<String>();
	}
	public void addScore(int score)
	{
		data.add(Integer.toString(score));
		if(data.size()>10)
		{
			data.remove(0);
		}
	}
	public void addScore(String score)
	{
		data.add((score));
		if(data.size()>10)
		{
			data.remove(0);
		}
	}
	public ArrayList<String> getScore()
	{
		return data;
	}

}
