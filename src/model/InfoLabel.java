package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label{
	String path="src/model/resources/kenvector_future.ttf";
	String imagepath="src/model/resources/yellow_button13.png";
	
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
