package model;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class numberRectangle extends Rectangle {
    private int num=0;
    private Text numberplate;

    public numberRectangle(float x, float y, float width, float height, int number, AnchorPane pane)
    {

        super(x,y,width,height);
        setFill(Color.color(Math.random(),Math.random(),Math.random()));
        setNum(number);
        numberplate=new Text("0");
        pane.getChildren().addAll(this,numberplate);


    }
    public int getNum()
    {
        return num;
    }
    public void setNum(int number)
    {
        num=number;
        numberplate.setText(Integer.toString(number));
    }


}
