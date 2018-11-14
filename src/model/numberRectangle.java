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
import javafx.scene.text.Text;

public class numberRectangle extends Rectangle {
    private int num=0;


    public numberRectangle(float x, float y, float width, float height, int number)
    {

        super(x,y,width,height);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.INSIDE);
        setNum(number);


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

    private static Image textToImage(String text) {
        Label label = new Label(text);
        label.setMinSize(125, 125);
        label.setMaxSize(125, 125);
        label.setPrefSize(125, 125);
        label.setStyle("-fx-background-color: white; -fx-text-fill:black;");
        label.setWrapText(true);
        Scene scene = new Scene(new Group(label));
        WritableImage img = new WritableImage(125, 125) ;
        scene.snapshot(img);
        return img ;
    }
}
