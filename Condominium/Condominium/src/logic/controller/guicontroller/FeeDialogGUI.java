package logic.controller.guicontroller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.controller.applicationcontroller.FeeController;
import logic.controller.applicationcontroller.PatternController;
import logic.engineeringclasses.bean.FeeBean;
import logic.model.Fee;
import logic.model.UserSingleton;
import java.sql.SQLException;

public class FeeDialogGUI {

    private final FeeController controller = new FeeController();
    private final PatternController pattern = new PatternController();
    UserSingleton sg = UserSingleton.getInstance();

    @FXML private TextField tfWater;
    @FXML private TextField tfGas;
    @FXML private TextField tfElectricity;
    @FXML private TextField tfAdministrative;
    @FXML private TextField tfPark;
    @FXML private TextField tfElevator;
    @FXML private TextField tfPet;
    @FXML private TextField tfWifi;

    private void noFee(TextField tf){
        tf.setText("Unavailable in your Condominium");
        tf.setEditable(false);
    }

    private String getTf(TextField tf){
        if(tf.editableProperty().get()) return tf.getText();
        return "0.0";
    }

    public boolean check(){
        if(tfWater.getText().equals("0.0")) return false;
        if(tfGas.getText().equals("0.0")) return false;
        if(tfElectricity.getText().equals("0.0")) return false;
        if(tfAdministrative.getText().equals("0.0")) return false;
        if(tfPark.editableProperty().get() && tfPark.getText().equals("0.0")) return false;
        if(tfElevator.editableProperty().get() && tfElevator.getText().equals("0.0")) return false;
        if(tfPet.editableProperty().get() && tfPet.getText().equals("0.0")) return false;
        if(tfWifi.editableProperty().get() && tfWifi.getText().equals("0.0")) return false;
        return true;
    }

    public FeeBean getFees(){
        return feeBean(tfWater.getText(),tfGas.getText(),tfElectricity.getText(),tfAdministrative.getText()
                ,getTf(tfPark),getTf(tfElevator),getTf(tfPet),getTf(tfWifi));
    }

    public void setUp() throws SQLException {
        pattern.textFilter(tfWater);
        pattern.textFilter(tfGas);
        pattern.textFilter(tfElectricity);
        pattern.textFilter(tfAdministrative);
        Fee fee = controller.setUpFees(sg.getAddress());
        if(!fee.getAvailablePark()) noFee(tfPark);
        else pattern.textFilter(tfPark);
        if(!fee.getAvailableElevator()) noFee(tfElevator);
        else  pattern.textFilter(tfElevator);
        if(!fee.getAvailablePet()) noFee(tfPet);
        else pattern.textFilter(tfPet);
        if(!fee.getAvailableWifi()) noFee(tfWifi);
        else pattern.textFilter(tfWifi);
    }

    private FeeBean feeBean(String water,String gas,String elect,String admin,String park,String elev,String pet,String wifi){
        FeeBean bean = new FeeBean();
        bean.setWater(Double.valueOf(water));
        bean.setGas(Double.valueOf(gas));
        bean.setElect(Double.valueOf(elect));
        bean.setAdmin(Double.valueOf(admin));
        bean.setPark(Double.valueOf(park));
        bean.setElevator(Double.valueOf(elev));
        bean.setPet(Double.valueOf(pet));
        bean.setWifi(Double.valueOf(wifi));
        return bean;
    }
}
