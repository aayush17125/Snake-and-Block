package application;


import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Text t = new Text();
			t.setText("Block 'Em");
			t.setFont(Font.font(80));
			t.setFill(Color.BLACK);
			t.setLayoutX(800.0);
			t.setLayoutY(100.0);
			Button Play = new Button("Start");
			Play.setOnAction(new EventHandler<ActionEvent>() {				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("Play");
					
				}
			});
			Button LeaderBoard = new Button("Leader Board");
			LeaderBoard.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("LeaderBoard");
				}
			});
			Button Exit = new Button("Exit");
			Exit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("Exit");
				}
			});
			Play.setLayoutX(900.0);
			Play.setLayoutY(350.0);
			LeaderBoard.setLayoutX(875.0);
			LeaderBoard.setLayoutY(400.0);
			Exit.setLayoutX(900.0);
			Exit.setLayoutY(450.0);
			Group r = new Group();
			r.setId("pane");
			r.getChildren().add(t);
			r.getChildren().add(Play);
			r.getChildren().add(LeaderBoard);
			r.getChildren().add(Exit);
			Scene scene = new Scene(r,2000,1000);
			scene.setFill(Color.GREENYELLOW);
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

