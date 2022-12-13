# **FP_OOP**
## **Repository for OOP Final Project using Java**

### Gabriella Natasya Br Ginting - 5025211081
<br>

### **Deskripsi**
Project ini merupakan game dengan bahasa Java yang mengimplementasikan JavaFX sebagai penerapan GUI. <br>
Game dengan nama CrashDodger ini merupakan game yang dirancang dengan konsep game mobil.<br> 
Game ini dimulai dengan pemain yang dapat memilih beberapa pilihan yaitu :
* Play
* Scores
* Help
* Credit
* Exit<br>

Untuk memulai permainan, pemain dapat memilih button **play** dan memilih mobil yang ingin dipakai untuk bermain. Setelah itu pemain dapat bermain dengan menghindari mobil yang berlawanan arah serta mendapatkan **star** .<br>
Pada awal permainan pemain akan memiliki 3 nyawa untuk bermain, saat pemain menabrak mobil lain maka pemain akan kehilangan satu nyawanya. Namun, saat pemain mendapatkan star. Maka, point pemain akan bertambah. Sehingga, pemain harus bertahan dengan 3 nyawa untuk mendapatkan point yang tinggi.

### **Aspek OOP**
Project ini diprogram dengan menerapkan aspek OOP, yaitu sebagai berikut:<br>
1. Casting/ Conversion <br>
src -> view -> ViewManager.java
```
String vol ="0.3";
double vol2 = Double.parseDouble(vol);
```
2. Constructor <br>
src -> model -> RoadSeparator.java
```
public class RoadSeparator extends Pane {
	
	ObservableList<Node> list;
	List<Road> roadList;
	int dist;
	
	Timeline timeline = new Timeline();
	
	public RoadSeparator() {
		list = getChildren();
		setRoadSeparator();
		Rectangle clip = new Rectangle(0, 10, 40, 1110);
		setClip(clip);
		moveRoadSeperator();
	}
	
	public void setRoadSeparator() {
		Road roadSeparator;
		roadList = new ArrayList<>();
		
		for(int i = 0 ; i < 16 ; i++) {
			if(i % 2 == 0) {
				roadSeparator = new Road(0, i * 60, 10, 70);
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
			
			if(list.get(dist).getTranslateY() > 900) {
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

```
3. Overloading <br>
```
extends
```
4. Overriding <br>
src -> view -> ViewManager.java
```
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
```
5. Encapsulation <br>
src -> view -> ViewManager.java
```
public boolean getCheckButtonToStart() {
    return isCheckButtonToStart;
}

public void setCheckButtonToStart(boolean isCheckButtonToStart) {
	this.isCheckButtonToStart = isCheckButtonToStart;
}
```
6. Inheritance <br>
src -> Model -> CrashDodgerSubScene.java
```
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
```
7. Polymorphism <br>
```
file
```
8. ArrayList <br>
src -> model -> RoadSeparator.java
```
Road roadSeparator;
roadList = new ArrayList<>();
```
9. Exception Handling <br>
src -> view -> ViewManager.java
```
try {
	CrashDodgerSounds.playSound(new URI(CLICK_BUTTON));
} 
catch(URISyntaxException e) {
	e.printStackTrace();
}
```
10. GUI <br>

![image](https://github.com/Gabbynts/FP_OOP/blob/main/img/GUI-img.jpg)

11. Interface <br>
src -> model -> RoadSeparator.java
```
public RoadSeparator() {
	list = getChildren();
	setRoadSeparator();
	Rectangle clip = new Rectangle(0, 10, 40, 1110);
	setClip(clip);
	moveRoadSeperator();
}
```
12. Abstract Class <br>
src -> model -> Elements.java
```
public abstract class Elements {
	private Color color;
	protected double x;
	protected double y;
	
	public void Shape() {
		color = Color.WHITE;
	}
	
	public abstract void down();
	
	public void move() {
		x += 2.00;
		y += 2.00;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
```
13. Generics <br>
```
file
```
14. Collection <br>
src -> model -> RoadSeparator.java
```
Road roadSeparator;
roadList = new ArrayList<>();

for(int i = 0 ; i < 16 ; i++) {
	if(i % 2 == 0) {
        roadSeparator = new Road(0, i * 60, 10, 70);
        roadList.add(roadSeparator);
list.add(roadSeparator);
	}
}
```
15. Input Output <br>
src -> view -> ExitGame.java
```
public class ExitGame {
	public static boolean exitGame() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("Quit the Game?");
		if (alert.showAndWait() != Optional.of(ButtonType.CANCEL)) {
			return false;
		} 
		return true;
	}
}
```
link referensi https://www.youtube.com/watch?v=DkIuA5ZEZ_U&list=PL4wcbt63yAbdtY-GOeuRjIePfUsukSJZ9

link demo 
