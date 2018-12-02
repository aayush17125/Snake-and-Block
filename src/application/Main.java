package application;
	
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import backend.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import model.CusRectangle;
import model.numberRectangle;
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
	/**
	 * Everything works in {@link ViewManager}. This is an instance of {@link ViewManager}.
	 */
	private static ViewManager manager;
	/**
	 * Serializes after force shutdown
	 * @param input a game object
	 */
	public static void serialize(Game game) throws IOException {
		ObjectOutputStream out = null;
		try { 
			out = new ObjectOutputStream(new FileOutputStream("score.txt"));
			out.writeObject(game);
		}finally {
			out.close();
		}
	}
	@Override
	/**
	 * This method is override of start method in Application class.
	 * @param primaryStage It is the stage on which we want our output
	 */
	public void start(Stage primaryStage) {
		try {
			manager=new ViewManager();
			primaryStage= manager.getMainStage();
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
