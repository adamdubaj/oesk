package oesk.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import oesk.algorithms.*;
import oesk.databases.QueryExecuter;
import oesk.databases.Table;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller implements Initializable {
    private LinkedHashMap map, map1;
    private DecimalFormat df = new DecimalFormat("#.##");
    private float downloadAverage, uploadAverage;
    @FXML private Label labelDownload;
    @FXML private Label labelUpload;
    @FXML private Button btnRunTest;
    @FXML private Button btnShowTestChartOne;
    @FXML private ProgressIndicator progress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progress.setVisible(false);
        btnShowTestChartOne.setDisable(true);
    }

    @FXML
    private void compareWithOldies(ActionEvent e){
        System.out.println("button compareWithOldies");
        ArrayList<Table> list;
        QueryExecuter queryExecuter = new QueryExecuter();
        double download=0, upload=0;
        int idCount;
        list = queryExecuter.executeSelect("SELECT COUNT(id) AS id, AVG(downloadAverage) AS downloadAverage, AVG(uploadAverage) AS uploadAverage FROM `benchmark`");
        if(list.size() == 1){
            download = list.get(0).getDownloadAverage();
            upload = list.get(0).getUploadAverage();
            idCount = list.get(0).getId();

            //chart
            String downloadAverageOldies = "Srednia wczesniejszych download";
            String currentDownload = "Aktualny download";
            String uploadAveeageOldies = "Srednia wczesniejszych upload";
            String currentUpload = "Aktualny upload";

            Stage stage = new Stage();
            stage.setTitle("Wykres");
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final BarChart<String,Number> bc =
                    new BarChart<String,Number>(xAxis,yAxis);
            bc.setTitle("Porównanie z wczesniejszymi wynikami");
            //xAxis.setLabel("Srednie");
            yAxis.setLabel("Mbit/s");

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("DOWNLOAD");
            series1.getData().add(new XYChart.Data(downloadAverageOldies, download));
            series1.getData().add(new XYChart.Data(currentDownload, downloadAverage));
            XYChart.Series series2 = new XYChart.Series();
            series2.setName("UPLOAD");
            series2.getData().add(new XYChart.Data(uploadAveeageOldies, upload));
            series2.getData().add(new XYChart.Data(currentUpload, uploadAverage));

            Scene scene  = new Scene(bc,800,600);
            bc.getData().addAll(series1, series2);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    private void runTest(ActionEvent e) throws InterruptedException {
        //progress and buttons set
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            System.out.println("button runTest");
            Platform.runLater(() -> labelUpload.setText("UPLOAD"));
            Platform.runLater(() -> labelDownload.setText("DOWNLOAD"));
            Platform.runLater(() -> progress.setVisible(true));
            Platform.runLater(() -> btnRunTest.setDisable(true));
            Platform.runLater(() -> btnShowTestChartOne.setDisable(true));

            //DOWNLOAD
            DownloadTest downloadTest = new DownloadTest();
            downloadTest.downloadRun();
            while(!DownloadTest.isFinish){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            downloadAverage = (float)downloadTest.getDownloadAverage()/1000000;
            //labelDownload.setText("DOWNLOAD: " + df.format(downloadAverage) + " Mbit/s");
            Platform.runLater(() -> labelDownload.setText("DOWNLOAD: " + df.format(downloadAverage) + " Mbit/s"));
            map = downloadTest.getDownloadChartMap();

            //UPLOAD
            UploadTest uploadTest = new UploadTest();
            uploadTest.uploadRun();
            while(!UploadTest.isFinish){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            uploadAverage = (float)uploadTest.getUploadAverage()/1000000;
            //labelUpload.setText("UPLOAD: " + df.format(uploadAverage) + " Mbit/s");
            Platform.runLater(() -> labelUpload.setText("UPLOAD: " + df.format(uploadAverage) + " Mbit/s"));
            map1 = uploadTest.getUploadChartMap();

            //DATABASE
            String sqlInsert = "INSERT INTO `benchmark`(`downloadAverage`, `uploadAverage`) " +
                    "VALUES ('" + downloadAverage + "','" + uploadAverage + "')";
            QueryExecuter queryExecuter = new QueryExecuter();
            queryExecuter.executeInsert(sqlInsert);

            Platform.runLater(() -> progress.setVisible(false));
            Platform.runLater(() -> btnRunTest.setDisable(false));
            Platform.runLater(() -> btnShowTestChartOne.setDisable(false));
        });
    }

    @FXML
    private void showTestChartOne(ActionEvent e){
        System.out.println("button showTestChartOne");

        Stage stage = new Stage();
        stage.setTitle("Download - Upload");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis(0,100,10);
        final NumberAxis yAxis = new NumberAxis(0,50,1);
        xAxis.setLabel("Progress percent");
        yAxis.setLabel("Mbit/s");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Wykres download-upload");

        //defining a series
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        series.setName("download");
        series1.setName("upload");
        //usuwa koleczka z wykresu
        lineChart.setCreateSymbols(false);
        //populating the series with data

        map.forEach((k, v) -> series.getData().add(new XYChart.Data(v, k)));
        map1.forEach((k, v) -> series1.getData().add(new XYChart.Data(v, k)));

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        lineChart.getData().add(series1);

        stage.setScene(scene);
        stage.show();
    }
}