package application;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class game extends Application {
  private static final int      KEYBOARD_MOVEMENT_DELTA = 5;


  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) throws Exception {
	  final Circle circle = new Circle(300, 350, 30, Color.CRIMSON);
	    circle.setOpacity(0.7);
    final Group group = new Group( circle);
    
    
    final Scene scene = new Scene(group, 600, 400, Color.CORNSILK);
    moveCircleOnKeyPress(scene, circle);
    stage.setScene(scene);
    stage.show();
  }

  private void moveCircleOnKeyPress(Scene scene, final Circle circle) {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case RIGHT: circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
         case LEFT:  circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
        }
      }
    });
  }

  
}
