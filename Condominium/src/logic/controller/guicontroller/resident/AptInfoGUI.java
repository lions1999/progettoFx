package logic.controller.guicontroller.resident;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.FeeController;
import logic.controller.guicontroller.general.MainGUI;
import logic.model.Apartment;
import logic.model.Fee;
import logic.model.UserSingleton;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AptInfoGUI extends MainGUI implements Initializable {
    private static final UserSingleton sg = UserSingleton.getInstance();
    private final ChartGUI chart = new ChartGUI();
    private final ApartmentController aptController = new ApartmentController();
    private final FeeController feeController = new FeeController();
    private final List<String> seriesName = Arrays.asList("Water","Gas","Energy","Admin","Parking","Elevator","Pet","WiFi");

    @FXML private ComboBox<String> chartCombo;
    @FXML private CheckBox LastMonthBtn;
    @FXML private Text tfNumber;
    @FXML private Text tfWater;
    @FXML private Text tfGas;
    @FXML private Text tfEnergy;
    @FXML private Text tfAdmin;
    @FXML private Text tfPark;
    @FXML private Text tfElevator;
    @FXML private Text tfPet;
    @FXML private Text tfWifi;
    @FXML private Text tfOwner;
    @FXML private Text tfPastAdmin;
    @FXML private Text tfPastElevator;
    @FXML private Text tfPastEnergy;
    @FXML private Text tfPastGas;
    @FXML private Text tfPastPark;
    @FXML private Text tfPastPet;
    @FXML private Text tfPastWater;
    @FXML private Text tfPastWifi;
    @FXML private GridPane pastGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        setUp();
    }


    public void submitLastMonth() {
        List<Double> ChartDataList = getList(sg.getPastfee());
        pastGrid.setVisible(LastMonthBtn.isSelected());
        if (chartCombo.getValue().equals("Bar Chart")) {
            BarChart oldBarChart = (BarChart) border.getRight();
            if (LastMonthBtn.isSelected()) {
                XYChart.Series<String,Number> series2 = chart.NewSeries(ChartDataList, seriesName, "Outgoings Last Month");
                oldBarChart.getData().add(series2);
            } else {
                try {
                    oldBarChart.getData().remove(1);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else if (chartCombo.getValue().equals("Line Chart")){
            LineChart oldLineChart = (LineChart) border.getRight();
            if (LastMonthBtn.isSelected()){
                XYChart.Series series2 = chart.NewSeries(ChartDataList, seriesName, "Outgoings Last Month");
                oldLineChart.getData().add(series2);
            } else {
                try {
                    oldLineChart.getData().remove(1);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
        else if (chartCombo.getValue().equals("Pie Chart")){
            Pane oldPieChart = (Pane) border.getRight();
            VBox vBox = (VBox) oldPieChart.getChildren().get(0);
            if (LastMonthBtn.isSelected()){
                ObservableList<PieChart.Data> valueList = chart.value(ChartDataList,seriesName);
                PieChart pc = chart.NewPieChart(valueList,"Outgoing Last Month");
                vBox.getChildren().add(pc);
            } else {
                try {
                    vBox.getChildren().remove(1);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            border.setRight(oldPieChart);
        }
    }

    public void submitTypeChart() {
        System.out.println(chartCombo.getValue());
        List<Double> ChartDataList = getList(sg.getFee());
        switch (chartCombo.getValue()){
            case "Choose Chart":
                border.setRight(null);
                break;
            case "Bar Chart":
                System.out.println("case 1");
                BarChart<String, Number> bc = chart.BarChart("Fees","","Outgoings");
                XYChart.Series<String, Number> BcSeries = chart.NewSeries(ChartDataList,seriesName,"Outgoing Current Year");
                bc.getData().add(BcSeries);
                System.out.println("case 1");
                border.setRight(bc);
                break;
            case "Pie Chart":
                System.out.println("case 2");
                ObservableList<PieChart.Data> valueList = chart.value(ChartDataList,seriesName);
                PieChart pc = chart.NewPieChart(valueList,"Outgoing Current Year");
                VBox vbox = new VBox(pc);
                Pane paneC = new Pane(vbox);
                border.setRight(paneC);
                break;
            case "Line Chart":
                System.out.println("case 3");
                LineChart lc = chart.NewLineChart("Fees","","Outgoings");
                XYChart.Series<String, Number> LcSeries = chart.NewSeries(ChartDataList,seriesName,"Outgoing Current Year");
                lc.getData().add(LcSeries);
                border.setRight(lc);
                break;
        }
        submitLastMonth();
    }

    private void noFee(Text t){
        t.setText("Unavailable in your Condominium");
    }

    public void setUp(){

        try {
            pastGrid.setVisible(false);
            Fee boolFee = feeController.setUpFees(sg.getAddress());
            final Apartment apartment = aptController.checkApartments(sg.getUserID(),sg.getAddress(),"apt_res");
            Fee currentFee = feeController.loadFees(aptController.loadApartmentId(apartment.getNumber(),sg.getAddress()),"fee");
            sg.setFee(currentFee);
            Fee pastFee = feeController.loadFees(aptController.loadApartmentId(apartment.getNumber(),sg.getAddress()),"pastfee" );
            sg.setPastfee(pastFee);

            if (boolFee.getAvailablePark()) { tfPark.setText(currentFee.getPark() + " €"); tfPastPark.setText(pastFee.getPark() + " €");}
            else {noFee(tfPark); noFee(tfPastPark);}
            if (boolFee.getAvailableElevator()) { tfElevator.setText(currentFee.getElevator() + " €"); tfPastElevator.setText(pastFee.getElevator()+" €"); }
            else {noFee(tfElevator); noFee(tfPastElevator);}
            if (boolFee.getAvailablePet()) { tfPet.setText(currentFee.getPet() + " €"); tfPastPet.setText(pastFee.getPet()+" €"); }
            else {noFee(tfPet); noFee(tfPastPet);}
            if (boolFee.getAvailableWifi()) { tfWifi.setText(currentFee.getWifi()+" €"); tfPastWifi.setText(pastFee.getWifi()+" €"); }
            else {noFee(tfWifi); noFee(tfPastWifi);}

            tfWater.setText(currentFee.getWater()+" €");
            tfPastWater.setText(pastFee.getWater()+" €");
            tfEnergy.setText(currentFee.getElect()+" €");
            tfPastEnergy.setText(pastFee.getElect()+" €");
            tfGas.setText(currentFee.getGas()+" €");
            tfPastGas.setText(pastFee.getGas()+" €");
            tfAdmin.setText(currentFee.getAdmin()+" €");
            tfPastAdmin.setText(pastFee.getAdmin()+" €");
            chartCombo.getItems().addAll("Choose Chart","Bar Chart","Pie Chart","Line Chart");
            chartCombo.setValue("Choose Chart");
            tfOwner.setText(apartment.getOwner());
            tfNumber.setText(apartment.getNumber());
            submitTypeChart();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Double> getList(Fee fee){
        return Arrays.asList(fee.getWater(),fee.getGas(),fee.getElect(),fee.getAdmin(),fee.getPark(),fee.getElevator(),fee.getPet(),fee.getWifi());
    }
}