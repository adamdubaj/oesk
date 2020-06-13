package oesk.databases;

public class Table {
    private int id;
    private double uploadAverage;
    private double downloadAverage;

    @Override
    public String toString(){
        return "{" +
                "id=" + id +
                ", downloadAverage='" + downloadAverage + '\'' +
                ", uploadAverage='" + uploadAverage + '\'' +
                '}';
    }

    // ---------------- GETTERS AND SETTERS ----------------
    public void setId(int id) {
        this.id = id;
    }

    public void setUploadAverage(double uploadAverage) {
        this.uploadAverage = uploadAverage;
    }

    public void setDownloadAverage(double downloadAverage) {
        this.downloadAverage = downloadAverage;
    }

    public int getId() {
        return id;
    }

    public double getUploadAverage() {
        return uploadAverage;
    }

    public double getDownloadAverage() {
        return downloadAverage;
    }
}
