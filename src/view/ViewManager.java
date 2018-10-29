package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
	ArrayList<SpaceRunnerButton> menuButtons;
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
	
		mainPane.getChildren().add(ss);
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
	

