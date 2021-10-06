package logic.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	private boolean i = false;
	
	public boolean display(String title,String message) {
		Stage window = new Stage();
		
		//Layouyt
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(450);
		window.setMinHeight(150);
		
		//Label 
		Label label = new Label(message);		
		label.setFont(new Font("System",18));
		label.setTextFill(Color.BLACK);
		
		//Button
		Button btnOk = new Button("OK");		
		btnOk.setOnAction(e->{
			i = true;	
			window.close();
		});
		//Button
		Button btnNo = new Button("NO");		
		btnNo.setOnAction(e->{
			i = false;	
			window.close();
		});		
				
		//Structure
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,btnOk,btnNo);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return i;
	}
}
