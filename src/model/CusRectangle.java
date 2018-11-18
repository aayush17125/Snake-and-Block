package model;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CusRectangle extends Rectangle {
    private StackPane st = new StackPane();
    boolean done= false;
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
    Image img1 = new Image("model/resources/bolt_gold.png");
    Image img2 = new Image("model/resources/fruit.png");
    Image img3 = new Image("model/resources/shield_gold.png");
    private int type=0;
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

}
