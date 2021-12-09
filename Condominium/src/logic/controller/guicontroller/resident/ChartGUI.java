package logic.controller.guicontroller.resident;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import java.util.List;

public class ChartGUI {
    public BarChart<String,Number> BarChart(String xAxis, String yAxis, String Title){
        final CategoryAxis categoryAxis = new CategoryAxis();
        final NumberAxis numberAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<>(categoryAxis,numberAxis);
        bc.setTitle(Title);
        categoryAxis.setLabel(xAxis);
        numberAxis.setLabel(yAxis);
        return bc;
    }

    public PieChart NewPieChart(ObservableList<PieChart.Data> valueList, String Title){
        PieChart pieChart = new PieChart(valueList);
        pieChart.setTitle(Title);
        pieChart.setStyle("-fx-pref-height: 350");
        return pieChart;
    }

    public LineChart NewLineChart(String xAxis, String yAxis, String Title){
        final CategoryAxis categoryAxis = new CategoryAxis();
        final NumberAxis numberAxis = new NumberAxis();
        LineChart lineChart = new LineChart(categoryAxis,numberAxis);
        categoryAxis.setLabel(xAxis);
        numberAxis.setLabel(yAxis);
        lineChart.setTitle(Title);
        return lineChart;
    }

    public XYChart.Series<String,Number> NewSeries(List<Double> DataList, List<String> seriesName, String name){
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName(name);
        for (int i = 0;i < DataList.size(); i++){
            series.getData().add(new XYChart.Data<String, Number>(seriesName.get(i),DataList.get(i)));
        }
        return series;
    }

    public ObservableList<PieChart.Data> value(List<Double> DataList,List<String> seriesName){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (int i = 0; i < DataList.size(); i++){
            data.add(new PieChart.Data(seriesName.get(i),DataList.get(i)));
        }
        return data;
    }
}
