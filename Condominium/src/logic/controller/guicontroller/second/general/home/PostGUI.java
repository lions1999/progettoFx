package logic.controller.guicontroller.second.general.home;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import logic.controller.applicationcontroller.PostController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.second.general.Menu2GUI;
import logic.engineeringclasses.bean.PostBean;
import logic.model.UserSingleton;

public class PostGUI {

    private static final Menu2GUI menu = new Menu2GUI();
    private final AlertGUI alert = new AlertGUI();
    private final PostController controller = new PostController();

    UserSingleton sg = UserSingleton.getInstance();

    @FXML private ImageView usrImg; //TODO
    @FXML private Label usrName;
    @FXML private TextField posTxt;
    @FXML private Button btnDelete;
    @FXML private ImageView imageView;

    public void onDeletePress() {
        switch (sg.getRole()) {
            case ADMINISTRATOR:
                try {
                    controller.deletePost(btnDelete.getAccessibleText());
                    menu.btnHomeClick();
                }catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case OWNER:
                case RESIDENT:
                    alert.alertInfo(null ,  "Sorry you are not allowed to do that", null);
                break;
        }
    }

    public void onImageClick() { //TODO
        DialogPane pane = new DialogPane();
        pane.setContent(imageView);
        imageView.setVisible(true);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);
        dialog.showAndWait();
    }

    public void setUpPost(PostBean bean) {
        posTxt.setText(bean.getText());
        posTxt.setEditable(false);
        btnDelete.setAccessibleText(bean.getId());
        usrName.setText(bean.getUser());
        imageView.setImage(controller.setPostImage(bean.getImage()));
    }
}
