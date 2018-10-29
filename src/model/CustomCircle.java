package model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static java.lang.Math.*;

public class CustomCircle extends Circle {
    private final static int KEYBOARD_MOVEMENT_DELTA=5;
    public CustomCircle(float width, float height, float radius, Scene scene)
    {
        setCenterX(width);
        setCenterY(height);
        setRadius(radius);
        setFill(Color.color(random(), random(), random(),0.7));
        keylisteners(scene);
    }

    private void keylisteners(Scene scene)
    {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode())
                {
                    case RIGHT:setCenterX(getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
                    case LEFT:setCenterX(getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
                }
            }
        });
    }





}
