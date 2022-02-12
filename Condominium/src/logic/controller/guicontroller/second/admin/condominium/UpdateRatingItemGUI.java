package logic.controller.guicontroller.second.admin.condominium;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import logic.controller.applicationcontroller.RateController;
import logic.controller.guicontroller.AlertGUI;
import logic.engineeringclasses.bean.RateBean;
import java.sql.SQLException;
import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class UpdateRatingItemGUI {

    private final RateController controller = new RateController();
    private final AlertGUI alert = new AlertGUI();

    public Label lbID;
    public Label lbName;
    public Label lbVal;
    public TextArea areaTxt;
    private int gui;

    public void onDeletePress() throws SQLException {
        if(alert.alertConfirm("Condominium/Info","Are you sure you want to delete this review?",null)) {
            controller.deleteRate(lbID.getText());
            if (gui == 2) {
                secondBorder.setRight(null);
            }
            alert.alertInfo("Condominium/Info","Review successfully deleted",null);
        }
    }

    public void setUp(RateBean bean,int gui) {
        lbID.setText(bean.getRateId());
        lbName.setText(bean.getResId());
        lbVal.setText(bean.getRateVal());
        areaTxt.setText(bean.getRateTxt());
        this.gui = gui;
    }
}
