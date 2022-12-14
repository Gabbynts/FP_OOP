package model;

public enum CAR {
	
	RED("view/resources/carchooser/red.png", "view/resources/carchooser/heart.png"),
	YELLOW("view/resources/carchooser/yellow.png", "view/resources/carchooser/heart.png"),
	BLACK("view/resources/carchooser/black.png", "view/resources/carchooser/heart.png"),
	BLUE("view/resources/carchooser/blue.png", "view/resources/carchooser/heart.png");
	
	private String urlCar;
	private String urlLife;
	
	private CAR(String urlCar, String urlLife) {
		this.urlCar = urlCar;
		this.urlLife = urlLife;
	}
	
	public String getUrl() {
		return this.urlCar;
	}
	
	public String getUrlLife() {
		return this.urlLife;
	}
}
