package model;

public enum CAR {
	
	RED("view/resources,carchooser/car.png"),
	YELLOW("view/resources,carchooser/car.png"),
	GREEN("view/resources,carchooser/car.png"),
	BLUE("view/resources,carchooser/car.png");
	
	String urlCar;
	
	private CAR(String urlCar) {
		this.urlCar = urlCar;
	}
}
