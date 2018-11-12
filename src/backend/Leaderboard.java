package backend;

import java.io.Serializable;
import java.util.ArrayList;

public  abstract class Leaderboard implements Serializable {

	private ArrayList<String> data;
	
	Leaderboard()
	{
		data=new ArrayList<String>();
	}
	public void addScore(String name,int score)
	{
		data.add(name+"_"+Integer.toString(score));
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
