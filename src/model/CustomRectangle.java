package model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
/**
 * This class is used to make the subscene on the main menu where leaderboard,etc are displayed
 * @author Akhil an Aayush
 *
 */
public class CustomRectangle extends SubScene {
	private final String FONT_PATH="src/model/resources/kenvector_future.ttf";
	private final String PANEL_PATH="model/resources/yellow_panel.png";
/**
 * This constructor is used to making new object of the class and sets background images and Pane set on the subscene
 */
	public CustomRectangle() {
		super(new AnchorPane(),600,400);prefWidth(600);prefHeight(400);
		BackgroundImage image=new BackgroundImage(new Image(PANEL_PATH,600,400,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		AnchorPane root2=(AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));

	}
	/**
	 * It return the AnchorPane of the subscene on which all the activity is done
	 * @return Pane of the subscene
	 */
	public AnchorPane getPane()
	{
		return (AnchorPane) this.getRoot();
	}
	/**
	 * It is used to bring the required object on top
	 * @param i It defines the number of that object which is needed
	 */
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