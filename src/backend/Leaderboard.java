package backend;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class controls and saves the state of the game
 * @author Akhil and aayush
 *@version 1.0
 */
public class Leaderboard implements Serializable  {

	private ArrayList<String> data;
	/**
	 * This constructor initialises the database of the leaderboard 
	 */
	public Leaderboard()
	{
		data=new ArrayList<String>();
	}
	/**
	 * It is used to add scores in the database
	 * @param score The value of score to be added
	 */
	public void addScore(int score)
	{
		data.add(Integer.toString(score));
		if(data.size()>10)
		{
			data.remove(0);
		}
	}
	/**
	 * It is used to add scores in the database with the time and date
	 * @param score String containing both date time and the score
	 */
	public void addScore(String score)
	{
		data.add((score));
		if(data.size()>10)
		{
			data.remove(0);
		}
	}
	/**
	 * It gives us the complete arraylist in which data is getting stored
	 * @return Arraylist of the database
	 */
	public ArrayList<String> getScore()
	{
		return data;
	}

}
