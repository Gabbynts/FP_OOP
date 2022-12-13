package view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

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
