package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class SpaceRunnerButton extends Button{
	private final String FONT_PATH="src/model/resources/kenvector_future.ttf";
	private final String Butpress="-fx-background-color:transparent;-fx-background-image:url('/model/resources/yellow_button01.png');";
	private final String but="-fx-background-color:transparent;-fx-background-image:url('/model/resources/yellow_button00.png');";
	
	public SpaceRunnerButton(String text)
	{
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(but);
		initialiseButtonListeners();
		
	}
	
	private void setButtonFont()
	{
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setFont(Font.font("Verdana",23));
		}
		
	}
	
	private void setButtonPressedStyle()
	{
		setStyle(Butpress);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
		
	}
	
	private void setButtonReleasedStyle()
	{
		setStyle(but);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
		
	}

	private void initialiseButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getButton().equals(MouseButton.PRIMARY))
				{
					setButtonPressedStyle();
				}
			}
			
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getButton().equals(MouseButton.PRIMARY))
				{
					setButtonReleasedStyle();
				}
			}
			
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				setEffect(new DropShadow());
			}
			
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				setEffect( (null));
			}
			
		});
		
		
	}
	
}