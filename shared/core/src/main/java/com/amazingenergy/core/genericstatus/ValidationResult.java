package com.amazingenergy.core.genericstatus;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResult {
    public List<String> memberNames;

    public String errorCode;

    public String errorMessage;

    public ValidationResult(String errorMessage) {
        this(errorMessage, null);
    }

    public ValidationResult(String errorMessage, List<String> memberNames) {
        this.errorMessage = errorMessage;
        this.memberNames = memberNames != null ? memberNames : new ArrayList<>();
    }

    public ValidationResult(String errorCode, String errorMessage, List<String> memberNames) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.memberNames = memberNames != null ? memberNames : new ArrayList<>();
    }

    protected ValidationResult(ValidationResult validationResult) {
        if (validationResult == null) {
            throw new IllegalArgumentException();
        }

        errorMessage = validationResult.errorMessage;
        memberNames = validationResult.memberNames;
    }

    @Override
    public String toString() {
        return !StringUtils.isEmpty(errorMessage) ? errorMessage : super.toString();
    }
}
