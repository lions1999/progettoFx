package logic.controller.guicontroller;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.controller.applicationcontroller.SendEmail;
import logic.controller.applicationcontroller.ViewController;
import logic.model.Apartment;
import logic.model.UserSingleton;
import logic.engineeringclasses.dao.ApartmentDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CondInfoGUI extends Application implements Initializable{

        private ViewController view = new ViewController();
        private static UserSingleton sg = UserSingleton.getInstance();
        private ApartmentDAO ourDb = new ApartmentDAO();


        @FXML private TextField OwnerFullName;
        @FXML private TextField ResidentFullName;
        @FXML private Pane InvPane;
        @FXML private Text ResidentText;
        @FXML private Text OwnerText;
        @FXML private ImageView userImg;
        @FXML private TextArea mailText;
        @FXML private Button sendMailBtn;
        @FXML private TableColumn<Apartment, String> IdCol;
        @FXML private TableColumn<Apartment, String> OwnerCol;
        @FXML private TableColumn<Apartment, String> ResidentCol;
        @FXML private TableColumn<Apartment, String> TaxCol;
        @FXML private TableView<Apartment> condominiumTable;


        @Override
        public void initialize(URL location, ResourceBundle resources){
            sendMailBtn.setDisable(true);

            OwnerCol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("owner"));
            ResidentCol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("resident"));
            IdCol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("number"));

            final ObservableList<Apartment> apartment;
            try {
                apartment = ourDb.checkApartmentsByVia(sg.getAddress());
                condominiumTable.setItems(apartment);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void start(Stage primaryStage) throws Exception {
            view.loadPage("CondInfo");
        }


        public void submitUser(MouseEvent mouseEvent) throws SQLException {
            mailText.setText("");
            sendMailBtn.setDisable(true);
            InvPane.setVisible(true);
            OwnerFullName.setText(condominiumTable.getSelectionModel().getSelectedItem().getOwner());
            ResidentFullName.setText(condominiumTable.getSelectionModel().getSelectedItem().getResident());
        }

        public void sendMail(ActionEvent actionEvent) throws SQLException {
            String mail = ourDb.checkMailById(ourDb.checkUserAptFromNumber((condominiumTable.getSelectionModel().getSelectedItem().getNumber()),"apt_own"));
            System.out.println(mail);

            String subject = "Hi from Condominium!";
            String body = mailText.getText();
            String message = String.format(body);

            String[] recipients = new String[] { mail };
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm to send email?");
            alert.setResizable(false);
            alert.setContentText("Are you sure to send mail to "+mail+" with text '"+mailText.getText()+"' as message?");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {
                new SendEmail().send(recipients, recipients, subject, message);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information");
                alert1.setHeaderText("Mail sent to "+mail+"!");
                alert1.setContentText("");
                alert1.show();
            }else{
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information");
                alert1.setHeaderText("Mail not sent!");
                alert1.setContentText("");
                alert1.show();
            }
        }

        public void enableButton(KeyEvent keyEvent) {
            if(mailText.getText().matches("( *)")){
                sendMailBtn.setDisable(true);
            }else{
                sendMailBtn.setDisable(false);
            }
        }
}
