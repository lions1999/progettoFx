package logic.controller.guicontroller.first.resident;

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
import logic.controller.guicontroller.first.general.Main1GUI;
import logic.model.Apartment;
import logic.model.Fee;
import logic.model.UserSingleton;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AptInfoGUI extends Main1GUI implements Initializable {
    private static final UserSingleton sg = UserSingleton.getInstance();
    private final ChartGUI chart = new ChartGUI();
    private final ApartmentController aptController = new ApartmentController();
    private final FeeController feeController = new FeeController();
    private final List<String> seriesName = Arrays.asList("Water","Gas","Electricity","Admin","Parking","Elevator","Pet","WiFi");
    private final String choose = "Choose Chart";
    private final String barChart = "Bar Chart";
    private final String pieChart = "Pie Chart";
    private final String lineChart = "Line Chart";

    @FXML private ComboBox<String> chartCombo;
    @FXML private CheckBox lastMonthBtn;
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
    public void initialize(URL location, ResourceBundle resources){setUp();}

    public void submitLastMonth() {
        List<Double> chartDataList = getList(sg.getPastfee());
        pastGrid.setVisible(lastMonthBtn.isSelected());
        switch (chartCombo.getValue()) {
            default:
            case choose:
                firstBorder.setRight(null);
                break;
            case barChart:
                BarChart<String, Number> oldBarChart = (BarChart) firstBorder.getRight();
                if (lastMonthBtn.isSelected()) {
                    XYChart.Series<String, Number> series2 = chart.newSeries(chartDataList, seriesName, "Outgoings Last Month");
                    oldBarChart.getData().add(series2);
                } else {
                    if (oldBarChart.getData().size() == 2) {
                        oldBarChart.getData().remove(1);
                    }
                }
                break;
            case lineChart:
                LineChart<String, Number> oldLineChart = (LineChart) firstBorder.getRight();
                if (lastMonthBtn.isSelected()) {
                    XYChart.Series<String, Number> series2 = chart.newSeries(chartDataList, seriesName, "Outgoings Last Month");
                    oldLineChart.getData().add(series2);
                } else {
                    if (oldLineChart.getData().size() == 2) {
                        oldLineChart.getData().remove(1);
                    }
                }
                break;
            case pieChart:
                Pane oldPieChart = (Pane) firstBorder.getRight();
                VBox vBox = (VBox) oldPieChart.getChildren().get(0);
                if (lastMonthBtn.isSelected()) {
                    ObservableList<PieChart.Data> valueList = chart.value(chartDataList, seriesName);
                    PieChart pc = chart.buildPieChart(valueList, "Outgoing Last Month");
                    vBox.getChildren().add(pc);
                } else {
                    if (vBox.getChildren().size() == 2) {
                        vBox.getChildren().remove(1);
                    }
                }
                break;
        }
    }

    public void submitTypeChart() {
        List<Double> chartDataList = getList(sg.getFee());
        String chartTitle = "Outgoing Current Month";
        switch (chartCombo.getValue()){
            default: case choose:
                firstBorder.setRight(null);
                break;
            case barChart:
                BarChart<String, Number> bc = chart.buildBarChart("Fees","","Outgoings");
                XYChart.Series<String, Number> bcSeries = chart.newSeries(chartDataList,seriesName,chartTitle);
                bc.getData().add(bcSeries);
                firstBorder.setRight(bc);
                break;
            case pieChart:
                ObservableList<PieChart.Data> valueList = chart.value(chartDataList,seriesName);
                PieChart pc = chart.buildPieChart(valueList,chartTitle);
                VBox vbox = new VBox(pc);
                Pane paneC = new Pane(vbox);
                firstBorder.setRight(paneC);
                break;
            case lineChart:
                LineChart<String ,Number> lc = chart.buildLineChart("Fees","","Outgoings");
                XYChart.Series<String, Number> lcSeries = chart.newSeries(chartDataList,seriesName,chartTitle);
                lc.getData().add(lcSeries);
                firstBorder.setRight(lc);
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
            chartCombo.getItems().addAll(choose,barChart,pieChart,lineChart);
            chartCombo.setValue(choose);
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