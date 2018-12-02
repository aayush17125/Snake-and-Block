package model;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
/**
 * This class makes the custom powerups which extends Circle class
 *
 *
 */
public class CusRectangle extends Circle {
    boolean done= false;
    /**
     * When the power is hit,it is called
     */
	 public void hit()
	    {
	        done=true;
	    }
	 /**
	  * It refresh the power i.e visibility,values,etc
	  */
	    public void refresh()
	    {
	        done=false;
	        setVisible(true);

	    }
	    /**
	     * This function return whether the power is hit or not
	     * @return true or false with respect to whether it is hitted or not
	     */
	    public boolean isHit()
	    {
	        return done;
	    }
    Image img1 = new Image("model/resources/bolt_gold.png");
    Image img2 = new Image("model/resources/fruit.png");
    Image img3 = new Image("model/resources/shield_gold.png");
    Image img4 = new Image("model/resources/nuke.png");
    private int type=0;
	private int point=7;
	/**
	 * This constructor initialise the circle superclass and calls various other class
	 * functions which are required
	 * @param q The radius of the circle
	 * @param x The X coordinate of the power
	 * @param y The Y coordinate of the power
	 */
    public CusRectangle(int q,double x,double y)
    {  
    	this.setCenterX(x);
    	this.setCenterY(y);
    	this.setRadius(20);
        if(q==1)
        {
            setFill(new ImagePattern(img1));
            type = 1;
        }
        else if(q==2)
        {	 
            setFill(new ImagePattern(img2));
            type=2;
        }
        else if (q==3)
        {
            setFill(new ImagePattern(img3));
            type=3;
        }
        else if(q==4)
        {
        	setFill(new ImagePattern(img4));
        	type=4;
        
        }
    }
    /**
     * It gives us the type of power.i.e block destroyer,magnet,etc.
     * @return integer which refer the type
     */
	public int getType() {
		return type;
	}
	/**
	 * This is used to set the type of power.i.e block destroyer,magnet,etc.
	 * @param type
	 *  defines the type of power
	 * 
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * It gives the points of the ball
	 * @return no of points
	 */
	 public int getLength()
	    {
		 return this.point;
    	}
	 /**
	  * public setter for the point of ball
	  * @param t the value of point to be set
	  */
	    public void setLength(int t)
	    {
	    	point = t;
	    	setFill(new ImagePattern(textToImage(Integer.toString(point))));
	    }
	    
	  private Image textToImage(String text) {
	      
	        Label label = new Label(text);
	        label.setMinSize(125, 125);
	        label.setMaxSize(125,125);
	        label.setPrefSize(125, 125);
	        label.setWrapText(true);
	        label.setFont(new Font(100)); 
	        Scene scene = new Scene(new Group(label));
	        WritableImage img = new WritableImage(125, 125) ;
	        scene.snapshot(img);
	        return img ;
	    }
	 

}
