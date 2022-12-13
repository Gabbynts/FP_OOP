package view;

import java.io.File;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.CAR;
import model.RoadSeparator;
import model.SmallInfoLabel;

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
	
	private final static String BACKGROUND_IMAGE = "view/resources/street.png";
	
	private final static String METEOR_BROWN_IMAGE = "view/resources/carchooser/red_1.png";
	private final static String METEOR_IMAGE = "view/resources/carchooser/yellow_1.png";
	
	private ImageView[] brownMeteors;
	private ImageView[] greyMeteors;
	Random randomPositionGenerator;
	
	private ImageView star;
	private SmallInfoLabel pointsLabel;
	private ImageView[] playerLifes;
	private int playerLife;
	private int points;
	private final static String GOLD_STAR_IMAGE = "view/resources/carchooser/star_gold.png";
	
	private final static int STAR_RADIUS = 12;
	private final static int CAR_RADIUS = 27;
	private final static int METEOR_RADIUS = 40;
	
	public GameViewManager() {
		randomPositionGenerator = new Random();
		
		initializeStage();
		createKeyListeners();
		//backgroundSound();
	}
	
//	private void backgroundSound() {
//		String path = "src/model/resources/sounds/atv-engine-motor.mp3";
//		Media media = new Media(new File(path).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		
//		mediaPlayer.setVolume(0.9);
//		mediaPlayer.setAutoPlay(true);
//	}

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
		gameStage.setResizable(false);
		gameStage.setOnCloseRequest(x -> {
			x.consume();
			if(ExitGame.exitGame()) {
				gameStage.close();
			menuStage.show();	
			}
		});
	}
	
	public void createNewGame(Stage menuStage, CAR choosenCar) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createRoadSeparator();
		createCar(choosenCar);
		createGameElements(choosenCar);
		createGameLoop();
		gameStage.show();
	}
	
	private void createRoadSeparator() {
		RoadSeparator roadSeparator1 = new RoadSeparator();
		RoadSeparator roadSeparator2 = new RoadSeparator();
		RoadSeparator roadSeparator3 = new RoadSeparator();
		
		roadSeparator1.setTranslateY(5);
		roadSeparator1.setTranslateX(200);
		roadSeparator2.setTranslateY(0);
		roadSeparator2.setTranslateX(400);
		gamePane.getChildren().addAll(roadSeparator1, roadSeparator2);
	}
	
	
	private void createGameElements(CAR choosenCar) {
		playerLife = 2;
		star = new ImageView(GOLD_STAR_IMAGE);
		setNewElementPosition(star);
		
		gamePane.getChildren().add(star);
		pointsLabel = new SmallInfoLabel("POINTS : 00");
		pointsLabel.setLayoutX(460);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		
		playerLifes = new ImageView[3]; 
		
		for(int i = 0 ; i < playerLifes.length ; i++) {
			playerLifes[i] = new ImageView(choosenCar.getUrlLife());
			playerLifes[i].setLayoutX(455 + (i * 50));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);
		}
		
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
		star.setLayoutY(star.getLayoutY() + 5);
		
		for(int i = 0; i < brownMeteors.length ; i++) {
			brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + 10);
		}
		
		for(int i = 0; i < greyMeteors.length ; i++) {
			greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + 10);
		}
	}
	
	private void checkIfElementsAreBehindTheCarsAndRelocate() {
		if(star.getLayoutY() > GAME_HEIGHT) {
			setNewElementPosition(star);
		}
		
		for(int i = 0 ; i < brownMeteors.length ; i++) {
			if(brownMeteors[i].getLayoutY() > GAME_HEIGHT) {
				setNewElementPosition(brownMeteors[i]);
			}
		}
		
		for(int i = 0 ; i < greyMeteors.length ; i++) {
			if(greyMeteors[i].getLayoutY() > GAME_HEIGHT) {
				setNewElementPosition(greyMeteors[i]);
			}
		}
	}
	
	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(400));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
	}
	
	private void createCar(CAR choosenCar) {
		car = new ImageView(choosenCar.getUrl());
		car.setLayoutX(GAME_WIDTH/2);
		car.setY(GAME_HEIGHT - 150);
		gamePane.getChildren().add(car);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				moveGameElements();
				checkIfElementsAreBehindTheCarsAndRelocate();
				checkIfElementsCollide();
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
		Image backgroundImage = new Image("view/resources/street.png", 600, 800, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT , BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		gamePane.setBackground(new Background(background));
	}
	
	private void checkIfElementsCollide() {
		if(CAR_RADIUS + STAR_RADIUS > calculateDistance(car.getLayoutX() + 49, star.getLayoutX() + 15, car.getLayoutY() + 37, star.getLayoutY() + 15)) {
			setNewElementPosition(star);
			
			points++;
			String textToSet = "POINTS: ";
			if(points < 10) {
				textToSet += "0";
			}
			pointsLabel.setText(textToSet + points);
		}
		
		for(int i = 0 ; i < brownMeteors.length ; i++) {
			if(METEOR_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, car.getLayoutY() + 37, brownMeteors[i].getLayoutX() + 20, brownMeteors[i].getLayoutY() + 20)) {
				removeLife();
				setNewElementPosition(brownMeteors[i]);
			}
		}
		
		for(int i = 0 ; i < greyMeteors.length ; i++) {
			if(METEOR_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, greyMeteors[i].getLayoutX() + 20, car.getLayoutY() + 37, brownMeteors[i].getLayoutY() + 20)) {
				removeLife();
				setNewElementPosition(greyMeteors[i]);
			}
		}
	}
	
	private void removeLife() {
		gamePane.getChildren().remove(playerLifes[playerLife]);
		playerLife--;
		if(playerLife < 0) {
			gameStage.close();
			gameTimer.stop();
			menuStage.show();
		}
	}
	
	private double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
