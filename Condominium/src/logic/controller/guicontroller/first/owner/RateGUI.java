package logic.controller.guicontroller.first.owner;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import logic.engineeringclasses.bean.RateBean;

public class RateGUI {

    @FXML private Text Rate;
    @FXML private TextArea RateComment;
    @FXML private Text RateName;

    public void setupRate(RateBean rate) {
        RateName.setText(rate.getOwnId());
        Rate.setText(rate.getRateVal());
        RateComment.setText(rate.getRateTxt());
    }
}