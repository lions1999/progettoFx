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
import logic.engineeringclasses.dao.ApartmentDAO;
import logic.model.Apartment;
import logic.model.UserSingleton;

public class AptInfoGUI extends MainGUI implements Initializable{
    private static final UserSingleton sg = UserSingleton.getInstance();
    private final ChartGUI chart = new ChartGUI();
    private final ApartmentDAO ourDb = new ApartmentDAO();
    private final List<String> seriesName = Arrays.asList("Gas","Water","Energy","Parking");
    final ObservableList<Apartment> apartment = ourDb.checkApartments(sg.getUserID(),"apt_res");

    @FXML private ComboBox<String> chartCombo;
    @FXML private Text tfEnergy;
    @FXML private Text tfGas;
    @FXML private Text tfWater;
    @FXML private Text tfNumber;
    @FXML private Text tfOwner;
    @FXML private CheckBox LastYearBtn;

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
                XYChart.Series series2 = chart.NewSeries(Arrays.asList("700", "800", "359","400"), seriesName, "Outgoings Last Year");
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
                XYChart.Series series2 = chart.NewSeries(Arrays.asList("700", "800", "359","400"), seriesName, "Outgoings Last Year");
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
                        new PieChart.Data("Gas",700),
                        new PieChart.Data("Water",800),
                        new PieChart.Data("Energy",359),
                        new PieChart.Data("Parking",400)
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
        switch (chartCombo.getValue()){
            case "Choose Chart":
                border.setRight(null);
                break;
            case "Bar Chart":
                System.out.println("case 1");
                BarChart bc = chart.BarChart("Fees","","Outgoings");
                List BcDataList = Arrays.asList(apartment.get(0).getGas(), apartment.get(0).getWater(), apartment.get(0).getEnergy(),apartment.get(0).getParking());
                XYChart.Series BcSeries = chart.NewSeries(BcDataList,seriesName,"Outgoing Current Year");
                bc.getData().add(BcSeries);
                System.out.println("case 1");
                border.setRight(bc);
                break;
            case "Pie Chart":
                System.out.println("case 2");
                ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                    new PieChart.Data("Gas",Integer.parseInt(apartment.get(0).getGas())),
                    new PieChart.Data("Water",Integer.parseInt(apartment.get(0).getWater())),
                    new PieChart.Data("Energy",Integer.parseInt(apartment.get(0).getEnergy())),
                    new PieChart.Data("Parking",Integer.parseInt(apartment.get(0).getParking()))
                );
                PieChart pc = chart.NewPieChart(valueList,"Outgoing Current Year");
                VBox vbox = new VBox(pc);
                Pane paneC = new Pane(vbox);
                border.setRight(paneC);
                break;
            case "Line Chart":
                System.out.println("case 3");
                LineChart lc = chart.NewLineChart("Fees","","Outgoings");
                List AcDataList = Arrays.asList(apartment.get(0).getGas(), apartment.get(0).getWater(), apartment.get(0).getEnergy(),apartment.get(0).getParking());
                XYChart.Series LcSeries = chart.NewSeries(AcDataList,seriesName,"Outgoing Current Year");
                lc.getData().add(LcSeries);
                border.setRight(lc);
                break;
        }
        submitLastYear();
    }

    public void setUp(){
        chartCombo.getItems().addAll("Choose Chart","Bar Chart","Pie Chart","Line Chart");
        chartCombo.setValue("Choose Chart");

        tfOwner.setText(apartment.get(0).getOwner());
        tfNumber.setText(apartment.get(0).getNumber());
        tfWater.setText(apartment.get(0).getWater()+"€");
        tfEnergy.setText(apartment.get(0).getEnergy()+"€");
        tfGas.setText(apartment.get(0).getGas()+"€");
        submitTypeChart();

    }
}