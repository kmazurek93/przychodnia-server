package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;

/**
 * Created by lupus on 11.09.16.
 */
public final class ErrorMessageBuilder {

    int status;

    int code;

    String message;

    String link;

    String developerMessage;

    private ErrorMessageBuilder() {
    }

    public static ErrorMessageBuilder anErrorMessage() {
        return new ErrorMessageBuilder();
    }

    public ErrorMessageBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public ErrorMessageBuilder withCode(int code) {
        this.code = code;
        return this;
    }

    public ErrorMessageBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorMessageBuilder withLink(String link) {
        this.link = link;
        return this;
    }

    public ErrorMessageBuilder withDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
        return this;
    }

    public ErrorMessage build() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(status);
        errorMessage.setCode(code);
        errorMessage.setMessage(message);
        errorMessage.setLink(link);
        errorMessage.setDeveloperMessage(developerMessage);
        return errorMessage;
    }
}
