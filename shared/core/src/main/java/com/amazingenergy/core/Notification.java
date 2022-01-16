package com.amazingenergy.core;

import com.amazingenergy.core.genericstatus.ValidationResult;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Notification {
    private List<ValidationResult> validationResults = new ArrayList<>();
    private String errorCode;
    private String errorMessage;

    public boolean hasErrorCode() {
        return !StringUtils.isEmpty(errorCode);
    }

    public static Notification instance() {
        return new Notification();
    }

    public Notification addError(String fieldName, String errorMessage) {
        if (validationResults == null)
            validationResults = new ArrayList<>();

        validationResults.add(new ValidationResult(errorMessage, new ArrayList<>() {{
            add(fieldName);
        }}));
        return this;
    }

    public Notification addErrors(List<ValidationResult> errors) {
        if (validationResults == null)
            validationResults = new ArrayList<>();
        validationResults.addAll(errors);
        return this;
    }

    public Notification addErrorCode(String errorCode, String messageTemplate, Object... parameters) {
        if (!StringUtils.isEmpty(this.errorCode) && !StringUtils.equalsIgnoreCase(this.errorCode, errorCode)) {
            throw new IllegalStateException("ErrorCode" + errorCode + " has been set. Only one errorCode support");
        }
        this.errorCode = errorCode;
        this.errorMessage = MessageFormat.format(messageTemplate, parameters);
        return this;
    }

    public Notification addErrorCode(String errorCode, String message) {
        if (!StringUtils.isEmpty(this.errorCode) && !StringUtils.equalsIgnoreCase(this.errorCode, errorCode)) {
            throw new IllegalStateException("ErrorCode" + errorCode + " has been set. Only one errorCode support");
        }
        this.errorCode = errorCode;
        this.errorMessage = message;
        return this;
    }

    public boolean hasError() {
        return (this.validationResults != null && this.validationResults.size() > 0)
                || !StringUtils.isEmpty(this.errorCode);
    }

    public Notification join(Notification other) {
        if (!StringUtils.isEmpty(this.errorCode) && !StringUtils.equalsIgnoreCase(this.errorCode, errorCode)) {
            throw new IllegalStateException("ErrorCode" + errorCode + " has been set. Only one errorCode support");
        }

        if (validationResults == null)
            validationResults = new ArrayList<>();

        this.errorCode = other.getErrorCode();
        this.errorMessage = other.getErrorMessage();
        if (!other.getValidationResults().isEmpty()) {
            validationResults.addAll(other.getValidationResults());
        }

        return this;
    }

}
