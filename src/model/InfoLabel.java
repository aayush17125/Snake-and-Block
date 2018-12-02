package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
/**
 * This class is used to make custom label used on the main menu scene
 * @author Akhil an Aayush
 *
 */
public class InfoLabel extends Label{
	/**
	 * path to the font
	 */
	String path="src/model/resources/kenvector_future.ttf";
	/**
	 * path to the background image of label
	 */
	String imagepath="src/model/resources/yellow_button13.png";
	/**
	 * It initialises the Label superclass and set various params like height width 
	 * @param text The required text on that label
	 */
	public InfoLabel(String text) {
		// TODO Auto-generated constructor stub
	setPrefWidth(600);
	setPrefHeight(400);
	setPadding(new Insets(40,40,40,40));
	setText(text);
	setWrapText(true);
	setLabelFont();
	setAlignment(Pos.CENTER);
	}
	/**
	 * set the font on the label
	 */
	private void setLabelFont() {
		// TODO Auto-generated method stub
		try {
			setFont(Font.loadFont(new FileInputStream(new File(path)), 23));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setFont(Font.font("Verdana",23));
		}
	}

}
