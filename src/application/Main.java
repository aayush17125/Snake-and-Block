package application;


import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Button Play = new Button("Start");
			Button LeaderBoard = new Button("Leader Board");
			Button Exit = new Button("Exit");
			Button ExitPlay = new Button("Quit");
			Rectangle rec = new Rectangle(950, 600, 100, 100);
			rec.setFill(Color.GREENYELLOW);
			InnerShadow is = new InnerShadow();
			Group r = new Group();
			Group r2 = new Group();
			Scene scene2 = new Scene(r2,2000,1000);
			Scene scene = new Scene(r,2000,1000);
			is.setOffsetX(4.0f);
			is.setOffsetY(4.0f);
			Text t = new Text();
			t.setEffect(is);
			t.setX(800);
			t.setY(100);
			t.setText("Block 'Em");
			t.setFill(Color.RED);
			t.setFont(Font.font(null, FontWeight.BOLD, 80));
			t.setTranslateX(300);
			t.setTranslateY(300);
			Play.setLayoutX(900.0);
			Play.setLayoutY(350.0);
			LeaderBoard.setLayoutX(875.0);
			LeaderBoard.setLayoutY(400.0);
			Exit.setLayoutX(900.0);
			Exit.setLayoutY(450.0);
			r2.getChildren().add(ExitPlay);
			r2.getChildren().add(rec);
			r.getChildren().add(t);
			r.getChildren().add(Play);
			r.getChildren().add(LeaderBoard);
			r.getChildren().add(Exit);
			scene.setFill(Color.GREENYELLOW);
			scene2.setFill(Color.BLACK);
			scene.getStylesheets().addAll(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Snake and Blocks");
			primaryStage.show();
			ExitPlay.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					primaryStage.setScene(scene);
				}
			});
			Play.setOnAction(new EventHandler<ActionEvent>() {				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("Play");
					primaryStage.setScene(scene2);
				}
			});
			
			LeaderBoard.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("LeaderBoard");
				}
			});
			Exit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("Exit");
					primaryStage.close();
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

