package model;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CusRectangle extends Rectangle {
    private StackPane st = new StackPane();
    boolean done= false;
    
	 public void hit()
	    {
	        done=true;
	        point--;
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
    Image img1 = new Image("model/resources/bolt_gold.png");
    Image img2 = new Image("model/resources/fruit.png");
    Image img3 = new Image("model/resources/shield_gold.png");
    Image img4 = new Image("model/resources/nuke.png");
    private int type=0;
	private int point=7;
//    public CusRectangle(String x)
//    {
//    setFill(Color.BROWN);
//        Text a=new Text(x);
//        st.getChildren().addAll(a,this);
//
//    }
    
    public CusRectangle(int q,double x,double y)
//        Rectangle a = new Rectangle(1,2);
    {   super(x,y);
//        setFill(Color.BROWN);
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
//    public StackPane lay1()
//    {   st.setLayoutX(getLayoutX());
//        st.getLayoutY(getLayoutY());
//        return lay1;
//    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	 public void decLength()
	    {
	    	point--;
	    	setFill(new ImagePattern(textToImage(Integer.toString(point))));
	    }
	    public void incLength()
	    {
	    	point++;
	    	setFill(new ImagePattern(textToImage(Integer.toString(point))));
	    }
	  public void setImage()
	  {if(type==2) {
		  setFill(new ImagePattern(textToImage(Integer.toString(point))));
		 
	  }}
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
