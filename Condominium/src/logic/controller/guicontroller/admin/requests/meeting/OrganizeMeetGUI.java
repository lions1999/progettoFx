package logic.controller.guicontroller.admin.requests.meeting;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.engineeringclasses.bean.MeetRequestBean;

public class OrganizeMeetGUI {
    public Text txt;
    public TextField obj;
    public TextArea area;

    public void setUp(MeetRequestBean bean){
        txt.setText("You are currently organize meeting, remember the mail will sent to all OWNERS in your condominium");
        obj.setText(bean.getObject());
    }
}
