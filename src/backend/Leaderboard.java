package backend;

import java.io.Serializable;
import java.util.ArrayList;

public  abstract class Leaderboard  {

	private ArrayList<String> data;
	
	Leaderboard()
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
	public ArrayList<String> getScore()
	{
		return data;
	}
}
