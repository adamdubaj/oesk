package oesk.algorithms;


import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static oesk.algorithms.FileServer.LOGGER;
import static oesk.algorithms.FileServer.SOCKET_TIMEOUT;

public class DownloadTest {
    private long downloadStartTime, downloadStopTime;
    private double downloadAverage;
    private ArrayList<Float> arrayListProgressPercent = new ArrayList<>();
    private LinkedHashMap<Double, Float> downloadChartMap = new LinkedHashMap<>();
    public static boolean isFinish = false;
    private int index;
    LinkedHashMap <Float, Double> temporaryMap = new LinkedHashMap<>();

    // instantiate speed examples
    final SpeedTestSocket speedTestSocket = new SpeedTestSocket();

    public void downloadRun(){
        Average average = new Average();
        isFinish = false;
        ArrayList<BigDecimal> arrayListDownload = new ArrayList<>();

        speedTestSocket.setSocketTimeout(SOCKET_TIMEOUT);

        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {
            @Override
            public void onCompletion(SpeedTestReport speedTestReport) {
                downloadStartTime = speedTestReport.getStartTime();
                downloadStopTime = System.nanoTime();
                downloadAverage = average.getAverageValue(arrayListDownload);
                temporaryMap.forEach((k, v) -> downloadChartMap.put(v, k));
                isFinish = true;
                index = 0;

                //temporaryMap.forEach((k, v) -> System.out.println("" + k + " ---- " + v));
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {

                if(percent != 0f && index == 0){
                    temporaryMap.put(report.getProgressPercent(), report.getTransferRateBit().doubleValue()/1000000);
                    arrayListDownload.add(report.getTransferRateBit());
                    index++;
                } else if(percent != 0f && index > 0){
                    if(temporaryMap.containsKey(report.getProgressPercent())){
                        if(report.getTransferRateBit().doubleValue()/1000000 < temporaryMap.get(report.getProgressPercent())){
                            temporaryMap.replace(report.getProgressPercent(), report.getTransferRateBit().doubleValue()/1000000);
                            arrayListDownload.set(arrayListDownload.size()-1, report.getTransferRateBit());
                        }
                    } else if(percent != 0f){
                        temporaryMap.put(report.getProgressPercent(), report.getTransferRateBit().doubleValue()/1000000);
                        arrayListDownload.add(report.getTransferRateBit());
                    }
                }
            }

            @Override
            public void onError(SpeedTestError speedTestError, String errorMessage) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("error " + speedTestError + " : " + errorMessage);
                }
            }
        });
        //speedTestSocket.startFixedUpload("http://ipv4.ikoula.testdebit.info/", 10000000, 10000);
        speedTestSocket.startFixedDownload(FileServer.SPEED_TEST_SERVER_URI_DOWNLOAD, FileServer.MAX_TEST_DURATION, FileServer.SPEED_TEST_REPORT_INTERVAL);
    }

    // ---------------- GETTERS AND SETTERS ----------------
    public long getDownloadStartTime() {
        return downloadStartTime;
    }

    public void setDownloadStartTime(long downloadStartTime) {
        this.downloadStartTime = downloadStartTime;
    }

    public long getDownloadStopTime() {
        return downloadStopTime;
    }

    public void setDownloadStopTime(long downloadStopTime) {
        this.downloadStopTime = downloadStopTime;
    }

    public double getDownloadAverage() {
        return downloadAverage;
    }

    public void setDownloadAverage(double downloadAverage) {
        this.downloadAverage = downloadAverage;
    }

    public ArrayList<Float> getProgressPercent() {
        return arrayListProgressPercent;
    }

    public LinkedHashMap<Double, Float> getDownloadChartMap() {
        return downloadChartMap;
    }
}
