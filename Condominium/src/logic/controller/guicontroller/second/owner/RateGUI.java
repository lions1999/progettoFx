package logic.controller.guicontroller.second.owner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.model.Rate;

public class RateGUI {

    @FXML private Label RateOwnName;
    @FXML private ImageView RateOwnImg;
    @FXML private Label RateValue;
    @FXML private TextArea RateComment;

    public void setupRate(Rate rate) {
        RateOwnImg.setImage(new Image("logic/view/icon/O.png"));
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(1.0);
        RateOwnImg.setEffect(color);
        RateOwnName.setText(rate.getOwnId());
        RateValue.setText(rate.getRateVal());
        RateComment.setText(rate.getRateTxt());
    }
}
