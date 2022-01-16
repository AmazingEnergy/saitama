package com.amazingenergy.core.genericstatus;

import java.util.List;

/**
 * This is the interface for the full StatusGenericHandler
 */
public interface StatusGenericHandler<T> extends StatusGeneric<T> {

    StatusGenericHandler<T> setResult(T result);

    /**
     * This adds one error to the Errors collection
     * NOTE: This is virtual so that the StatusGenericHandler.Generic can override it. That allows both to return a IStatusGeneric result
     *
     * @param errorMessage  The text of the error message
     * @param propertyNames optional. A list of property names that this error applies to
     * @return
     */
    StatusGeneric<T> addError(String errorMessage, List<String> propertyNames);

    StatusGeneric<T> addError(String errorCode, String errorMessage, String... propertyNames);

    /**
     * This adds one error to the Errors collection and saves the exception's data to the DebugData property
     *
     * @param ex
     * @param errorMessage  The exception that you want to turn into a IStatusGeneric error.
     * @param propertyNames optional. A list of property names that this error applies to
     * @return
     */
    StatusGeneric<T> addError(Exception ex, String errorMessage, String... propertyNames);

    /**
     * This adds one ValidationResult to the Errors collection
     *
     * @param validationResult
     */
    void addValidationResult(ValidationResult validationResult);

    /**
     * This appends a collection of ValidationResults to the Errors collection
     *
     * @param validationResults
     */
    void addValidationResults(List<ValidationResult> validationResults);

}
