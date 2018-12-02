package model;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import static java.lang.Math.*;
/**
 * This class is used to make the snake body bids
 * @author Akhil n Aayush
 *
 */
public class CustomCircle extends Circle {
    private final static int KEYBOARD_MOVEMENT_DELTA=5;
	private int point=1;
	/*
	 * sets the length of snake
	 * @param point sets the length equal to point
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	/**
	 * This constructor initialise the various parameters and call the various required functions
	 * @param width the X coordinate of the snake
	 * 
	 * @param height the Y coordinate of the snake
	 * @param radius the radius of the snake
	 * @param scene The scene element of class javafx.scene in which it is to be put
	 */
	public CustomCircle(float width, float height, float radius, Scene scene) {
        setCenterX(width);
        setCenterY(height);
        setRadius(radius);
        setFill(Color.color(random(), random(), random(), 0.7));
    }
/**
 * Used to decrease the length of snake
 * @param a  amount of length to be reduced
 */
	 public void decLength(int a)
	    {
	    	point-=a;
	    	setFill(new ImagePattern(textToImage(Integer.toString(point))));
	    }
	 /**
	  * Used to increase the length of snake
	  * 
	  */
	    public void incLength()
	    {
	    	point++;
	    	setFill(new ImagePattern(textToImage(Integer.toString(point))));
	    }
	    /**
	     * It is used to set the label on the snake body denoting the length of snake
	     */
	  public void setImage()
	  {
		  setFill(new ImagePattern(textToImage(Integer.toString(point))));
		 
	  }	    
	  /**
	     * It is used to set the label on the snake body denoting the length of snake
	     * @param t input length
	     */
	  public void setImage(int t)
	  {
		  setFill(new ImagePattern(textToImage(Integer.toString(t))));
		 
	  }
	  private Image textToImage(String text) {
	      
	        Label label = new Label(text);
	        label.setMinSize(125, 125);
	        label.setMaxSize(125,125);
	        label.setPrefSize(125, 125);
	        label.setWrapText(true);
	        label.setFont(new Font(50)); 
	        Scene scene = new Scene(new Group(label));
	        WritableImage img = new WritableImage(125, 125) ;
	        scene.snapshot(img);
	        return img ;
	    }

}
