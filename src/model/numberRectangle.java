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

public class numberRectangle extends Rectangle {
    private int num=0;
    private boolean done;

    public numberRectangle(float x, float y, float width, float height, int number)
    {

        super(x,y,width,height);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.INSIDE);
        setNum(number);
        done=false;


        setFill(new ImagePattern(textToImage(Integer.toString(number))));

    }
    public int getNum()
    {
        return num;
    }
    public void setNum(int number)
    {
        num=number;
        setFill(new ImagePattern(textToImage(Integer.toString(num))));
    }
    public void hit()
    {
        done=true;
    }
    public void refresh()
    {
        done=false;
        setVisible(true);

    }
    public boolean isHit()
    {
        return done;
    }

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
