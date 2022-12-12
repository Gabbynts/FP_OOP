package model;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RoadSeparator extends Pane {
	
	ObservableList<Node> list;
	List<Road> roadList;
	int dist;
	
	Timeline timeline = new Timeline();
	
	public RoadSeparator() {
		list = getChildren();
		setRoadSeparator();
		Rectangle clip = new Rectangle(0, -100, 15, 500);
		setClip(clip);
		moveRoadSeperator();
	}
	
	public void setRoadSeparator() {
		Road roadSeparator;
		roadList = new ArrayList<>();
		
		for(int i = 0 ; i < 9 ; i++) {
			if(i % 2 == 0) {
				roadSeparator = new Road(0, i * 40, 6, 40);
				roadList.add(roadSeparator);
				list.add(roadSeparator);
			}
		}
		dist = list.size() - 1;
	}
	
	private void moveRoadSeperator() {
		KeyFrame keyFrame = new KeyFrame(Duration.millis(2),(event)->{
			for(int i = 0 ; i < list.size() ; i++) {
				list.get(i).setTranslateY(list.get(i).getTranslateY() + 1);
			}
			
			if(list.get(dist).getTranslateY() > 400) {
				list.get(dist).setTranslateY(-50);
				dist -= 1;
				if(dist < 0) {
					dist =  list.size() - 1;
				}
			}
		});
		
		timeline.getKeyFrames().add(keyFrame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
}
