package backend;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class is the main class for saving ,controlling the scores .
 * @author Akhil Jarodia and Aayush Gupta
 * @version 1.0	
 * */
public class Game implements Serializable {
	
	private static final long serialVersionUID = 7244301249917304921L;
	private int score;
	private int snakeLen;
	private ArrayList<Double> posY;
	private ArrayList<Integer> blockVal;
	/**
	 * This constructor initialize the value of score 
	 * 
	 */
	public Game() {
		// TODO Auto-generated constructor stub
		score=0;
		posY = new ArrayList<Double>();
		blockVal = new ArrayList<Integer>();
		snakeLen = 0;
	}
	/**
	 * this function is a getter of size of snakeBody
	 * @return it return the {@link Integer} which tells about the length of snake 
	 */
	public int getSnakeLen() {
		return snakeLen;
	}
	/**
	 * this function is a setter of snakeLen
	 * @param snakeLen it takes length of snake as a input which need to be set
	 */
	public void setSnakeLen(int snakeLen) {
		this.snakeLen = snakeLen;
	}
	/**
	 * this function is a getter of ArrayList of score of Obstacleblock
	 * @return it return the ArrayList of {@link Integer}
	 */
	public ArrayList<Integer> getBlockVal() {
		return blockVal;
	}
	/**
	 * this function is a setter of BlockValues
	 * @param blockVal it takes score as a input which need to be set
	 */
	public void setBlockVal(ArrayList<Integer> blockVal) {
		this.blockVal = blockVal;
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
	 * this function is a getter of ArrayList of position of block
	 * @return it return the ArrayList
	 */
	public ArrayList<Double> getPosY() {
		return posY;
	}
	/**
	 * this function is a setter of ArrayList posY
	 * @param posY It takes ArrayList as an input which need to be set
	 */
	public void setPosY(ArrayList<Double> posY) {
		this.posY = posY;
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
