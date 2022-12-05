package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import model.CrashDodgerButton;
import model.CrashDodgerSubScene;

public class ViewManager {
	
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 150;
	
	List<CrashDodgerButton> menuButtons;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButton(CrashDodgerButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createButtons() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
		createLogo();
		
		CrashDodgerSubScene subScene = new CrashDodgerSubScene();
		
		subScene.setLayoutX(200);
		subScene.setLayoutY(100);
		mainPane.getChildren().add(subScene);
	}
	
	private void createStartButton() {
		CrashDodgerButton startButton = new CrashDodgerButton("PLAY");
		addMenuButton(startButton);
	}
	
	private void createScoresButton() {
		CrashDodgerButton scoresButton = new CrashDodgerButton("SCORES");
		addMenuButton(scoresButton);
	}
	
	private void createHelpButton() {
		CrashDodgerButton helpButton = new CrashDodgerButton("HELP");
		addMenuButton(helpButton);
	}
	
	private void createCreditsButton() {
		CrashDodgerButton creditsButton = new CrashDodgerButton("CREDITS");
		addMenuButton(creditsButton);
	}
	
	private void createExitButton() {
		CrashDodgerButton exitButton = new CrashDodgerButton("EXIT");
		addMenuButton(exitButton);
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("view/resources/purple.png", 256, 256, false, true);
		//Image backgroundImage = new Image(getClass().getResourceAsStream("resources/purple.jpg"));
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/crash_dodger.png");
		logo.setLayoutX(400);
		logo.setLayoutY(-110);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
			
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
			
		});
		
		mainPane.getChildren().add(logo);
	}
}
