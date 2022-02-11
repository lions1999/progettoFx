package logic.controller.guicontroller.second.general;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import logic.controller.applicationcontroller.PostController;
import logic.controller.applicationcontroller.UserController;
import logic.controller.guicontroller.first.general.Menu1GUI;
import logic.controller.guicontroller.second.admin.condominium.InfoItemGUI;
import logic.controller.guicontroller.second.admin.condominium.InfoTableGUI;
import logic.controller.guicontroller.second.admin.requests.RequestBottomNavMenuGUI;
import logic.controller.guicontroller.second.general.home.AddPostGUI;
import logic.controller.guicontroller.second.general.home.PostGUI;
import logic.engineeringclasses.bean.PostBean;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Post;
import logic.model.User;
import logic.model.UserSingleton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Menu2GUI extends Main2GUI implements Initializable {

    public static final UserSingleton sg = UserSingleton.getInstance();
    private final HBox scrollBox = new HBox();
    private final VBox vbox = new VBox();
    private final PostController postCtrl = new PostController();
    private final UserController usrCtrl = new UserController();

    @FXML private ImageView imgUser;
    @FXML private Label lbName;
    @FXML private Label tfCondominiumCode;
    @FXML private ChoiceBox<String> choice;
    private final ObservableList<String> admin = FXCollections.observableArrayList("Home","Request","Condominium","Sign Out");

    public void setUp(){
        choice.setOnAction(this::getChoice);
        choice.setValue("Home");
        imgUser.setImage(new Image(view.addImage(sg.getRole())));
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(1);
        imgUser.setEffect(color);
        tfCondominiumCode.setText(sg.getAddress());
        switch (sg.getRole()) {
            case ADMINISTRATOR:
                lbName.setText(sg.getAdministrator().getUsrName());
                choice.setItems(admin);
                break;
            case OWNER:
                lbName.setText(sg.getOwner().getUsrName());
                break;
            case RESIDENT:
                lbName.setText(sg.getResident().getUsrName());
                break;
        }
    }

    private void getChoice(javafx.event.ActionEvent actionEvent) {
        switch (choice.getValue()){
            case "Home":
                btnHomeClick();
                break;
            case "Request":
                btnMeetingClick();
                break;
            case "Condominium":
                btnInfoClick();
                break;
            case "Sign Out":
                btnSignOutClick();
                break;
        }
    }

    public void btnHomeClick() {
        secondBorder.setBottom(null);
        vbox.getChildren().clear();
        scrollBox.getChildren().clear();
        addPost();
        ObservableList<Post> posts = postCtrl.loadPost();
        loadPosts(posts);
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setOnScroll(event -> {
            if(event.getDeltaX() == 0 && event.getDeltaY() != 0) {
                scroll.setHvalue(scroll.getHvalue() - event.getDeltaY() / scrollBox.getWidth());
            }
        });
        scrollBox.setSpacing(30);
        scroll.setContent(scrollBox);
        vbox.getChildren().add(scroll);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        vbox.getStylesheets().add("/logic/view/second/style.css");
        secondBorder.setCenter(vbox);
    }

    private void addPost(){
        Button btnAdd = new Button("Add Post");
        btnAdd.getStyleClass().add("btnGeneral");
        btnAdd.setOnAction(e->{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/logic/view/first/Dialog.fxml"));
                DialogPane pane = loader.load();
                FXMLLoader addPost = new FXMLLoader(getClass().getResource("/logic/view/second/AddPostView.fxml"));
                Pane post = addPost.load();
                pane.setContent(post);
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(pane);
                Optional<ButtonType> btn = dialog.showAndWait();
                if (btn.isPresent() && btn.get() == ButtonType.OK) {
                    AddPostGUI gui = addPost.getController();
                    if(gui.getMsg() == null || gui.getFile() == null){
                        alert.alertWarn("Condominium/Home/Warning", "Ops... Missing Text/File", "Please insert Text");
                    }else{
                        postCtrl.addPost(sg.getUserID(), gui.getMsg(),gui.getFile(),sg.getAddress());
                        btnHomeClick();
                    }
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        btnAdd.getStyleClass().add("btnGeneral");
        vbox.getChildren().add(btnAdd);
    }

    private void loadPosts(ObservableList<Post> posts) {
        try {
            for (int i = posts.size() - 1; i >= 0; i--) {
                Menu1GUI menu = new Menu1GUI();
                PostBean bean = menu.setUpPost(posts.get(i));
                FXMLLoader loader = view.loader("Post",2);
                Parent root = loader.load();
                PostGUI postgui = loader.getController();
                postgui.setUpPost(bean);
                scrollBox.getChildren().add(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnMeetingClick()  {
        secondBorder.setRight(null);
        try{
            FXMLLoader loader = view.loader("RequestBottomNavMenu", 2);
            Pane root = loader.load();
            RequestBottomNavMenuGUI bottomMenu = loader.getController();
            bottomMenu.setAddress(sg.getAddress());
            bottomMenu.btnRegClick();
            root.setMaxSize(Double.MAX_VALUE, Region.USE_COMPUTED_SIZE);
            secondBorder.setBottom(root);
        }catch(IOException ignore){
        }
    }

    public void btnInfoClick() {
        secondBorder.setCenter(null);
        secondBorder.setRight(null);
        try {
            FXMLLoader loader = view.loader("InfoTable", 2);
            Pane table = loader.load();
            InfoTableGUI tableCtrl = loader.getController();
            tableCtrl.clearChildren();
            ObservableList<User> list = usrCtrl.loadUserList(sg.getAddress());
            for(int i = 0; i<=list.size()-1; i++){
                UserBean bean = setUpBean(list.get(i));
                FXMLLoader item = view.loader("ItemInfo",2);
                Pane root = item.load();
                InfoItemGUI itemCtrl = item.getController();
                itemCtrl.setUp(bean);
                tableCtrl.addChildren(root);
            }
            table.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            secondBorder.setCenter(table);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private UserBean setUpBean(User user){
        UserBean bean = new UserBean();
        bean.setUsrID(user.getUsrID());
        bean.setUsrName(user.getUsrName());
        bean.setUsrEmail(user.getUsrEmail());
        bean.setUsrPwd(user.getUsrPwd());
        bean.setUsrRole(user.getUsrRole());
        bean.setUsrAddr(user.getUsrAddr());
        return bean;
    }

    public void btnSignOutClick() {
        sg.clearState();
        Pane log = view.getPage("Login",2);
        secondBorder.setRight(null);
        secondBorder.setTop(null);
        secondBorder.setCenter(log);
        secondBorder.setBottom(null);
        fullScreen(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
