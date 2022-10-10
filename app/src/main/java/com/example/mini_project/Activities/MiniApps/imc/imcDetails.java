package com.example.mini_project.Activities.MiniApps.imc;

public class imcDetails {

    private String date;
    private String time;
    private String imcValue;
    private String imcStatus;

    public imcDetails(String date, String time, String imcValue, String imcStatus) {
        this.date = date;
        this.time = time;
        this.imcValue = imcValue;
        this.imcStatus = imcStatus;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getImcValue() {
        return imcValue;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setImcValue(String imcValue) {
        this.imcValue = imcValue;
    }

    public String getImcStatus() {
        return imcStatus;
    }

    public void setImcStatus(String imcStatus) {
        this.imcStatus = imcStatus;
    }
}
