package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.sun.jndi.cosnaming.CNNameParser;

import backend.Leaderboard;
import javafx.animation.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import model.*;

public class ViewManager {
	private static final int HEIGHT = 720;
	private static final int WIDTH = 1024;
	private static final int START_X = 100;
	private static final int START_Y = 150;
	private static final int radius=15;
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
	private MediaPlayer bomb;
	private MediaPlayer coin;
	private MediaPlayer current;
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
	private Timeline time10;
	private boolean paused;
	private boolean firstTime;
	private CustomRectangle ss;
	private CusRectangle ui;
	private CusRectangle ud;
	private CusRectangle ub;
	private CusRectangle uc;
	private Image img1;
	private Image img2;
	private Image img3;
	private Image img4;
	private ListView leaderboard = new ListView();
	private InfoLabel Help_label=new InfoLabel("1.Use mouse to control the snake\n2.Get through all the obstacles\n3.You can save the game also");
	private InfoLabel Credit_Label=new InfoLabel("Akhil Jarodia(2017130)\nAayush Gupta(2017125)");
	private InfoLabel Main_Label=new InfoLabel("----------->\nHey there\nPress the play button to play\n<--------");
	private SpaceRunnerButton pauseButton=new SpaceRunnerButton("PAUSE");
	private Leaderboard l = new Leaderboard();
	private SpaceRunnerButton resumeMain=new SpaceRunnerButton("RESUME");
	public ViewManager()
	{
		menuButtons=new ArrayList<SpaceRunnerButton>();
		snakeBody=new ArrayList<CustomCircle>();
		snakeBody.add(circle);
		circle.setRadius(radius);
		firstTime = false;
		powerList = new ArrayList<CusRectangle>();
		obstacleWall = new ArrayList<Rectangle>();
		mainPane=new AnchorPane();
		mainScene=new Scene(mainPane,WIDTH,HEIGHT);
		pointsLabel=new SmallInfoLabel("POINTS:00");
		pointsLabel.setLayoutX(850);
		pointsLabel.setLayoutY(0);
		img1 = new Image("model/resources/bolt_gold.png");
		img2 = new Image("model/resources/fruit.png");
	    img3 = new Image("model/resources/shield_gold.png");
	    img4 = new Image("model/resources/nuke.png");
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
		time10 = new Timeline();
		mainStage =new Stage();
		mainStage.setScene(mainScene);
		paused = true;
		createButtons();
		createlogo();
		initialiseButtonListeners();
		ss=new CustomRectangle();
		ss.setLayoutX(310);
		ss.setLayoutY(210);
		ss.getPane().getChildren().addAll(leaderboard,Help_label,Credit_Label,Main_Label);
		leaderboard.setVisible(false);
		Help_label.setVisible(false);
		Credit_Label.setVisible(false);
		leaderboard.setPrefWidth(600);
		leaderboard.setPrefHeight(400);
		circle.setOpacity(0.7);//
		r2.getChildren().add(circle);//
		mainPane.getChildren().add(ss);
		scene2.setFill(Color.BLACK);
		createObstacleWall();
		ui= powerGenerator();
		ub=powerGenerator();
		uc =powerGenerator();
		ud=powerGenerator();
		ui.setLayoutX(rand_x.nextInt(WIDTH));
		ui.setLayoutY(0);
		ub.setLayoutX(rand_x.nextInt(WIDTH));
		ub.setLayoutY(0);
		uc.setLayoutX(rand_x.nextInt(WIDTH));
		uc.setLayoutY(0);
		ud.setLayoutX(50);
		ud.setLayoutY(40);
		powerList.add(ui);
		powerList.add(ub);
		powerList.add(uc);
		powerList.add(ud);
		r2.getChildren().addAll(ui,ub,uc,ud);
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
        refreshLeaderboard();
        this.mainPane.getChildren().add(resumeMain);
        resumeMain.setLayoutX(WIDTH-300);
        this.snakeBody.get(0).setImage();
        
	}

	private void refreshLeaderboard() {
		// TODO Auto-generated method stub
//		leaderboard=new ListView();
		for(int i=0;i<l.getScore().size();i++)
		{
			leaderboard.getItems().remove(l.getScore().get(i));
		}
		for(String x:l.getScore())
		{
			leaderboard.getItems().add(x);
		}
	}
	private CusRectangle powerGenerator() {
		CusRectangle block= new CusRectangle(1, 0, 0);
		int t = rand_x.nextInt(110);
		if (t<70) {
			block = new CusRectangle(2,30,30);
			block.setLength(randomNum(10));
		}
		else if (t<=85) {
			block = new CusRectangle(1,19,30);
		}
		else if (t<=95) {
			block =new CusRectangle(3,30,36);
		}
		else {
			block=new CusRectangle(4,30,36);
		}
		return block;
		
	}
	private void respawnPower(CusRectangle block) {
		int t = rand_x.nextInt(110);
		if (t<70) {
			block.setType(2);
			block.setLength(randomNum(10));
		}
		else if (t<=85) {
			block.setType(1);
			block.setFill(new ImagePattern(img1));
		}
		else if (t<=95) {
			block.setType(3);
			block.setFill(new ImagePattern(img3));
		}
		else {
			block.setType(4);
			block.setFill(new ImagePattern(img4));
		}
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
		ImageView logo2=new ImageView("view/resources/maxresdefault.jpg");
		pausePane.getChildren().addAll(logo2);
		addPauseButton(new SpaceRunnerButton("RESTART"));
		addPauseButton(new SpaceRunnerButton("PLAY"));
		addPauseButton(new SpaceRunnerButton("EXIT"));
	}

	public void playMusic(){
		URL res = getClass().getResource("Song.mp3");
		mediaPlayer = new MediaPlayer(new Media(res.toString()));
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	public void playBomb() {
		URL rest = getClass().getResource("Bomb.mp3");
		bomb = new MediaPlayer(new Media(rest.toString()));
		bomb.setCycleCount(1);
		bomb.play();
	}
	public void playCoin() {
		URL rest = getClass().getResource("Coin.mp3");
		coin = new MediaPlayer(new Media(rest.toString()));
		coin.setCycleCount(1);
		coin.play();
	}
	public void playCurrent() {
		URL rest = getClass().getResource("Current.mp3");
		current = new MediaPlayer(new Media(rest.toString()));
		current.setCycleCount(1);
		current.play();
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
	{	
		if(snakeBody.size()==0)
		{
			q.setLayoutX(WIDTH/2);
			q.setLayoutY(HEIGHT-200+ snakeBody.size()*2*radius);
			snakeBody.add(q);
			r2.getChildren().add(q);
		}
		else {
			snakeBody.get(0).incLength();
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
		time10.play();
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
		time10.pause();
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
		time10.stop();
	}
	private void removeSnakeBody(Rectangle rectangle){
		if(snakeBody.size()>0 && !((numberRectangle) rectangle).isHit()) {
			((numberRectangle) rectangle).hit();
			for (int j=0;j<6;j++) {
				((numberRectangle)obstacleWall.get(j)).hit();
			}
			rectangle.setVisible(false);
			for (int i = 0; i < ((numberRectangle) rectangle).getNum(); i++){
				if (snakeBody.size()==1 || snakeBody.size()<=0) {
					System.out.println("Game Over");
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Alert!");
					alert.setHeaderText("Game Over");
					alert.setContentText("Score :"+points);
					alert.show();
					l.addScore(points);
					refreshLeaderboard();
					stopMovement();
					mediaPlayer.stop();
					mainStage.setScene(mainScene);
					break;
				}
				
				removeLastSnake();
				points++;
				pointsLabel.setText("POINTS:0"+Integer.toString(points));			
			}
		}
	}
	private int randomNum(int q) {
		int t = rand_x.nextInt(q);
		while(t<=3) {
			t = rand_x.nextInt(q);
		}
		return t;
	}
	private void removeLastSnake() {
		for (int i=0;i<r2.getChildren().size();i++) {
			if (r2.getChildren().get(i)==snakeBody.get(snakeBody.size()-1)) {
				snakeBody.get(0).decLength(1);
				r2.getChildren().remove(i);
				snakeBody.remove(snakeBody.size()-1);
				break;
			}
		}
	}
	private void restart() {
		scene2.setFill(Color.BLACK);
		powerList.get(0).setLayoutX(rand_x.nextInt(WIDTH));
		powerList.get(0).setLayoutY(0);
		powerList.get(1).setLayoutX(rand_x.nextInt(WIDTH));
		powerList.get(1).setLayoutY(0);
		powerList.get(2).setLayoutX(rand_x.nextInt(WIDTH));
		powerList.get(2).setLayoutY(0);
		powerList.get(3).setLayoutX(50);
		powerList.get(3).setLayoutY(40);
		points = 0;
		int[] num = new int[7];
		int q=0;
		for (int i=0;i<r2.getChildren().size();i++) {
			for (int j=0;j<obstacleWall.size();j++) {
				try {
					if (r2.getChildren().get(i)==(obstacleWall.get(j))) {
						num[q] = i;
						q++;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		for (int i=0;i<7;i++) {
			r2.getChildren().remove(obstacleWall.get(i));
		}
		for (int i=0;i<r2.getChildren().size();i++) {
			if (r2.getChildren().get(i)==circle) {
				r2.getChildren().remove(i);
			}
		}
		try {
			for (int i=0;i<snakeBody.size();i++) {
				r2.getChildren().remove(snakeBody.get(i));
			}
			r2.getChildren().remove(ui);
			r2.getChildren().remove(ub);
			r2.getChildren().remove(uc);
			r2.getChildren().remove(ud);
		} catch (Exception e) {
			// TODO: handle exception
		}
		snakeBody = new ArrayList<CustomCircle>();
		snakeBody.add(circle);
		circle.setOpacity(0.7);//
		r2.getChildren().add(circle);//
		obstacleWall = new ArrayList<Rectangle>();
		ui= powerGenerator();
		ub=powerGenerator();
		uc =powerGenerator();
		ud=powerGenerator();
	    ui.setLayoutX(rand_x.nextInt(WIDTH));
		ui.setLayoutY(0);
		ub.setLayoutX(rand_x.nextInt(WIDTH));
		ub.setLayoutY(0);
		uc.setLayoutX(rand_x.nextInt(WIDTH));
		uc.setLayoutY(0);
		ud.setLayoutX(50);
		ud.setLayoutY(40);
		RotateTransition rt = new RotateTransition(Duration.millis(3000), ui);
		ui.setRotationAxis(Rotate.Y_AXIS);
		rt.setByAngle(360);
	    rt.setCycleCount(Animation.INDEFINITE);
	    rt.setAutoReverse(false);
	    rt.play();
	    RotateTransition pt = new RotateTransition(Duration.millis(3000), ub);
	    ub.setRotationAxis(Rotate.Y_AXIS);
	    pt.setByAngle(360);
	    pt.setCycleCount(Animation.INDEFINITE);
	    pt.setAutoReverse(false);
	    pt.play();
	    RotateTransition qt = new RotateTransition(Duration.millis(3000), uc);
	    uc.setRotationAxis(Rotate.Y_AXIS);
	    qt.setByAngle(360);
	    qt.setCycleCount(Animation.INDEFINITE);
	    qt.setAutoReverse(false);
	    qt.play();
	    RotateTransition st = new RotateTransition(Duration.millis(3000), ud);
	    ud.setRotationAxis(Rotate.Y_AXIS);
	    st.setByAngle(360);
	    st.setCycleCount(Animation.INDEFINITE);
	    st.setAutoReverse(false);
	    st.play();
		powerList = new ArrayList<CusRectangle>();
		powerList.add(ui);
		powerList.add(ub);
		powerList.add(uc);
		powerList.add(ud);
		r2.getChildren().addAll(ui,ub,uc,ud);
		createObstacleWall();
		createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        createSnakeBody();
        l.addScore(6);
        points=0;
        pointsLabel.setText("POINTS:0"+Integer.toString(points));
		initialiseButtonListeners();
        refreshLeaderboard();
		paused = true;
		mainStage.setScene(scene2);
		playMusic();
		time0.setDelay(Duration.millis(450));
		time0.setCycleCount(Animation.INDEFINITE);
		KeyFrame key0 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(0).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(0);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(0).yProperty(),HEIGHT));
		time0.getKeyFrames().add(key0);

		time1.setDelay(Duration.millis(450));
		time1.setCycleCount(Animation.INDEFINITE);
		KeyFrame key1 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(1).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(1);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(1).yProperty(),HEIGHT));
		time1.getKeyFrames().add(key1);

		time2.setDelay(Duration.millis(450));
		time2.setCycleCount(Animation.INDEFINITE);
		KeyFrame key2 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(2).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(2);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(2).yProperty(),HEIGHT));
		time2.getKeyFrames().add(key2);

		time3.setDelay(Duration.millis(450));
		time3.setCycleCount(Animation.INDEFINITE);
		KeyFrame key3 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(3).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(3);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(3).yProperty(),HEIGHT));
		time3.getKeyFrames().add(key3);

		time4.setDelay(Duration.millis(450));
		time4.setCycleCount(Animation.INDEFINITE);
		KeyFrame key4 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(4).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(4);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(4).yProperty(),HEIGHT));
		time4.getKeyFrames().add(key4);

		time5.setDelay(Duration.millis(450));
		time5.setCycleCount(Animation.INDEFINITE);
		KeyFrame key5 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(5).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(5);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(5).yProperty(),HEIGHT));
		time5.getKeyFrames().add(key5);

		time6.setCycleCount(Animation.INDEFINITE);
		KeyFrame key6 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(6).setY(-200);
			int h = rand_x.nextInt(HEIGHT-300);
			while (h<200) {
				h = rand_x.nextInt(HEIGHT-300);
			}
			obstacleWall.get(6).setHeight(h);
			obstacleWall.get(6).setX(rand_x.nextInt(WIDTH));
		},new KeyValue(obstacleWall.get(6).yProperty(),HEIGHT));
		time6.getKeyFrames().add(key6);

		time7.setDelay(Duration.millis(450));
		time7.setCycleCount(Animation.INDEFINITE);
		KeyFrame key7 = new KeyFrame(Duration.millis(3100),e->{
			respawnPower(powerList.get(0));
			powerList.get(0).setLayoutY(0);
			powerList.get(0).refresh();
			powerList.get(0).setLayoutX(rand_x.nextInt(WIDTH));
		},new KeyValue(powerList.get(0).layoutYProperty(),HEIGHT));
		time7.getKeyFrames().add(key7);

		time8.setDelay(Duration.millis(450));
		time8.setCycleCount(Animation.INDEFINITE);
		KeyFrame key8 = new KeyFrame(Duration.millis(3100),e->{
			respawnPower(powerList.get(1));
			powerList.get(1).setLayoutY(0);;
			powerList.get(1).refresh();
			powerList.get(1).setLayoutX(rand_x.nextInt(WIDTH));
		},new KeyValue(powerList.get(1).layoutYProperty(),HEIGHT));
		time8.getKeyFrames().add(key8);

		time9.setDelay(Duration.millis(450));
		time9.setCycleCount(Animation.INDEFINITE);
		KeyFrame key9 = new KeyFrame(Duration.millis(3100),e->{
			respawnPower(powerList.get(2));
			powerList.get(2).setLayoutY(0);
			powerList.get(2).refresh();
			powerList.get(2).setLayoutX(rand_x.nextInt(WIDTH));
		},new KeyValue(powerList.get(2).layoutYProperty(),HEIGHT));
		time9.getKeyFrames().add(key9);
		
		time10.setDelay(Duration.millis(450));
		time10.setCycleCount(Animation.INDEFINITE);
		KeyFrame key10 = new KeyFrame(Duration.millis(3100),e->{
			respawnPower(powerList.get(3));
			powerList.get(3).setLayoutY(0);
			powerList.get(3).refresh();
			powerList.get(3).setLayoutX(rand_x.nextInt(WIDTH));
		},new KeyValue(powerList.get(3).layoutYProperty(),HEIGHT));
		time10.getKeyFrames().add(key10);
		startMovement();
	}
	private int shouldMove()
	{
		double dist=Math.sqrt((Math.pow(obstacleWall.get(6).getX()-snakeBody.get(0).getCenterX(), 2))+(Math.pow(obstacleWall.get(6).getY()-snakeBody.get(0).getCenterY(), 2)));
		System.out.println(dist+" hello");
		if(dist<100 )
		{
			if(obstacleWall.get(6).getX()>snakeBody.get(0).getCenterX())
			{
				return 1;
			}
			else
			{
				return 2;
			}
		}
		
		return 0;
	}
	private void start() {
		paused = false;
		mainStage.setScene(scene2);
		RotateTransition rt = new RotateTransition(Duration.millis(3000), ui);
		ui.setRotationAxis(Rotate.Y_AXIS);
		rt.setByAngle(360);
	    rt.setCycleCount(Animation.INDEFINITE);
	    rt.setAutoReverse(false);
	    rt.play();
	    RotateTransition pt = new RotateTransition(Duration.millis(3000), ub);
	    ub.setRotationAxis(Rotate.Y_AXIS);
	    pt.setByAngle(360);
	    pt.setCycleCount(Animation.INDEFINITE);
	    pt.setAutoReverse(false);
	    pt.play();
	    RotateTransition qt = new RotateTransition(Duration.millis(3000), uc);
	    uc.setRotationAxis(Rotate.Y_AXIS);
	    qt.setByAngle(360);
	    qt.setCycleCount(Animation.INDEFINITE);
	    qt.setAutoReverse(false);
	    qt.play();
	    RotateTransition st = new RotateTransition(Duration.millis(3000), ud);
	    ud.setRotationAxis(Rotate.Y_AXIS);
	    st.setByAngle(360);
	    st.setCycleCount(Animation.INDEFINITE);
	    st.setAutoReverse(false);
	    st.play();
		playMusic();
		time0.setDelay(Duration.millis(450));
		time0.setCycleCount(Animation.INDEFINITE);
		KeyFrame key0 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(0).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(0);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(0).yProperty(),HEIGHT));
		time0.getKeyFrames().add(key0);

		time1.setDelay(Duration.millis(450));
		time1.setCycleCount(Animation.INDEFINITE);
		KeyFrame key1 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(1).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(1);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(1).yProperty(),HEIGHT));
		time1.getKeyFrames().add(key1);

		time2.setDelay(Duration.millis(450));
		time2.setCycleCount(Animation.INDEFINITE);
		KeyFrame key2 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(2).setY(-200);
			
			numberRectangle temp = (numberRectangle) obstacleWall.get(2);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(2).yProperty(),HEIGHT));
		time2.getKeyFrames().add(key2);

		time3.setDelay(Duration.millis(450));
		time3.setCycleCount(Animation.INDEFINITE);
		KeyFrame key3 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(3).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(3);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(3).yProperty(),HEIGHT));
		time3.getKeyFrames().add(key3);

		time4.setDelay(Duration.millis(450));
		time4.setCycleCount(Animation.INDEFINITE);
		KeyFrame key4 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(4).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(4);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(4).yProperty(),HEIGHT));
		time4.getKeyFrames().add(key4);

		time5.setDelay(Duration.millis(450));
		time5.setCycleCount(Animation.INDEFINITE);
		KeyFrame key5 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(5).setY(-200);
			numberRectangle temp = (numberRectangle) obstacleWall.get(5);
			temp.setNum(randomNum(20));
			temp.refresh();
		},new KeyValue(obstacleWall.get(5).yProperty(),HEIGHT));
		time5.getKeyFrames().add(key5);

		time6.setCycleCount(Animation.INDEFINITE);
		KeyFrame key6 = new KeyFrame(Duration.millis(4000),e->{
			obstacleWall.get(6).setY(-200);
			int h = rand_x.nextInt(HEIGHT-300);
			while (h<200) {
				h = rand_x.nextInt(HEIGHT-300);
			}
			obstacleWall.get(6).setHeight(h);
			obstacleWall.get(6).setX(rand_x.nextInt(WIDTH));
		},new KeyValue(obstacleWall.get(6).yProperty(),HEIGHT));
		time6.getKeyFrames().add(key6);


		time7.setDelay(Duration.millis(450));
		time7.setCycleCount(Animation.INDEFINITE);
		KeyFrame key7 = new KeyFrame(Duration.millis(3100),e->{
			respawnPower(powerList.get(0));
			powerList.get(0).setLayoutY(0);
			powerList.get(0).refresh();
			powerList.get(0).setLayoutX(rand_x.nextInt(WIDTH));
		},new KeyValue(powerList.get(0).layoutYProperty(),HEIGHT));
		time7.getKeyFrames().add(key7);

		time8.setDelay(Duration.millis(450));
		time8.setCycleCount(Animation.INDEFINITE);
		KeyFrame key8 = new KeyFrame(Duration.millis(3100),e->{
			respawnPower(powerList.get(1));
			powerList.get(1).setLayoutY(0);
			powerList.get(1).refresh();
			powerList.get(1).setLayoutX(rand_x.nextInt(WIDTH));
		},new KeyValue(powerList.get(1).layoutYProperty(),HEIGHT));
		time8.getKeyFrames().add(key8);

		time9.setDelay(Duration.millis(450));
		time9.setCycleCount(Animation.INDEFINITE);
		KeyFrame key9 = new KeyFrame(Duration.millis(3100),e->{
			respawnPower(powerList.get(2));
			powerList.get(2).setLayoutY(0);
			powerList.get(2).refresh();
			powerList.get(2).setLayoutX(rand_x.nextInt(WIDTH));
		},new KeyValue(powerList.get(2).layoutYProperty(),HEIGHT));
		time9.getKeyFrames().add(key9);
		startMovement();
		
		time10.setDelay(Duration.millis(450));
		time10.setCycleCount(Animation.INDEFINITE);
		KeyFrame key10 = new KeyFrame(Duration.millis(3100),e->{
			respawnPower(powerList.get(3));
			powerList.get(3).setLayoutY(0);
			powerList.get(3).refresh();
			powerList.get(3).setLayoutX(rand_x.nextInt(WIDTH));
		},new KeyValue(powerList.get(3).layoutYProperty(),HEIGHT));
		time10.getKeyFrames().add(key10);
		startMovement();
	}
	private void initialiseButtonListeners() {
		scene2.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ArrayList<Double> prev = new ArrayList<Double>();
				try {if(shouldMove()==0) {
					snakeBody.get(0).setCenterX(event.getX());
					
				}
				else
				{
					if(shouldMove()==1 && event.getX()-snakeBody.get(0).getCenterX()<0)
					{
						snakeBody.get(0).setCenterX(event.getX());
					}
					else if(shouldMove()==2 && event.getX()-snakeBody.get(0).getCenterX()>0)
					{
						snakeBody.get(0).setCenterX(event.getX());
					}
				}
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
						if (snakeBody.get(0).intersects(obstacleWall.get(i).getBoundsInParent())){
							removeSnakeBody(obstacleWall.get(i));
						}
					}catch (Exception e) {
						stop();
					}
				}
				if (snakeBody.get(0).intersects(obstacleWall.get(6).getBoundsInParent())) {
//					System.out.println("HN BSDK");
				}
				for (int i=0;i<powerList.size();i++) {
					try {
						if (snakeBody.get(0).intersects(powerList.get(i).getBoundsInParent()) && (!(powerList.get(i).isHit()))) {
							System.out.println("coll");
							powerList.get(i).hit();
							powerList.get(i).setVisible(false);
							if (powerList.get(i).getType()==1) {
								playCurrent();
								for (int j=0;j<powerList.size();j++) {
									if (powerList.get(j).getType()==2) {
										powerList.get(j).setVisible(false);
										for (int k=0;k<powerList.get(j).getLength();k++) {
											createSnakeBody();
										}
									}
								}
							}
							else if (powerList.get(i).getType()==2) {
								playCoin();
								for (int j=0;j<powerList.get(i).getLength();j++) {
									createSnakeBody();
								}
								powerList.get(i).setVisible(false);
							}
							else if (powerList.get(i).getType()==3) {
								System.out.println("BHENCHOD MERI FUNCTIONALITY TERA BAAP DAALEGA :'( ");
							}
							else if (powerList.get(i).getType()==4) {
								playBomb();
								for (int j=0;j<6;j++) {
									numberRectangle rect = (numberRectangle) obstacleWall.get(j);
									rect.hit();
									rect.setVisible(false);
									points += rect.getNum();
									pointsLabel.setText("POINTS:"+Integer.toString(points));
								}
								
							}
							
						}
					}catch (Exception e) {
						// TODO: handle exception
						stop();
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
				if (!firstTime) {
					start();
					firstTime = true;
				}
				else {
					restart();
				}
				
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
				if (paused){
					paused = false;
					startMovement();
					mediaPlayer.play();
				}


			}
		});
		pauseList.get(2).setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setScene(mainScene);
				stopMovement();
			}
		});
		pauseList.get(0).setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setScene(mainScene);
				stopMovement();
				restart();
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
	

