package logic.controller.guicontroller.owner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.RateController;
import logic.engineeringclasses.bean.UserBean;

import java.sql.SQLException;

public class RatingUserGUI {

    private final RateController rateController = new RateController();
    private final UserBean bean = new UserBean();

    @FXML public TextArea rate_comment;
    @FXML public Button Rate;
    @FXML private Text residentName;
    @FXML private Slider sliderRating;


    public void setUp(String name, String id) {
        residentName.setText(name);
        Rate.setDisable(true);
        sliderRating.setStyle("-fx-font-size: 20");
        setBean(Integer.parseInt(id));
    }

    public void submitRating() throws SQLException {
        int rate = (int) sliderRating.getValue();
        String commentRate = rate_comment.getText();
        String id = String.valueOf(bean.getID());
        rateController.rateUser(id,rate,commentRate);
    }

    public void enableButton(KeyEvent keyEvent) {
        if(rate_comment.getText().matches("( *)")){
            Rate.setDisable(true);
        }else{
            Rate.setDisable(false);
        }
    }



    public UserBean setBean(int id){
        bean.setID(id);
        return bean;
    }
}
