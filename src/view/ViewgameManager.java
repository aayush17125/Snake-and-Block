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
import model.SmallInfoLabel;

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
	private Image img1 = new Image("model/resources/bolt_gold.png");
	private Image img2 = new Image("model/resources/fruit.png");
	private Image img3 = new Image("model/resources/shield_gold.png");
	private SmallInfoLabel pointsLabel;
	private int points;
	private int snakeLength;
	private ImageView star;
	private final static String StarString="view/resources/star_gold.png";
	private ImageView[]	power;
	private ImageView[] fruit;
	private ImageView[] shield;
	private final static int STAR_RADIUS=12;


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
		star=new ImageView(StarString);
		setNewPosition(star);
		gamePane.getChildren().add(star);
		pointsLabel=new SmallInfoLabel("POINTS:00");
		pointsLabel.setLayoutX(460);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);



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
	{	star.setLayoutY(star.getLayoutY()+5);


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

	{	if(star.getLayoutY()>120){
		setNewPosition(star);
	}

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
		createGameElements(	);

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
	private void checkIfElementsCollided()
	{
		if(snake_radius+STAR_RADIUS>calculateDistance(snake0.getLayoutX()+49,star.getLayoutX()+15,star.getLayoutY()+37,snake0.getLayoutY()+15)){
			setNewPosition(star);
			points++;
			String textToset="POINTS : ";
			if (points<	10)
			{
				textToset=textToset+"0";


			}
			pointsLabel.setText(textToset+points);

		}
		for ()
		{
			meteor k saath same :
			changeLength();
			setNewPosition();
		}
	}
	private void changeLength()
	{
//		gamePane.getChildren().remove(snakearray ka last daalna h yha pl0x);
		snakeLength--;
		if (snakeLength<0)
		{
			gameStage.close();
			gameTimer.stop();
			menuStage.show();

		}
	}
	private double calculateDistance(double x1,double x2,double y1,double y2){
		return Math.sqrt( Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
	}

}
