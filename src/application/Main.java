package application;

import com.sun.prism.Image;

import javafx.application.Application;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Button Play = new Button("Start");
			Play.setLayoutX(100.0);
			Play.setLayoutY(200.0);
			Button LeaderBoard = new Button("Leader Board");
			Button Exit = new Button("Exit");
			TilePane r = new TilePane();
			r.setId("pane");
			r.getChildren().add(Play);
			r.getChildren().add(LeaderBoard);
			r.getChildren().add(Exit);
			Image image = new Image(Main.class.getResource("C:\\Users\\agupt\\OneDrive\\Pictures\\DYm1aqo.jpg"));
//			BorderPane root = new BorderPane();
			Scene scene = new Scene(r,2000,1000);
			scene.getStylesheets().addAll(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Snake and Blocks");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

