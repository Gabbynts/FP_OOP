package model;

public enum CAR {
	
	RED("view/resources/carchooser/car_red.png"),
	YELLOW("view/resources/carchooser/car_yellow.png"),
	BLACK("view/resources/carchooser/car_black.png"),
	BLUE("view/resources/carchooser/car_blue.png");
	
	private String urlCar;
	
	private CAR(String urlCar) {
		this.urlCar = urlCar;
	}
	
	public String getUrl() {
		return this.urlCar;
	}
}
