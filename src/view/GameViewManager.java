package view;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.CAR;

public class GameViewManager {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	private Stage menuStage;
	private ImageView car;
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private int angle;
	private AnimationTimer gameTimer;
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "view/resources/purple.png";
	
	private final static String METEOR_BROWN_IMAGE = "view/resources/meteorBrown.png";
	private final static String METEOR_IMAGE = "view/resources/meteorBrown.png";
	
	private ImageView[] brownMeteors;
	private ImageView[] greyMeteors;
	Random randomPositionGenerator;
	
	public GameViewManager() {
		initializeStage();
		createKeyListeners();
	}

	private void createKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler <KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				}
				else if(event.getCode() == KeyCode.RIGHT){
					isRightKeyPressed = true;
				}
			}
			
		});
		
		gameScene.setOnKeyReleased(new EventHandler <KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				}
				else if(event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}
			}
			
		});
		
	}

	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
	}
	
	public void createNewGame(Stage menuStage, CAR choosenCar) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createCar(choosenCar);
		createGameElements();
		createGameLoop();
		gameStage.show();
	}
	
	private void createGameElements() {
		brownMeteors = new ImageView[3];
		for(int i = 0 ; i < brownMeteors.length ; i++) {
			brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
			setNewElementPosition(brownMeteors[i]);
			gamePane.getChildren().add(brownMeteors[i]);
		}
		
		greyMeteors = new ImageView[3];
		for(int i = 0 ; i < greyMeteors.length ; i++) {
			greyMeteors[i] = new ImageView(METEOR_IMAGE);
			setNewElementPosition(greyMeteors[i]);
			gamePane.getChildren().add(greyMeteors[i]);
		}
	}
	
	private void moveGameElements() {
		for(int i = 0; i < brownMeteors.length ; i++) {
			brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + 7);
			brownMeteors[i].setRotate(brownMeteors[i].getRotate() + 4);
		}
		
		for(int i = 0; i < greyMeteors.length ; i++) {
			greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + 7);
			greyMeteors[i].setRotate(greyMeteors[i].getRotate() + 4);
		}
	}
	
	private void checkIfElementsAreBehindTheCarsAndRelocate() {
		for(int i = 0 ; i < brownMeteors.length ; i++) {
			if(brownMeteors[i].getLayoutY() > 900) {
				setNewElementPosition(brownMeteors[i]);
			}
		}
		
		for(int i = 0 ; i < greyMeteors.length ; i++) {
			if(greyMeteors[i].getLayoutY() > 900) {
				setNewElementPosition(greyMeteors[i]);
			}
		}
	}
	
	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(370));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
	}
	
	private void createCar(CAR choosenCar) {
		car = new ImageView(choosenCar.getUrl());
		car.setLayoutX(GAME_WIDTH/2);
		car.setY(GAME_HEIGHT - 90);
		gamePane.getChildren().add(car);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				moveBackground();
				moveGameElements();
				checkIfElementsAreBehindTheCarsAndRelocate();
				moveCar();
			}
			
		};
		
		gameTimer.start();
	}
	
	private void moveCar() {
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(angle > -30) {
				angle -= 5;
			}
			car.setRotate(angle);
			if(car.getLayoutX() > -20) {
				car.setLayoutX(car.getLayoutX() -3);
			}
		}
		
		if(isRightKeyPressed && !isLeftKeyPressed) {
			if(angle < 30) {
				angle += 5;
			}
			car.setRotate(angle);
			if(car.getLayoutX() < 522) {
				car.setLayoutX(car.getLayoutX() +3);
			}
		}
		
		if(!isLeftKeyPressed && !isRightKeyPressed){
			if(angle < 0) {
				angle += 5;
			} 
			else if(angle > 0) {
				angle -= 5;
			}
			car.setRotate(angle);
		}
		
		if(isRightKeyPressed && isLeftKeyPressed) {
			if(angle < 0) {
				angle += 5;
			} 
			else if(angle > 0) {
				angle -= 5;
			}
			car.setRotate(angle);
		}
	}
	
	private void createBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		
		for(int i = 0 ; i < 12 ; i++) {
			ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
			GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
			gridPane1.getChildren().add(backgroundImage1);
			gridPane2.getChildren().add(backgroundImage2);
		}
		
		gridPane1.setLayoutY(-1024);
		
		gamePane.getChildren().addAll(gridPane1, gridPane2);
	}
	
	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);
		
		if(gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}
		
		if(gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}
}
