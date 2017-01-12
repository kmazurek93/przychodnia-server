package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;


public final class ErrorMessageBuilder {
    private int status;
    private String message;
    private String value;
    private String valueType;

    private ErrorMessageBuilder() {
    }

    public static ErrorMessageBuilder anErrorMessage() {
        return new ErrorMessageBuilder();
    }

    public ErrorMessageBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public ErrorMessageBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorMessageBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public ErrorMessageBuilder withValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public ErrorMessage build() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(status);
        errorMessage.setMessage(message);
        errorMessage.setValue(value);
        errorMessage.setValueType(valueType);
        return errorMessage;
    }
}
