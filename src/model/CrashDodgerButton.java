package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CrashDodgerButton extends Button {
	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final static String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent;-fx-background-image: url('/model/resources/yelolow_button_pressed.png');";
	private final static String BUTTON_FREE_STYLE = "-fx-background-color: transparent;-fx-background-image: url('/model/resources/purple_button.png');";

	public CrashDodgerButton(String text) {
		setText(text);
		setButtonFont();
		setPrefHeight(49);
		setPrefWidth(190);
		setBackground(getBackground());
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
	}
	
	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 23));
		}
	}
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
	}
	
	private void initializeButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow(50, Color.YELLOW));
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
}