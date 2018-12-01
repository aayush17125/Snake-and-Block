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

public class CustomCircle extends Circle {
    private final static int KEYBOARD_MOVEMENT_DELTA=5;
	private int point=1;
    public CustomCircle(float width, float height, float radius, Scene scene) {
        setCenterX(width);
        setCenterY(height);
        setRadius(radius);
        setFill(Color.color(random(), random(), random(), 0.7));
    }

	 public void decLength(int a)
	    {
	    	point-=a;
	    	setFill(new ImagePattern(textToImage(Integer.toString(point))));
	    }
	    public void incLength()
	    {
	    	point++;
	    	setFill(new ImagePattern(textToImage(Integer.toString(point))));
	    }
	  public void setImage()
	  {
		  setFill(new ImagePattern(textToImage(Integer.toString(point))));
		 
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
