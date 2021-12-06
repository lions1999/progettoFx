package logic.controller.guicontroller.owner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class RatingUserGUI implements Initializable {

    @FXML private Text RateValue;
    @FXML private Text residentName;
    @FXML private Slider sliderRating;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setUp(String name) {
        residentName.setText(name);
        RateValue.setStyle("-fx-font-size: 15");
        sliderRating.setStyle("-fx-font-size: 20");
    }

    public void submitRate() {
        RateValue.setText(String.valueOf((int)sliderRating.getValue()));
        RateValue.setVisible(true);
    }

    public void submitRating() {

    }
}
