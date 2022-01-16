package com.amazingenergy.core.genericstatus;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * This hold an error registered in the {@link StatusGeneric} Errors collection
 */
@Data
public class ErrorGeneric {
    public static final String HeaderSeparator = ">";

    /**
     * A Header. Can be null
     */
    private String header;

    /**
     * This is the error provided
     */
    private ValidationResult errorResult;

    /**
     * This can be used to contain extra data to help the developer debug the error
     * For instance, the content of an exception.
     */
    private String debugData;

    public ErrorGeneric(String header, ValidationResult error) {
        if (header == null)
            throw new IllegalArgumentException("header");
        if (error == null)
            throw new IllegalArgumentException("error");
        this.header = header;
        this.errorResult = error;
    }

    public ErrorGeneric(String prefix, ErrorGeneric existingError) {
        this.header = StringUtils.isEmpty(prefix)
                ? existingError.header
                : StringUtils.isEmpty(existingError.header)
                ? prefix
                : prefix + HeaderSeparator + existingError.header;
        this.errorResult = existingError.errorResult;
        this.debugData = existingError.debugData;
    }

    /**
     * This copies the exception Message, StackTrace and any entries in the Data dictionary into the DebugData string
     *
     * @param ex
     */
    public void copyExceptionToDebugData(Exception ex) {
        var sb = new StringBuilder();
        sb.append(ex.getMessage())
                .append(System.lineSeparator())
                .append("StackTrace:")
                .append(System.lineSeparator())
                .append(Arrays.toString(ex.getStackTrace()))
                .append(System.lineSeparator());
        debugData = sb.toString();
    }

    /**
     * A human-readable error display
     *
     * @return
     */
    @Override
    public String toString() {
        var start = StringUtils.isEmpty(header) ? "" : header + ": ";
        return start + errorResult.toString();
    }
}
