package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomCircle;

import java.util.Random;


public class ViewgameManager {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private static final int GAME_WIDTH=600;
	private static final int GAME_HEIGHT=400;
	private Stage menuStage;
	private CustomCircle snake0;
	private boolean LeftKeyPressed;
	private boolean RightKeyPressed;
	private AnimationTimer	 gameTimer;
	Image img1 = new Image("model/resources/bolt_gold.png");
	Image img2 = new Image("model/resources/fruit.png");
	Image img3 = new Image("model/resources/shield_gold.png");
	private ImageView[]	power;
	private ImageView[] fruit;
	private ImageView[] shield;
	Random randno;
	public ViewgameManager() {
		// TODO Auto-generated constructor stub
		initialiseStage();
		createKeyListeners();
		randno=new Random();
	}
	private void createKeyListeners() {
		// TODO Auto-generated method stub
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode()== KeyCode.LEFT)
				{LeftKeyPressed=true;}
				else if (event.getCode()==KeyCode.RIGHT)
				{RightKeyPressed=true;}
			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode()== KeyCode.LEFT)
				{LeftKeyPressed=false;}
				else if (event.getCode()==KeyCode.RIGHT)
				{RightKeyPressed=false;}
			}
		});
	}
	private void createGameElements() {
		power = new ImageView[3];
		for (int i = 0; i < power.length; i++) {
			power[i] = new ImageView(img1);
			setNewPosition( power[i]);
			gamePane.getChildren().add(power[i]);
		}

		shield = new ImageView[3];
		for (int i = 0; i < shield.length; i++) {
			shield[i] = new ImageView(img2);
			setNewPosition( shield[i]);
			gamePane.getChildren().add(shield[i]);
		}

		fruit = new ImageView[3];
		for (int i = 0; i < fruit.length; i++) {
			fruit[i] = new ImageView(img3);
			setNewPosition( fruit[i]);
			gamePane.getChildren().add(fruit[i]);
		}
	}
	private void moveGameElements()
	{
		for (int i=0;i<power.length;i++)
		{
			power[i].setLayoutY(power[i].getLayoutY()+7);

		}
		for (int i=0;i<shield.length;i++)
		{
			shield[i].setLayoutY(shield[i].getLayoutY()+7);

		}
		for (int i=0;i<fruit.length;i++)
		{
			fruit[i].setLayoutY(fruit[i].getLayoutY()+7);

		}
	}
	private void elementvalid()
	{
		for (int i=0;i<power.length;i++)
		{
			if (power[i].getLayoutY()>900)
			{
				setNewPosition(power[i]);
			}
		}

		for (int i=0;i<shield.length;i++)
		{
			if (shield[i].getLayoutY()>900)
			{
				setNewPosition(shield[i]);
			}
		}
		for (int i=0;i<fruit.length;i++)
		{
			if (fruit[i].getLayoutY()>900)
			{
				setNewPosition(fruit[i]);
			}
		}
	}
	private void setNewPosition(ImageView image)
	{
		image.setLayoutX(randno.nextInt(170));
		image.setLayoutY(-randno.nextInt(3200)+600);

	}
	private void initialiseStage() {
		// TODO Auto-generated method stub
		gamePane=new AnchorPane();
		gameScene=new Scene(gamePane,GAME_WIDTH,GAME_HEIGHT);
		gameStage=new Stage();
		gameStage.setScene(gameScene);
		
	}
	public void createNewGame(Stage menuStage,CustomCircle s)
	{
		this.menuStage=menuStage;
		this.menuStage.hide();
		createSnake(s);
		createGameElements();

		createGameLoop();
		gameStage.show();
	}
	private void createSnake(CustomCircle s)
	{
		s.setLayoutX(GAME_WIDTH/2);
		s.setLayoutY(GAME_HEIGHT-90);
		gamePane.getChildren().add(s);
	}
	private void createGameLoop()
	{
		gameTimer=new AnimationTimer() {
			@Override
			public void handle(long now) {
				moveGameElements();
				elementvalid();
				moveSnake();
			}
	};
		gameTimer.start();

	}
	private void moveSnake()
	{ //to be added
	}

}
