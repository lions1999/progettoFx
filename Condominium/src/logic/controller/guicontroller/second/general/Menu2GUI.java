package logic.controller.guicontroller.second.general;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import logic.controller.applicationcontroller.PostController;
import logic.controller.guicontroller.first.general.Menu1GUI;
import logic.controller.guicontroller.second.general.home.PostGUI;
import logic.engineeringclasses.bean.PostBean;
import logic.model.Post;
import logic.model.UserSingleton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu2GUI extends Main2GUI implements Initializable {

    public static final UserSingleton sg = UserSingleton.getInstance();
    private final HBox scrollBox = new HBox();
    private final PostController controller = new PostController();

    public Label lbName;
    public ImageView imgUser;
    public Label lbTitle;
    public Label tfCondominiumCode;
    public Button btnHome;
    public Button btnMeeting;
    public Button btnApartment;
    public Button btnSign;
    public AnchorPane topMenu;

    public void btnHomeClick() {
        secondBorder.setCenter(null);
        scrollBox.getChildren().clear();
        ObservableList<Post> posts = controller.loadPost();
        loadPosts(posts);
        secondBorder.setCenter(new ScrollPane(scrollBox));
    }

    private void loadPosts(ObservableList<Post> posts) {
        try {
            for (int i = posts.size() - 1; i >= 0; i--) {
                Menu1GUI menu = new Menu1GUI();
                PostBean bean = menu.setUpPost(posts.get(i));
                FXMLLoader loader = view.loader("Post",2);
                Parent root = loader.load();
                //PostGUI postgui = loader.getController();
                PostGUI postgui = loader.getController();
                postgui.setUpPost(bean);
                scrollBox.getChildren().add(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnMeetingClick() {
    }

    public void btnInfoClick() {
    }

    public void btnSignOutClick() {
    }

    public void setUp() throws IOException {
        btnHomeClick();
        tfCondominiumCode.setText(sg.getAddress());
        switch (sg.getRole()) {
            case ADMINISTRATOR:
                lbName.setText(sg.getAdministrator().getUsrName());
                break;
            case OWNER:
                lbName.setText(sg.getOwner().getUsrName());
                break;
            case RESIDENT:
                lbName.setText(sg.getResident().getUsrName());
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
