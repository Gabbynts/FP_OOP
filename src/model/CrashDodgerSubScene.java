package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class CrashDodgerSubScene extends SubScene {
	
	private final static String FONT_PATH = "src/model/resources/konvector_future.ttf";
	private final static String BACKGROUND_IMAGE = "model/resources/Information.png";
	
	private boolean isHidden;
	

	public CrashDodgerSubScene() {
		super(new AnchorPane(), 700, 700);
		prefWidth(700);
		prefHeight(700);
		
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 700, 700, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		
		root2.setBackground(new Background(image));
		
		isHidden = true;
		
		setLayoutX(1024);
		setLayoutY(180);
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		
		if(isHidden) {
			transition.setToX(-676);
			isHidden = false;
		}
		else {
			transition.setToX(0);
			isHidden = true;
		}
		
		transition.play();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
	
}
