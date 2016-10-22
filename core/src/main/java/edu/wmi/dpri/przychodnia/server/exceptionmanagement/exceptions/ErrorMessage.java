package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;

public class ErrorMessage {

    private int status;
    private String message;
    private String value;
    private String valueType;

    public ErrorMessage() {
    }

    public ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorMessage(Exception ex) {
        this.status = 500;
        this.message = "An error has occurred: " + ex.getMessage();
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
