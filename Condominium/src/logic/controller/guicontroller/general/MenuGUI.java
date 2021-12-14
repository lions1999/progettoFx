package logic.controller.guicontroller.general;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.controller.applicationcontroller.PostController;
import logic.controller.guicontroller.general.home.PostGUI;
import logic.engineeringclasses.bean.PostBean;
import logic.model.Post;
import logic.model.UserSingleton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuGUI extends MainGUI implements Initializable {

    public static final UserSingleton sg = UserSingleton.getInstance();
    private final PostController controller = new PostController();
    private final VBox scrollBox = new VBox();

    @FXML private Button btnSign;
    @FXML private Button btnApartment;
    @FXML private Label lbName;
    @FXML private Button btnMeeting;
    @FXML private Button btnHome;
    @FXML private Label tfCondominiumCode;
    @FXML private Label lbTitle; //TODO
    @FXML private ImageView imgUser; //TODO

    @FXML public void btnHomeClick() {
        scrollBox.getChildren().clear();
        Pane addPost = view.getPage("AddPost");
        scrollBox.getChildren().add(addPost);
        ObservableList<Post> posts = controller.loadPost();
        loadPosts(posts);
        border.setRight(null);
        border.setCenter(new ScrollPane(scrollBox));
    }

    @FXML public void btnMeetingClick() {
        border.setRight(null);
        try{
        switch (sg.getRole()) {
            case ADMINISTRATOR:
                FXMLLoader loader = view.loader("TabOrganize");
                Parent root = loader.load();
               // TabOrganizeGUI tab = new TabOrganizeGUI();
              //  tab.regTabClick();
                border.setCenter(root);
                break;
            case OWNER:

            case RESIDENT:
                scrollBox.getChildren().clear();
                Pane Contact = view.getPage("Contact");
                scrollBox.getChildren().add(Contact);
                border.setCenter(new ScrollPane(scrollBox));
                break;
        }}catch(Exception e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    @FXML public void btnInfoClick() {
        border.setRight(null);
        switch (sg.getRole()){
            case ADMINISTRATOR:
                Pane condInfo = view.getPage("CondInfo");
                border.setCenter(condInfo);
                break;
            case OWNER:
                Pane Rate = view.getPage("RateResident");
                border.setCenter(Rate);
                break;
            case RESIDENT:
                Pane aptInfo = view.getPage("AptInfo");
                border.setCenter(aptInfo);
                break;
        }
    }

    @FXML private void btnSignOutClick() {
        sg.clearState();
        Pane log = view.getPage("Login");
        border.setRight(null);
        border.setLeft(null);
        border.setCenter(log);
        fullScreen(false);
    }

    private void loadPosts(ObservableList<Post> posts) {
        try {
            for (int i = posts.size() - 1; i >= 0; i--) {
                PostBean bean = setUpPost(posts.get(i));
                FXMLLoader loader = view.loader("Post");
                Parent root = loader.load();
                PostGUI postgui = loader.getController();
                postgui.setUpPost(bean);
                scrollBox.getChildren().add(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PostBean setUpPost(Post curr) {
        PostBean post = new PostBean();
        post.setId(curr.getId());
        post.setImage(curr.getImage());
        post.setText(curr.getText());
        post.setUser(curr.getUser());
        return post;
    }

    private void btnColor(Button btn) {
        btn.setOnMouseEntered(event -> btn.setStyle("-fx-background-color : #0A0E3F"));
        btn.setOnMouseExited(event -> btn.setStyle("-fx-background-color : #0C39FF"));
        //TODO ADD TO CSS
    }

    public void setUp() throws IOException {
        btnHomeClick();
        btnColor(btnHome);
        btnColor(btnMeeting);
        btnColor(btnSign);
        btnColor(btnApartment);
        tfCondominiumCode.setText(sg.getAddress());
        switch (sg.getRole()) {
            case ADMINISTRATOR:
                lbName.setText(sg.getAdministrator().getName());
                btnApartment.setText("Condominium Info");
                btnMeeting.setText("Requests");
                break;
            case OWNER:
                lbName.setText(sg.getOwner().getName());
                btnMeeting.setText("Request Meeting");
                btnApartment.setText("Rate Resident");
                break;
            case RESIDENT:
                lbName.setText(sg.getResident().getName());
                btnMeeting.setText("Contact");
                btnApartment.setText("Apartment Info");
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
