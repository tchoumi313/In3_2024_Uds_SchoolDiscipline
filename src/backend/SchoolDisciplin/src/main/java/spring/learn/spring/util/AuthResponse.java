package spring.learn.spring.util;

import java.util.Date;

public class AuthResponse {
    private Date timestamp;
    private int status;
    private String error;
    private String path;

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public AuthResponse() {
    }

    public AuthResponse(Date timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

}
