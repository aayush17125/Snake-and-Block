package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
/**
 * The main class of the game.This will call the other classes to generate the game elements
 * @author Akhil and aayush
 * @version 1.0
 *
 */

public class Main extends Application {
	@Override
	/**
	 * This method is override of start method in Application class.
	 * @param primaryStage It is the stage on which we want our output
	 */
	public void start(Stage primaryStage) {
		try {
			ViewManager manager=new ViewManager();
			primaryStage= manager.getMainStage();
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
