package model;

import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class CustomRectangle extends SubScene {
	private final String FONT_PATH="src/model/resources/kenvector_future.ttf";
	private final String PANEL_PATH="model/resources/yellow_panel.png";
	
	public CustomRectangle() {
		super(new StackPane(),600,400);prefWidth(600);prefHeight(400);
		BackgroundImage image=new BackgroundImage(new Image(PANEL_PATH,600,400,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		StackPane root2=(StackPane) this.getRoot();
		root2.setBackground(new Background(image));
		
	}
	public StackPane getPane()
	{
		return (StackPane) this.getRoot();
	}
	public void topper(int i)
	{
		this.getPane().getChildren().remove(index);
	}

}
