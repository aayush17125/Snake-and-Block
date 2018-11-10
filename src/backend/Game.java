package backend;

public class Game {
private int score;
	public Game() {
		// TODO Auto-generated constructor stub
		score=0;
		
	}
	
	public int getScore()
	{
		return score;
	}
	public void addScore()
	{
		score=score+1;
	}
	public void deductScore()
	{
		score=score-1;
	}
	public void collided()
	{
		
	}
}
