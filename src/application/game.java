package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class game extends Application {
  private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
  private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.25);

  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) throws Exception {
    final Circle circle = createCircle();
    final Group group = new Group( circle);
    
    
    final Scene scene = new Scene(group, 600, 400, Color.CORNSILK);
    moveCircleOnKeyPress(scene, circle);
   
//    circle.setCenterY(350);
    stage.setScene(scene);
    stage.show();
  }

  private Circle createCircle() {
    final Circle circle = new Circle(300, 350, 30, Color.CRIMSON);
    circle.setOpacity(0.7);
    return circle;
  }

 

  private void moveCircleOnKeyPress(Scene scene, final Circle circle) {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case RIGHT: circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
         case LEFT:  circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
        }
      }
    });
  }

  
}
