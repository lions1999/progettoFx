package logic.controller.guicontroller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.FeeController;
import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.dao.ApartmentDAO;
import logic.engineeringclasses.dao.FeeDAO;
import logic.model.Apartment;
import logic.model.Fee;
import logic.model.UserSingleton;

public class AptInfoGUI extends MainGUI implements Initializable {
    private static final UserSingleton sg = UserSingleton.getInstance();
    private final ChartGUI chart = new ChartGUI();
    private final FeeController feeController = new FeeController();
    private final ApartmentDAO apartmentDAO = new ApartmentDAO();
    private final FeeDAO feeDAO = new FeeDAO();
    private final List<String> seriesName = Arrays.asList("Gas","Water","Energy","Admin","Parking","Elevator","Pet","WiFi");
    final Apartment apartment = apartmentDAO.checkApartments(sg.getUserID(),sg.getAddress(),"apt_res");
    final Fee fee1 = feeDAO.loadFees(apartmentDAO.loadApartmentId(apartment.getNumber(),sg.getAddress()));

    @FXML private ComboBox<String> chartCombo;
    @FXML private Text tfEnergy;
    @FXML private Text tfGas;
    @FXML private Text tfWater;
    @FXML private Text tfNumber;
    @FXML private Text tfOwner;
    @FXML private Text tfAdmin;
    @FXML private CheckBox LastYearBtn;
    @FXML private Text tfElevator;
    @FXML private Text tfPark;
    @FXML private Text tfPet;
    @FXML private Text tfWifi;

    public AptInfoGUI() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        setUp();
    }


    public void submitLastYear() {
        if (chartCombo.getValue().equals("Bar Chart")) {
            BarChart oldBarChart = (BarChart) border.getRight();
            if (LastYearBtn.isSelected()) {
                XYChart.Series series2 = chart.NewSeries(Arrays.asList(50,30,43,23,0,0,18,38), seriesName, "Outgoings Last Year");
                oldBarChart.getData().add(series2);
            } else {
                try {
                    oldBarChart.getData().remove(1);
                } catch (Exception e){

                }
            }
        }
        else if (chartCombo.getValue().equals("Line Chart")){
            LineChart oldLineChart = (LineChart) border.getRight();
            if (LastYearBtn.isSelected()){
                XYChart.Series series2 = chart.NewSeries(Arrays.asList(50,30,43,23,0,0,18,38), seriesName, "Outgoings Last Year");
                oldLineChart.getData().add(series2);
            } else {
                try {
                    oldLineChart.getData().remove(1);
                } catch (Exception e){

                }
            }

        }
        else if (chartCombo.getValue().equals("Pie Chart")){
            Pane oldPieChart = (Pane) border.getRight();
            VBox vBox = (VBox) oldPieChart.getChildren().get(0);
            if (LastYearBtn.isSelected()){
                ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                        new PieChart.Data("Gas",50),
                        new PieChart.Data("Water",30),
                        new PieChart.Data("Energy",43),
                        new PieChart.Data("Parking",0),
                        new PieChart.Data("Admin",23),
                        new PieChart.Data("WiFi",38),
                        new PieChart.Data("Elevator",0),
                        new PieChart.Data("Pet",18)
                );
                PieChart pc = chart.NewPieChart(valueList,"Outgoing Last Year");
                vBox.getChildren().add(pc);
            } else {
                try {
                    vBox.getChildren().remove(1);
                } catch (Exception e){

                }
            }
            border.setRight(oldPieChart);
        }
    }

    public void submitTypeChart() {
        System.out.println(chartCombo.getValue());
        List ChartDataList = Arrays.asList(fee1.getWater(),fee1.getGas(),fee1.getElect(),fee1.getAdmin(),fee1.getPark(),fee1.getElevator(),fee1.getPet(),fee1.getWifi());
        switch (chartCombo.getValue()){
            case "Choose Chart":
                border.setRight(null);
                break;
            case "Bar Chart":
                System.out.println("case 1");
                BarChart bc = chart.BarChart("Fees","","Outgoings");
                XYChart.Series BcSeries = chart.NewSeries(ChartDataList,seriesName,"Outgoing Current Year");
                bc.getData().add(BcSeries);
                System.out.println("case 1");
                border.setRight(bc);
                break;
            case "Pie Chart":
                System.out.println("case 2");
                ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                    new PieChart.Data("Gas",fee1.getGas()),
                    new PieChart.Data("Water",fee1.getWater()),
                    new PieChart.Data("Energy",fee1.getElect()),
                    new PieChart.Data("Parking",fee1.getPark()),
                    new PieChart.Data("Admin",fee1.getAdmin()),
                    new PieChart.Data("WiFi",fee1.getWifi()),
                    new PieChart.Data("Elevator",fee1.getElevator()),
                    new PieChart.Data("Pet",fee1.getPet())
                );
                PieChart pc = chart.NewPieChart(valueList,"Outgoing Current Year");
                VBox vbox = new VBox(pc);
                Pane paneC = new Pane(vbox);
                border.setRight(paneC);
                break;
            case "Line Chart":
                System.out.println("case 3");
                LineChart lc = chart.NewLineChart("Fees","","Outgoings");
                XYChart.Series LcSeries = chart.NewSeries(ChartDataList,seriesName,"Outgoing Current Year");
                lc.getData().add(LcSeries);
                border.setRight(lc);
                break;
        }
        submitLastYear();
    }

    private void noFee(Text t){
        t.setText("Unavailable in your Condominium");
    }

    public void setUp(){

        try {
            Fee boolFee = feeController.setUpFees(sg.getAddress());

            if (boolFee.getAvailablePark()) tfPark.setText(fee1.getPark()+" €");
            else noFee(tfPark);
            if (boolFee.getAvailableElevator()) tfElevator.setText(fee1.getElevator()+" €");
            else noFee(tfElevator);
            if (boolFee.getAvailablePet()) tfPet.setText(fee1.getPet()+" €");
            else noFee(tfPet);
            if (boolFee.getAvailableWifi()) tfWifi.setText(fee1.getWifi()+" €");
            else noFee(tfWifi);
            tfWater.setText(fee1.getWater()+" €");
            tfEnergy.setText(fee1.getElect()+" €");
            tfGas.setText(fee1.getGas()+" €");
            tfAdmin.setText(fee1.getAdmin()+" €");
            chartCombo.getItems().addAll("Choose Chart","Bar Chart","Pie Chart","Line Chart");
            chartCombo.setValue("Choose Chart");
            tfOwner.setText(apartment.getOwner());
            tfNumber.setText(apartment.getNumber());
            submitTypeChart();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("AO");
        }
    }
}