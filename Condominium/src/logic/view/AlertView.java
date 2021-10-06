package logic.view;

import javafx.scene.control.Alert;

public class AlertView {
		
	public void alertError(String title,String headerText,String contentText) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
	    alert.setContentText(contentText);
	    alert.showAndWait();
	}
	
	public void alertInfo(String title,String headerText,String contentText) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public void alertConfirm(String title,String headerText,String contentText) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
}
