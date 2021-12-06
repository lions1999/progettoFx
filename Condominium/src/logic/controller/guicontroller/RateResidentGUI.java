package logic.controller.guicontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.engineeringclasses.dao.ApartmentDAO;
import logic.model.Apartment;
import logic.model.UserSingleton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RateResidentGUI extends MainGUI implements Initializable {

    private final ApartmentDAO ourDb = new ApartmentDAO();
    private final UserSingleton sg = UserSingleton.getInstance();

    @FXML private TableView<Apartment> Table;
    @FXML private TableColumn<?, ?> aptAddress;
    @FXML private TableColumn<?, ?> resName;
    @FXML private TableColumn<?, ?> aptNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUp();
        Apartment apartment;

        try {
            apartment = ourDb.checkApartments(sg.getUserID(),sg.getAddress(),"apt_own");
            final ObservableList<Apartment> list = FXCollections.observableArrayList();
            list.add(apartment);
            System.out.println(apartment);
            Table.setItems(list);
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

    public void submitUser() throws IOException {
        Pane pane = new Pane();
        FXMLLoader loader = view.loader("RatingUser");
        Parent root = loader.load();
        RatingUserGUI rating = loader.getController();
        String name = Table.getSelectionModel().getSelectedItem().getResident();
        rating.setUp(name);
        border.setRight(root);
    }

}
