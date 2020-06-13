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

public class UploadTest {
    final SpeedTestSocket speedTestSocket = new SpeedTestSocket();
    private LinkedHashMap<Double, Float> uploadChartMap = new LinkedHashMap<>();
    private long uploadStartTime, uploadStopTime;
    private double uploadAverage;
    public static boolean isFinish = false;
    private float temporaryPercent = 0f;
    private BigDecimal temporaryRateBit;
    private int index = 0;
    private LinkedHashMap<Float, Double> temporaryMap = new LinkedHashMap<>();
    ArrayList<BigDecimal> temporaryList = new ArrayList<>();

    public void uploadRun(){
        Average average = new Average();
        isFinish = false;
        ArrayList<BigDecimal> arrayListUpload = new ArrayList<>();
        speedTestSocket.setSocketTimeout(SOCKET_TIMEOUT);

        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {
            @Override
            public void onCompletion(SpeedTestReport speedTestReport) {
                uploadStartTime = speedTestReport.getStartTime();
                uploadStopTime = System.nanoTime();
                uploadAverage = average.getAverageValue(arrayListUpload);
                isFinish = true;
                index = 0;
                temporaryMap.forEach((k, v) -> uploadChartMap.put(v, k));
                //temporaryMap.forEach((k, v) -> arrayListUpload.add(new BigDecimal(v*1000000)));

                //temporaryMap.forEach((k, v) -> System.out.println("" + k + " ---- " + v));
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {

                if(percent != 0f && index == 0){
                    temporaryMap.put(report.getProgressPercent(), report.getTransferRateBit().doubleValue()/1000000);
                    arrayListUpload.add(report.getTransferRateBit());
                    index++;
                } else if(percent != 0f && index > 0){
                    if(temporaryMap.containsKey(report.getProgressPercent())){
                        if(report.getTransferRateBit().doubleValue()/1000000 < temporaryMap.get(report.getProgressPercent())){
                            temporaryMap.replace(report.getProgressPercent(), report.getTransferRateBit().doubleValue()/1000000);
                            arrayListUpload.set(arrayListUpload.size()-1, report.getTransferRateBit());
                        }
                    } else if(percent != 0f){
                        temporaryMap.put(report.getProgressPercent(), report.getTransferRateBit().doubleValue()/1000000);
                        arrayListUpload.add(report.getTransferRateBit());
                    }
                }

                /*
                //PONIZEJ DZIALA
                if(report.getTransferRateBit().doubleValue()/1000000 != 0.0){
                    temporaryPercent = report.getProgressPercent();
                    temporaryRateBit = report.getTransferRateBit();
                    arrayListUpload.add(report.getTransferRateBit());
                    uploadChartMap.put(report.getTransferRateBit().doubleValue()/1000000, report.getProgressPercent());
                    System.out.println("UPLOAD WARTOSC: " + report.getTransferRateBit().doubleValue() + " Mbit/s" + " PROCENT: " + report.getProgressPercent() + " ---- " +percent);
                }
                 */
            }

            @Override
            public void onError(SpeedTestError speedTestError, String errorMessage) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("error " + speedTestError + " : " + errorMessage);
                }
            }
        });
        speedTestSocket.startFixedUpload(FileServer.SPEED_TEST_SERVER_URI_UPLOAD, FileServer.UPLOAD_FILE_SIZE, FileServer.MAX_TEST_DURATION, FileServer.SPEED_TEST_REPORT_INTERVAL);
    }

    // ---------------- GETTERS AND SETTERS ----------------
    public LinkedHashMap<Double, Float> getUploadChartMap() {
        return uploadChartMap;
    }

    public long getUploadStartTime() {
        return uploadStartTime;
    }

    public void setUploadStartTime(long uploadStartTime) {
        this.uploadStartTime = uploadStartTime;
    }

    public long getUploadStopTime() {
        return uploadStopTime;
    }

    public void setUploadStopTime(long uploadStopTime) {
        this.uploadStopTime = uploadStopTime;
    }

    public double getUploadAverage() {
        return uploadAverage;
    }

    public void setUploadAverage(double uploadAverage) {
        this.uploadAverage = uploadAverage;
    }
}
