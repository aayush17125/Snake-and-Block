package backend;
import java.io.Serializable;
/**
 * This class is the main class for saving ,controlling the scores .
 * @author Akhil Jarodia and Aayush Gupta
 * @version 1.0	
 * */
public class Game implements Serializable {
	
	private static final long serialVersionUID = 7244301249917304921L;
private int score;
	/**
	 * This constructor initialize the value of score 
	 * 
	 */
	public Game() {
		// TODO Auto-generated constructor stub
		score=0;
	}
	/**
	 * This function resets the score
	 */
	public void resetScore(){
		setScore(0);
	}
	/**
	 * this function is a setter of score
	 * @param score it takes score as a input which need to be set
	 */
	public void setScore(int score){
		this.score = score;
	}
	/**
	 * this function return the current score
	 * @return the current score of the game
	 */
	public int getScore(){
		return score;
	}
	/**
	 * this is used to increment the score by 1
	 */
	public void addScore(){
		score=score+1;
	}
	/**
	 * this method is used to reduce score
	 */
	public void deductScore(){
		score=score-1;
	}
	
}
