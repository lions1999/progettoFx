package logic.controller.guicontroller.first.owner;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import logic.model.Rate;

public class RateGUI {

    @FXML private Text Rate;
    @FXML private TextArea RateComment;
    @FXML private Text RateName;

    public void setupRate(Rate rate) {
        RateName.setText(rate.getOwnId());
        Rate.setText(rate.getRateVal());
        RateComment.setText(rate.getRateTxt());
    }
}