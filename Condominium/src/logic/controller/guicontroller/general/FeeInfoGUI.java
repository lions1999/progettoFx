package logic.controller.guicontroller.general;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.FeeController;
import logic.controller.applicationcontroller.PatternController;
import logic.engineeringclasses.bean.FeeBean;
import logic.model.Fee;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class FeeInfoGUI {

    private final FeeController controller = new FeeController();
    private final PatternController pattern = new PatternController();
    private final ApartmentController aptCtrl = new ApartmentController();

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

    private boolean checkErrorTf(TextField tf){
        if(!tf.editableProperty().get()) return false;
        if(tf.editableProperty().get() && tf.getText().equals("0.0")) return true;
        double d = Double.parseDouble(tf.getText());
        if(d < 0) return true;
        int length = String.valueOf((int)d).length();
        return length > 3;
    }

    private String format(TextField tf){
        if(!tf.editableProperty().get()) return "0.0";
        double d = Double.parseDouble(tf.getText());
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }

    public boolean check(){
        if(checkErrorTf(tfWater)) return false;
        if(checkErrorTf(tfGas)) return false;
        if(checkErrorTf(tfElectricity)) return false;
        if(checkErrorTf(tfAdministrative)) return false;
        if(checkErrorTf(tfElevator)) return false;
        if(checkErrorTf(tfPet)) return false;
        return !checkErrorTf(tfWifi);
    }

    public FeeBean getFees(){
    return feeBean(format(tfWater),format(tfGas),format(tfElectricity),format(tfAdministrative),
            format(tfPark),format(tfElevator),format(tfPet),format(tfWifi));
    }

    public void loadFeeInfo(String usrID) throws SQLException {
        String aptId = aptCtrl.loadAptId(usrID);
        Fee fee = controller.loadFees(aptId,"fee");
        tfWater.setText(fee.getWater().toString());
        tfGas.setText(fee.getGas().toString());
        tfElectricity.setText(fee.getElect().toString());
        tfAdministrative.setText(fee.getAdmin().toString());
        if(!fee.getPark().toString().equals("0.0")) tfPark.setText(fee.getPark().toString());
        if(!fee.getElevator().toString().equals("0.0")) tfElevator.setText(fee.getElevator().toString());
        if(!fee.getPet().toString().equals("0.0")) tfPet.setText(fee.getPet().toString());
        if(!fee.getWifi().toString().equals("0.0")) tfWifi.setText(fee.getWifi().toString());
    }

    public void setUp(String address) throws SQLException {
        pattern.textFilter(tfWater);
        pattern.textFilter(tfGas);
        pattern.textFilter(tfElectricity);
        pattern.textFilter(tfAdministrative);
        Fee fee = controller.setUpFees(address);
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
