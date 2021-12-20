package logic.controller.guicontroller.owner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.guicontroller.general.MainGUI;
import logic.model.Apartment;
import logic.model.UserSingleton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RateResidentGUI extends MainGUI implements Initializable {

    private final ApartmentController aptController = new ApartmentController();
    private final UserSingleton sg = UserSingleton.getInstance();

    @FXML private TableView<Apartment> Table;
    @FXML private TableColumn<?, ?> aptAddress;
    @FXML private TableColumn<?, ?> resName;
    @FXML private TableColumn<?, ?> aptNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUp();
        try {
            final ObservableList<Apartment> ownerApt = FXCollections.observableArrayList(aptController.checkApartmentsList(sg.getUserID(),sg.getAddress(),"apt_own"));
            //final ObservableList<Apartment> list = FXCollections.observableArrayList();
            //list.add(apartment);
            Table.setItems(ownerApt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUp(){
        resName.setCellValueFactory(new PropertyValueFactory<>("resident"));
        aptAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        aptNum.setCellValueFactory(new PropertyValueFactory<>("number"));
        Table.setColumnResizePolicy(Table.CONSTRAINED_RESIZE_POLICY);
    }

    public void submitUser() throws IOException, SQLException {
        FXMLLoader loader = view.loader("RatingUser");
        Parent root = loader.load();
        RatingUserGUI rating = loader.getController();
        Apartment apt = Table.getSelectionModel().getSelectedItem();
        String name = apt.getResident();
        String id = aptController.checkUserAptFromNumber(apt.getAddress(),apt.getNumber(),"apt_res");
        rating.setUp(name,id);
        border.setRight(root);
    }



}
