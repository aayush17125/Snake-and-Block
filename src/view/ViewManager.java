package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

import static javafx.scene.input.KeyCode.*;

public class ViewManager {
	private static final int HEIGHT = 720;
	private static final int WIDTH = 1024;
	private static final int START_X = 100;
	private static final int START_Y = 150;
	private static final int radius=10;
	AnimationTimer gameTimer;
	private SmallInfoLabel pointsLabel;
	private int points;
	private AnchorPane mainPane;
	private AnchorPane pausePane=new AnchorPane();
	private Scene pauseScene=new Scene(pausePane,WIDTH,HEIGHT);
	private Scene mainScene;
	private Stage mainStage;
	private ArrayList<SpaceRunnerButton> menuButtons;
	private ArrayList<Rectangle> obstacleWall ;
	private Group r = new Group();
	private Group r2 = new Group();
	private Scene scene2 = new Scene(r2,WIDTH,HEIGHT);
	private ArrayList<CustomCircle> snakeBody;
	private CustomCircle circle=new CustomCircle(WIDTH/2, HEIGHT-200, radius,scene2);
    private Random rand_x = new Random();
	private MediaPlayer mediaPlayer;
    private ArrayList <CusRectangle> powerList;
    private ArrayList<SpaceRunnerButton> pauseList=new ArrayList<SpaceRunnerButton>();
	private static final int KEYBOARD_MOVEMENT_DELTA = 12;
	private Timeline time0;
	private Timeline time1;
	private Timeline time2;
	private Timeline time3;
	private Timeline time4;
	private Timeline time5;
	private Timeline time6;
	private Timeline time7;
	private Timeline time8;
	private Timeline time9;
	private boolean paused;
	private CustomRectangle ss;
	private TableView leaderboard = new TableView();
	private InfoLabel Help_label=new InfoLabel("1.Use mouse to control the snake\n2.Get through all the obstacles\n3.You can save the game also");
	private InfoLabel Credit_Label=new InfoLabel("Akhil Jarodia(2017130)\nAayush Gupta(2017125)");
	private SpaceRunnerButton pauseButton=new SpaceRunnerButton("PAUSE");;
	public ViewManager()
	{
		menuButtons=new ArrayList<SpaceRunnerButton>();
		snakeBody=new ArrayList<CustomCircle>();
		snakeBody.add(circle);
		powerList = new ArrayList<CusRectangle>();
		obstacleWall = new ArrayList<Rectangle>();
		mainPane=new AnchorPane();
		mainScene=new Scene(mainPane,WIDTH,HEIGHT);
		pointsLabel=new SmallInfoLabel("POINTS:00");
		pointsLabel.setLayoutX(900);
		pointsLabel.setLayoutY(0);
		
		time0 = new Timeline();
		time1 = new Timeline();
		time2 = new Timeline();
		time3 = new Timeline();
		time4 = new Timeline();
		time5 = new Timeline();
		time6 = new Timeline();
		time7 = new Timeline();
		time8 = new Timeline();
		time9 = new Timeline();
		mainStage =new Stage();
		mainStage.setScene(mainScene);
		paused = true;
		createButtons();
		createlogo();
		initialiseButtonListeners();
		ss=new CustomRectangle();
		ss.setLayoutX(310);
		ss.setLayoutY(210);
		ss.getPane().getChildren().addAll(leaderboard,Help_label,Credit_Label);
		circle.setOpacity(0.7);//
		r2.getChildren().add(circle);//
		mainPane.getChildren().add(ss);
		scene2.setFill(Color.BLACK);
		createObstacleWall();
		CusRectangle ui= new CusRectangle(1,19,30);
		CusRectangle ub=new CusRectangle(2,30,30);
		CusRectangle uc =new CusRectangle(3,30,36);
		ui.setLayoutX(rand_x.nextInt(WIDTH));
		ui.setLayoutY(0);
		ub.setLayoutX(rand_x.nextInt(WIDTH));
		ub.setLayoutY(0);
		uc.setLayoutX(rand_x.nextInt(WIDTH));
		uc.setLayoutY(0);
		powerList.add(ui);
		powerList.add(ub);
		powerList.add(uc);
		r2.getChildren().addAll(ui,ub,uc);
//		pauseButton=new SpaceRunnerButton("PAUSE");
		pauseButton.setLayoutX(200);
		pauseButton.setLayoutY(0);
		r2.getChildren().add(pauseButton);
		r2.getChildren().add(pointsLabel);
		points = 0;
		createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();	
        
	}

	private void createObstacleWall(){

		for (int i=0;i<6;i++){

			numberRectangle tempRect = new numberRectangle(i*(int)(WIDTH/6),-200,WIDTH/6,100,1);
			r2.getChildren().add(tempRect);
			obstacleWall.add(tempRect);
		}
		Rectangle obsWall = new Rectangle(rand_x.nextInt(WIDTH), -200,5,rand_x.nextInt(HEIGHT-100-(int)obstacleWall.get(0).getY()) );
		obstacleWall.add(obsWall);
        obsWall.setFill(Color.WHITE);
        r2.getChildren().add(obsWall);
	}
	private void createPauseMenu()
	{
		addPauseButton(new SpaceRunnerButton("SAVE"));
		addPauseButton(new SpaceRunnerButton("PLAY"));
		addPauseButton(new SpaceRunnerButton("EXIT"));
	}

	public void playMusic(){
		URL res = getClass().getResource("Song.mp3");
		mediaPlayer = new MediaPlayer(new Media(res.toString()));
		mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
		mediaPlayer.play();
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
		createPauseMenu();
	}
	private void createSnakeBody()
	{
		addSnakeBody(new CustomCircle(WIDTH/2, HEIGHT-200, radius,scene2));
	}
	private void addSnakeBody(CustomCircle q)
	{	if(snakeBody.size()==0)
		{
			q.setLayoutX(WIDTH/2);
			q.setLayoutY(HEIGHT-200+ snakeBody.size()*2*radius);
			snakeBody.add(q);
			r2.getChildren().add(q);
		}
		else {
			q.setLayoutX(snakeBody.get(snakeBody.size()-1).getLayoutX());
			q.setLayoutY(snakeBody.get(snakeBody.size()-1).getLayoutY()+2*radius);
			snakeBody.add(q);
			r2.getChildren().add(q);
		}

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
	private void addPauseButton(SpaceRunnerButton button)
	{
		button.setLayoutX(START_X);
		button.setLayoutY(START_Y+ pauseList.size()*100);
		pauseList.add(button);
		pausePane.getChildren().add(button);
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
	private void startMovement(){
		time0.play();
		time1.play();
		time2.play();
		time3.play();
		time4.play();
		time5.play();
		time6.play();
		time7.play();
		time8.play();
		time9.play();

	}
	private void pauseMovement(){
		time0.pause();
		time1.pause();
		time2.pause();
		time3.pause();
		time4.pause();
		time5.pause();
		time6.pause();
		time7.pause();
		time8.pause();
		time9.pause();
	}
	private void stopMovement(){
		time0.stop();
		time1.stop();
		time2.stop();
		time3.stop();
		time4.stop();
		time5.stop();
		time6.stop();
		time7.stop();
		time8.stop();
		time9.stop();
	}
	private void removeSnakeBody(Rectangle rectangle)
	{
		if(snakeBody.size()>0 && !((numberRectangle) rectangle).isHit()) {
			rectangle.setVisible(false);
			((numberRectangle) rectangle).hit();
			System.out.println(((numberRectangle) rectangle).getNum()); 
			for (int i = 0; i < ((numberRectangle) rectangle).getNum(); i++){
				if (snakeBody.size()==0) {
					System.out.println("Game Over");
					stopMovement();
					break;
				}
//				CustomCircle temp=snakeBody.get(snakeBody.size()-1);
//				r2.getChildren().remove(r2.getChildren().size()-1);
//				snakeBody.remove(snakeBody.size()-1);
				removeLastSnake();
				points++;
				pointsLabel.setText("POINTS:0"+Integer.toString(points));
			}
		}

	}
	private void removeLastSnake() {
		for (int i=0;i<r2.getChildren().size();i++) {
			if (r2.getChildren().get(i)==snakeBody.get(snakeBody.size()-1)) {
				r2.getChildren().remove(i);
				snakeBody.remove(snakeBody.size()-1);
				break;
			}
		}
	}
	private void initialiseButtonListeners() {
		scene2.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ArrayList<Double> prev = new ArrayList<Double>();
				try {
					snakeBody.get(0).setCenterX(event.getX());
					for (int i=0;i<snakeBody.size()-1;i++) {
						prev.add(snakeBody.get(i).getCenterX());
					}
					for (int i=1;i<snakeBody.size();i++) {
						snakeBody.get(i).setCenterX(prev.get(i-1));
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		});

		pauseButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setScene(pauseScene);
				pauseMovement();
				mediaPlayer.pause();
				paused = true;
			}
		});
		
		gameTimer=new AnimationTimer() {
			@Override
			public void handle(long now) {
				for (int i=0;i<6;i++){
					try {
						if (snakeBody.get(0).intersects(obstacleWall.get(i).getLayoutBounds())){
							removeSnakeBody(obstacleWall.get(i));
						}
					}catch (Exception e) {
						stop();
					}
				}
				for (int i=0;i<powerList.size();i++) {
					try {
						if (snakeBody.get(0).intersects(powerList.get(i).getLayoutBounds())) {
							System.out.println("coll"); 	
							if (powerList.get(i).getType()==1) {
								System.out.println("type1");
							}
							else if (powerList.get(i).getType()==2) {
								for (int j=0;j<rand_x.nextInt(15);j++) {
									System.out.println("ype2");
									powerList.get(i).setVisible(false);
									createSnakeBody();
								}
							}
							else if (powerList.get(i).getType()==3) {
								System.out.println("type3");	
							}
							
						}
					}catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getStackTrace());
					}
				}

			}
		};
		gameTimer.start();

		menuButtons.get(0).setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				paused = false;
				mainStage.setScene(scene2);
				playMusic();
				time0.setDelay(Duration.millis(450));
				time0.setCycleCount(Animation.INDEFINITE);
				KeyFrame key0 = new KeyFrame(Duration.millis(4000),e->{
					obstacleWall.get(0).setY(-200);
					numberRectangle temp = (numberRectangle) obstacleWall.get(0);
					temp.setNum(rand_x.nextInt(20));
					temp.refresh();
				},new KeyValue(obstacleWall.get(0).yProperty(),HEIGHT));
				time0.getKeyFrames().add(key0);

				time1.setDelay(Duration.millis(450));
				time1.setCycleCount(Animation.INDEFINITE);
				KeyFrame key1 = new KeyFrame(Duration.millis(4000),e->{
					obstacleWall.get(1).setY(-200);
					numberRectangle temp = (numberRectangle) obstacleWall.get(1);
					temp.setNum(rand_x.nextInt(20));
					temp.refresh();
				},new KeyValue(obstacleWall.get(1).yProperty(),HEIGHT));
				time1.getKeyFrames().add(key1);

				time2.setDelay(Duration.millis(450));
				time2.setCycleCount(Animation.INDEFINITE);
				KeyFrame key2 = new KeyFrame(Duration.millis(4000),e->{
					obstacleWall.get(2).setY(-200);
					
					numberRectangle temp = (numberRectangle) obstacleWall.get(2);
					temp.setNum(rand_x.nextInt(20));
					temp.refresh();
				},new KeyValue(obstacleWall.get(2).yProperty(),HEIGHT));
				time2.getKeyFrames().add(key2);

				time3.setDelay(Duration.millis(450));
				time3.setCycleCount(Animation.INDEFINITE);
				KeyFrame key3 = new KeyFrame(Duration.millis(4000),e->{
					obstacleWall.get(3).setY(-200);
//					((numberRectangle)obstacleWall.get(3)).refresh();
					numberRectangle temp = (numberRectangle) obstacleWall.get(3);
					temp.setNum(rand_x.nextInt(20));
					temp.refresh();
				},new KeyValue(obstacleWall.get(3).yProperty(),HEIGHT));
				time3.getKeyFrames().add(key3);

				time4.setDelay(Duration.millis(450));
				time4.setCycleCount(Animation.INDEFINITE);
				KeyFrame key4 = new KeyFrame(Duration.millis(4000),e->{
					obstacleWall.get(4).setY(-200);
//					((numberRectangle)obstacleWall.get(4)).refresh();
					numberRectangle temp = (numberRectangle) obstacleWall.get(4);
					temp.setNum(rand_x.nextInt(20));
					temp.refresh();
				},new KeyValue(obstacleWall.get(4).yProperty(),HEIGHT));
				time4.getKeyFrames().add(key4);

				time5.setDelay(Duration.millis(450));
				time5.setCycleCount(Animation.INDEFINITE);
				KeyFrame key5 = new KeyFrame(Duration.millis(4000),e->{
					obstacleWall.get(5).setY(-200);
					
					numberRectangle temp = (numberRectangle) obstacleWall.get(5);
					temp.setNum(rand_x.nextInt(20));
					temp.refresh();
				},new KeyValue(obstacleWall.get(5).yProperty(),HEIGHT));
				time5.getKeyFrames().add(key5);

				time6.setCycleCount(Animation.INDEFINITE);
				KeyFrame key6 = new KeyFrame(Duration.millis(4000),e->{
					obstacleWall.get(6).setY(-200);
//					((numberRectangle)obstacleWall.get(6)).refresh();
					obstacleWall.get(6).setHeight(rand_x.nextInt(HEIGHT-400));
					obstacleWall.get(6).setX(rand_x.nextInt(WIDTH));
				},new KeyValue(obstacleWall.get(6).yProperty(),HEIGHT));
				time6.getKeyFrames().add(key6);


				time7.setDelay(Duration.millis(450));
				time7.setCycleCount(Animation.INDEFINITE);
				KeyFrame key7 = new KeyFrame(Duration.millis(3100),e->{
					powerList.get(0).setY(0);
					powerList.get(0).setLayoutX(rand_x.nextInt(WIDTH));
				},new KeyValue(powerList.get(0).yProperty(),HEIGHT));
				time7.getKeyFrames().add(key7);

				time8.setDelay(Duration.millis(450));
				time8.setCycleCount(Animation.INDEFINITE);
				KeyFrame key8 = new KeyFrame(Duration.millis(3100),e->{
					powerList.get(1).setY(0);
					powerList.get(1).setLayoutX(rand_x.nextInt(WIDTH));
				},new KeyValue(powerList.get(1).yProperty(),HEIGHT));
				time8.getKeyFrames().add(key8);

				time9.setDelay(Duration.millis(450));
				time9.setCycleCount(Animation.INDEFINITE);
				KeyFrame key9 = new KeyFrame(Duration.millis(3100),e->{
					powerList.get(2).setY(0);
					powerList.get(2).setLayoutX(rand_x.nextInt(WIDTH));
				},new KeyValue(powerList.get(2).yProperty(),HEIGHT));
				time9.getKeyFrames().add(key9);
				startMovement();
			}
		});
	menuButtons.get(1).setOnMouseReleased(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			ss.topper(1);
		}
	});
	menuButtons.get(2).setOnMouseReleased(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			ss.topper(2);
		}
	});

	menuButtons.get(3).setOnMouseReleased(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			ss.topper(3);
		}
	});


		r2.getChildren().get(0).setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setScene(mainScene);
				stopMovement();
				mediaPlayer.stop();
			}
		});
		pauseList.get(1).setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setScene(scene2);
//				mediaPlayer.play();
				System.out.print(123);
				if (paused){
					paused = false;
					startMovement();
				}


			}
		});
		pauseList.get(2).setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.close();
			}
		});
		pauseList.get(0).setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("save");
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
	

