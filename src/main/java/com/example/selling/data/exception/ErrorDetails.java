package com.example.selling.data.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String mess;
    private String details;

    public ErrorDetails() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ErrorDetails(Date timestamp, String mess, String details) {
        this.timestamp = timestamp;
        this.mess = mess;
        this.details = details;
    }
}
