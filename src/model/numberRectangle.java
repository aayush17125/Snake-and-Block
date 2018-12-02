package model;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;
/**
 * This class is used to make custom rectangle for the bricks
 * THis class extends rectangle class
 * @author Akhil and aayush
 *
 */
public class numberRectangle extends Rectangle {
    private int num=0;
    private boolean done;
    /**
     * It initialises the rectangle super class and customise it's stroke and set number on top of it
     * @param x X coordinate of the rectangle
     * @param y Y coordinate of the rectangle
     * @param width Width of the rectangle required
     * @param height Height of the rectangle
     * @param number Number which is needed on that brick
     */
    public numberRectangle(float x, float y, float width, float height, int number)
    {

        super(x,y,width,height);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.INSIDE);
        setNum(number);
        done=false;


        setFill(new ImagePattern(textToImage(Integer.toString(number))));

    }
    /**
     * It returns the point on that brick
     * @return the point needed to break that block
     */
    public int getNum()
    {
        return num;
    }
    /**
     * It is used to set the number of that brick
     * @param number the num which the user wishes on the brick
     */
    public void setNum(int number)
    {
        num=number;
        setFill(new ImagePattern(textToImage(Integer.toString(num))));
    }
    /**
     * Called when the block hits the snake
     */
    public void hit()
    {
        done=true;
    }
    /**
     * It reinitializes the various param of the rectangle that are required in every cycle
     */
    public void refresh()
    {
        done=false;
        setVisible(true);

    }
    /**
     * Gives the status whether the block is hitted or not
     * @return true or false denoting whether it is hit or not
     */
    public boolean isHit()
    {
        return done;
    }
    /**
     * generates the number on the bricks
     * @param text the number to be set
     * @return image that is used as background
     */
    private Image textToImage(String text) {
        Random rand_x = new Random();
        Label label = new Label(text);
        label.setMinSize(125, 125);
        label.setMaxSize(125, 125);
        label.setPrefSize(125, 125);
        label.setFont(new Font(30)); 
        int nextInt = rand_x.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", nextInt);
        label.setStyle("-fx-background-color: "+ colorCode+"; -fx-text-fill:#000000;");
        label.setWrapText(true);
        Scene scene = new Scene(new Group(label));
        WritableImage img = new WritableImage(125, 125) ;
        scene.snapshot(img);
        return img ;
    }
}
