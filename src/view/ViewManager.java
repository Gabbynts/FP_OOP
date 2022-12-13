package view;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CAR;
import model.CarPicker;
import model.CrashDodgerButton;
import model.CrashDodgerSubScene;
import model.InfoLabel;
import model.CrashDodgerSounds;

public class ViewManager {
	
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 150;
	
	private final static String CLICK_BUTTON = "file:src/model/resources/sounds/water-sci-fi.mp3";
	
	private CrashDodgerSubScene credistsSubScene;
	private CrashDodgerSubScene helpSubScene;
	private CrashDodgerSubScene scoreSubScene;
	private CrashDodgerSubScene carChooserScene;
	
	private CrashDodgerSubScene sceneToHide;
	
	List<CrashDodgerButton> menuButtons;
	
	List<CarPicker> carsList;
	private CAR choosenCar;
	
	private boolean isCheckButtonToStart;
	
	
	public ViewManager() {
		//Constructor
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();
		backgroundSound();
	}
	
	private void backgroundSound() {
		String path = "src/model/resources/sounds/neon-gaming.mp3";
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		//CASTING
		String vol ="0.3";
		double vol2 = Double.parseDouble(vol);
		
		mediaPlayer.setVolume(vol2);
		mediaPlayer.setAutoPlay(true);
	}
	
	private void showSubScene(CrashDodgerSubScene subScene) {
		if(sceneToHide != null) {
			sceneToHide.moveSubScene();
		}
		
		subScene.moveSubScene();
		sceneToHide = subScene;
	}
	
	private void createSubScenes() {
		
		credistsSubScene = new CrashDodgerSubScene();
		mainPane.getChildren().add(credistsSubScene);
		
		helpSubScene = new CrashDodgerSubScene();
		mainPane.getChildren().add(helpSubScene);
		
		scoreSubScene = new CrashDodgerSubScene();
		mainPane.getChildren().add(scoreSubScene);
		
		createCarChooserSubScene();
		createHelpSubScene();
	}
	
	private void createHelpSubScene() {
		helpSubScene = new CrashDodgerSubScene();
		mainPane.getChildren().add(helpSubScene);
		
		InfoLabel helpLabel = new InfoLabel("Information");
		helpLabel.setLayoutX(130);
		helpLabel.setLayoutY(210);
		
		helpSubScene.getPane().getChildren().add(helpLabel);
	}
	
	private void createCarChooserSubScene() {
		carChooserScene = new CrashDodgerSubScene();
		mainPane.getChildren().add(carChooserScene);
		
		InfoLabel chooseCarLabel = new InfoLabel("LETS DRIVE");
		chooseCarLabel.setLayoutX(130);
		chooseCarLabel.setLayoutY(210);
		
		carChooserScene.getPane().getChildren().add(chooseCarLabel);
		carChooserScene.getPane().getChildren().add(createCarsToChoose());
		carChooserScene.getPane().getChildren().add(createButtonToStart());
	}
	
	private HBox createCarsToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		carsList = new ArrayList<>();
		for(CAR car : CAR.values()) {
			CarPicker carToPick = new CarPicker(car);
			carsList.add(carToPick);
			box.getChildren().add(carToPick);
			
			carToPick.setOnMouseEntered(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent event) {
					carToPick.setEffect(new DropShadow(20, Color.YELLOW));
				}
				
			});
			
			carToPick.setOnMouseExited(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent event) {
					carToPick.setEffect(null);
				}
				
			});
			carToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					try {
						CrashDodgerSounds.playSound(new URI(CLICK_BUTTON));
					} 
					catch(URISyntaxException e) {
						e.printStackTrace();
					}
					
					for(CarPicker car : carsList) {
						car.setIsCircleChoosen(false);
					}
					carToPick.setIsCircleChoosen(true);
					choosenCar = carToPick.getCar();
				}
			});
		}
		
		box.setLayoutX(300 - (75*2));
		box.setLayoutY(290);
		return box;
	}
	
	private CrashDodgerButton createButtonToStart() {
		CrashDodgerButton startButton = new CrashDodgerButton("START");
		startButton.setLayoutX(340);
		startButton.setLayoutY(488);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				//EXCEPTION HANDLING
				try {
					CrashDodgerSounds.playSound(new URI(CLICK_BUTTON));
				} 
				catch(URISyntaxException e) {
					e.printStackTrace();
				}
				
				if(choosenCar != null) {
					//isCheckButtonToStart = true;
					GameViewManager gameViewManager = new GameViewManager();
					gameViewManager.createNewGame(mainStage, choosenCar);
				}
				
			}
		});
		return startButton;
	}
	
//	public boolean isCheckButtonToStart() {
//		return isCheckButtonToStart;
//	}
//	
//	public void setIsCheckButtonToStart(boolean isCheckButtonToStart) {
//		this.isCheckButtonToStart = isCheckButtonToStart;
//		isCheckButtonToStart = false;
//	}

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
		createCredistsButton();
		createExitButton();
	}
	
	private void createStartButton() {
		CrashDodgerButton startButton = new CrashDodgerButton("PLAY");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					CrashDodgerSounds.playSound(new URI(CLICK_BUTTON));
				} 
				catch(URISyntaxException e) {
					e.printStackTrace();
				}
				showSubScene(carChooserScene);
				//carChooserScene.moveSubScene();
			}
			
		});
	}
	
	private void createScoresButton() {
		CrashDodgerButton scoresButton = new CrashDodgerButton("SCORES");
		addMenuButton(scoresButton);
		
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					CrashDodgerSounds.playSound(new URI(CLICK_BUTTON));
				} 
				catch(URISyntaxException e) {
					e.printStackTrace();
				}
				showSubScene(scoreSubScene);
			}
			
		});
	}
	
	private void createHelpButton() {
		CrashDodgerButton helpButton = new CrashDodgerButton("HELP");
		addMenuButton(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					CrashDodgerSounds.playSound(new URI(CLICK_BUTTON));
				} 
				catch(URISyntaxException e) {
					e.printStackTrace();
				}
				showSubScene(helpSubScene);
			}
		});
	}
	
	private void createCredistsButton() {
		CrashDodgerButton creditsButton = new CrashDodgerButton("CREDITS");
		addMenuButton(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					CrashDodgerSounds.playSound(new URI(CLICK_BUTTON));
				} 
				catch(URISyntaxException e) {
					e.printStackTrace();
				}
				showSubScene(credistsSubScene);
			}
		});
	}
	
	private void createExitButton() {
		CrashDodgerButton exitButton = new CrashDodgerButton("EXIT");
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					CrashDodgerSounds.playSound(new URI(CLICK_BUTTON));
				} 
				catch(URISyntaxException e) {
					e.printStackTrace();
				}
				mainStage.close();
			}
			
		});
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("view/resources/bg_1.png", 1100, 780, false, true);
		//Image backgroundImage = new Image(getClass().getResourceAsStream("resources/purple.jpg"));
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT , BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
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
