package view;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import model.CustomRectangle;
import model.SpaceRunnerButton;

public class ViewManager {
	private static final int HEIGHT = 720;
	private static final int WIDTH = 1024;
	private static final int START_X = 100;
	private static final int START_Y = 150;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	Path path = new Path();	
	PathTransition pathTransition = new PathTransition();
	ArrayList<SpaceRunnerButton> menuButtons;
	ArrayList<Circle> snakeBody;
	Circle circle = new Circle(WIDTH/2, HEIGHT-100, 10, Color.CRIMSON);
	Rectangle rect = new Rectangle(500, -100, 30, 30);
	Group r = new Group();
	Group r2 = new Group();
	Scene scene2 = new Scene(r2,WIDTH,HEIGHT);
	private static final int KEYBOARD_MOVEMENT_DELTA = 30;
	public ViewManager()
	{	menuButtons=new ArrayList<SpaceRunnerButton>();
		mainPane=new AnchorPane();
		mainScene=new Scene(mainPane,WIDTH,HEIGHT);
		mainStage =new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createlogo();
		initialiseButtonListeners();
		CustomRectangle ss=new CustomRectangle();
		ss.setLayoutX(310);
		ss.setLayoutY(210);
		circle.setOpacity(0.7);
		r2.getChildren().add(circle);
		rect.setFill(Color.BLUE);
		r2.getChildren().add(rect);
		mainPane.getChildren().add(ss);
		pathSet();
		scene2.setFill(Color.BLACK);
	}
	private void pathSet() {
		pathTransition.setDuration(new Duration(3000));
		pathTransition.setPath(path);
		pathTransition.setNode(rect);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(false);
		path.getElements().add(new MoveTo(500.0f, 0.0f));
		path.getElements().add(new VLineTo(1500.0f));
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
	public Stage getMainStage()
	{
		return mainStage;
	}
	
	private void createButtons()
	{
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditButton();
		createExitButton();
		createMainMenuButton();
	}
	
	
	private void createlogo()
	{
		ImageView logo=new ImageView("view/resources/snake-vs-block.png");
		logo.setLayoutX(400);
		logo.setLayoutY(-100);
		mainPane.getChildren().add(logo);
		
		
	}
	private void addMenuButton(SpaceRunnerButton button)
	{
		button.setLayoutX(START_X);
		button.setLayoutY(START_Y+ menuButtons.size()*100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	private void addMainMenuButton(SpaceRunnerButton button)
	{
		button.setLayoutX(0);
		button.setLayoutY(0);
		button.setPrefHeight(49);
		button.setPrefWidth(191);
		r2.getChildren().add(button);
	}
	private void createStartButton()
	{
		SpaceRunnerButton startbutton=new SpaceRunnerButton("PLAY");
		addMenuButton(startbutton);
	}
	private void createScoresButton()
	{
		SpaceRunnerButton scorebutton=new SpaceRunnerButton("SCORES");
		addMenuButton(scorebutton);
	}
	private void createHelpButton()
	{
		SpaceRunnerButton helpbutton=new SpaceRunnerButton("HELP");
		addMenuButton(helpbutton);
	}
	private void createMainMenuButton()
	{
		SpaceRunnerButton mainMenubutton=new SpaceRunnerButton("Main Menu");
		addMainMenuButton(mainMenubutton);
	}
	private void createCreditButton()
	{
		SpaceRunnerButton helpbutton=new SpaceRunnerButton("CREDITS");
		addMenuButton(helpbutton);
	}
	private void createExitButton()
	{
		SpaceRunnerButton helpbutton=new SpaceRunnerButton("EXIT");
		addMenuButton(helpbutton);
	}
	
	private void initialiseButtonListeners() {
		menuButtons.get(0).setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				mainStage.setScene(scene2);
				pathTransition.play();
				moveCircleOnKeyPress(scene2, circle);

			}
		});
		
		menuButtons.get(4).setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getButton().equals(MouseButton.PRIMARY))
				{
					mainStage.close();
				}
			}
			
		});
		
	
	}
	
	}
	
