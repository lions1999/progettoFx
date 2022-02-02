//package logic.controller.guicontroller.first.owner;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.VBox;
//import javafx.stage.Screen;
//import logic.controller.applicationcontroller.ApartmentController;
//import logic.controller.applicationcontroller.RateController;
//import logic.controller.guicontroller.AlertGUI;
//import logic.controller.guicontroller.first.general.MainGUI;
//import logic.model.Apartment;
//import logic.model.Rate;
//import logic.model.UserSingleton;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class RateResidentGUI extends MainGUI implements Initializable {
//
//    private final ApartmentController aptController = new ApartmentController();
//    private final UserSingleton sg = UserSingleton.getInstance();
//    private final RateController rateCtrl = new RateController();
//
//    @FXML private TableView<Apartment> Table;
//    @FXML private TableColumn<?, ?> aptAddress;
//    @FXML private TableColumn<?, ?> resName;
//    @FXML private TableColumn<?, ?> aptNum;
//    @FXML private VBox scroll;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setUp();
//        try {
//            ObservableList<Apartment> ownerApt = FXCollections.observableArrayList(aptController.checkApartmentsList(sg.getUserID(),sg.getAddress(),"apt_own"));
//            Table.setItems(ownerApt);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setUp(){
//        resName.setCellValueFactory(new PropertyValueFactory<>("resident"));
//        aptAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//        aptNum.setCellValueFactory(new PropertyValueFactory<>("number"));
//        Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//    }
//
//    public void submitUser() throws SQLException, IOException {
//        FXMLLoader loader = view.loader("RatingUser");
//        Parent root = loader.load();
//        RatingUserGUI rating = loader.getController();
//        Apartment apt = Table.getSelectionModel().getSelectedItem();
//        String name = apt.getResident();
//        String id = aptController.checkUserAptFromNumber(apt.getAddress(),apt.getNumber(),"apt_res");
//        ObservableList<Rate> rates = FXCollections.observableArrayList(rateCtrl.getRates(id));
//        rating.setUp(name,id);
//        border.setRight(root);
//        scroll.getChildren().clear();
//        if (rates.size() != 0) {
//            VBox ratesPane = new VBox();
//            for (int i = rates.size() - 1; i >= 0; i--) {
//                FXMLLoader loader1 = view.loader("Rating");
//                Parent root1 = loader1.load();
//                RateGUI pastRate = loader1.getController();
//                pastRate.setupRate(rates.get(i));
//                ratesPane.getChildren().add(root1);
//            }
//            ScrollPane scrollPane = new ScrollPane();
//            scrollPane.setContent(ratesPane);
//            scrollPane.setPrefHeight(Screen.getPrimary().getBounds().getHeight()-300);
//            scroll.getChildren().add(scrollPane);
//        }else{
//            AlertGUI alert = new AlertGUI();
//            alert.alertInfo("Rates/Error","This user has no rates","No rates found on the user "+apt.getResident());
//        }
//    }
//}