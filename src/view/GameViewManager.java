package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
	
	public GameViewManager() {
		initializeStage();
		createKeyListeners();
	}

	private void createKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler <KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				}
				else if(event.getCode() == KeyCode.RIGHT){
					isRightKeyPressed = false;
				}
			}
			
		});
		
		gameScene.setOnKeyReleased(new EventHandler <KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					
				}
				else if(event.getCode() == KeyCode.RIGHT) {
					
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
		createCar(choosenCar);
		createGameLoop();
		gameStage.show();
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
			if(angle > 30) {
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
}
