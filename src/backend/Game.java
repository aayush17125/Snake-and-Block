package backend;
import java.io.Serializable;
public class Game implements Serializable {
private int score;
	public Game() {
		// TODO Auto-generated constructor stub
		score=0;
	}
	public void resetScore(){
		setScore(0);
	}
	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}
	public void addScore(){
		score=score+1;
	}
	public void deductScore(){
		score=score-1;
	}
	
}
