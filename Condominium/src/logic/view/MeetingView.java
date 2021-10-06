package logic.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.controller.ViewController;
import logic.model.Request;
import logic.model.UserSingleton;

public class MeetingView implements Initializable{

	private ViewController view = new ViewController();
	UserSingleton sg = UserSingleton.getInstance();
	
    @FXML
    private VBox scrollBox;

    @Override
	public void initialize(URL location, ResourceBundle resources){
	  	try {
	  		List<Request> req = sg.getAdministrator().getListRequest();
	  		for(int i=0;i<req.size();i++) {	  			
	  			sg.setReq(req.get(i));
	  			sg.getReq().setPos(i);	  			
	  			Pane pane = view.getPage("Item");
				scrollBox.getChildren().add(pane);	  			
	  		}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	  }
    
}
