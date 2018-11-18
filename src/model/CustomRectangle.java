package model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class CustomRectangle extends SubScene {
	private final String FONT_PATH="src/model/resources/kenvector_future.ttf";
	private final String PANEL_PATH="model/resources/yellow_panel.png";

	public CustomRectangle() {
		super(new AnchorPane(),600,400);prefWidth(600);prefHeight(400);
		BackgroundImage image=new BackgroundImage(new Image(PANEL_PATH,600,400,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		AnchorPane root2=(AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));

	}
	public AnchorPane getPane()
	{
		return (AnchorPane) this.getRoot();
	}
	public void topper(int i)

	{
		for(int j=0;j<this.getPane().getChildren().size();j++)
		{
			if(j==i-1)
			{
				this.getPane().getChildren().get(j).setVisible(true);
			}
			else
			{
				this.getPane().getChildren().get(j).setVisible(false);
			}
		}
	}

}