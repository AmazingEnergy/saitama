package com.amazingenergy.core.genericstatus;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StatusGenericHandlerImpl<T> implements StatusGenericHandler<T> {

    public static final String DEFAULT_STATUS_MESSAGE = "Success";

    protected final List<ErrorGeneric> _errors = new ArrayList<>();

    private String _successMessage = DEFAULT_STATUS_MESSAGE;

    /**
     * This creates a StatusGenericHandler, with optional header (see Header property, and CombineStatuses)
     *
     * @param header
     */
    public StatusGenericHandlerImpl(String header) {
        this.header = header;
    }

    /**
     * The header provides a prefix to any errors you add. Useful if you want to have a general prefix to all your errors
     * e.g. a header if "MyClass" would produce error messages such as "MyClass: This is my error message."
     */
    public String header;

    /**
     * This holds the list of ValidationResult errors. If the collection is empty, then there were no errors
     */
    @Override
    public ImmutableList<ErrorGeneric> getErrors() {return ImmutableList.copyOf(_errors);}

    /// <summary>
    /// </summary>
    /**
     * This is true if there are no errors
     */
    @Override
    public boolean isValid() { return _errors.isEmpty();}

    /**
     * This is true if any errors have been added
     */
    @Override
    public boolean hasErrors() { return !_errors.isEmpty();}

    T result;

    @Override
    public Optional<T> getResult() {
        return this.isValid() ? Optional.of(this.result) : Optional.empty();
    }

    @Override
    public StatusGenericHandler<T> setResult(T result) {
        this.result = result;
        return this;
    }

    /**
     * On success this returns the message as set by the business logic, or the default messages set by the BizRunner
     * If there are errors it contains the message "Failed with NN errors"
     *
     * @return
     */
    @Override
    public String getMessage() {
        return isValid() ? _successMessage : "Failed with {_errors.Count} error" + (_errors.size() == 1 ? "" : "s");
    }

    @Override
    public void setMessage(String message) {
        _successMessage = message;
    }

    /**
     * This allows statuses to be combined. Copies over any errors and replaces the Message if the currect message is null
     * If you are using Headers then it will combine the headers in any errors in combines
     * e.g. Status1 with header "MyClass" combines Status2 which has header "MyProp" and status2 has errors.
     * The result would be error message in status2 would be updates to start with "MyClass>MyProp: This is my error message."
     *
     * @param status
     * @return
     */
    @Override
    public StatusGeneric<T> combineStatuses(StatusGeneric<T> status) {
        if (!status.isValid()) {
            _errors.addAll(StringUtils.isEmpty(this.header)
                    ? status.getErrors()
                    : status.getErrors().stream().map(x -> new ErrorGeneric(this.header, x)).collect(Collectors.toList()));
        }
        if (this.isValid() && status.getMessage() != DEFAULT_STATUS_MESSAGE)
            this.setMessage(status.getMessage());

        return this;
    }

    /**
     * This is a simple method to output all the errors as a single string - null if no errors
     * Useful for feeding back all the errors in a single exception (also nice in unit testing)
     *
     * @param separator if null then each errors is separated by Environment.NewLine, otherwise uses the separator you provide
     * @return a single string with all errors separated by the 'separator' string
     */
    @Override
    public String getAllErrors(String separator) {
        if (StringUtils.isEmpty(separator))
            separator = System.lineSeparator();

        return !_errors.isEmpty() ? StringUtils.join(_errors, separator) : null;
    }

    /**
     * This adds one error to the Errors collection
     * NOTE: This is virtual so that the StatusGenericHandler.Generic can override it. That allows both to return a IStatusGeneric result
     *
     * @param errorMessage  The text of the error message
     * @param propertyNames optional. A list of property names that this error applies to
     * @return
     */
    @Override
    public StatusGeneric<T> addError(String errorMessage, List<String> propertyNames) {
        if (errorMessage == null) throw new IllegalArgumentException("errorMessage");
        _errors.add(new ErrorGeneric(this.header, new ValidationResult(errorMessage, propertyNames)));
        return this;
    }

    @Override
    public StatusGeneric<T> addError(String errorCode, String errorMessage, Object... parameters) {
        if (errorMessage == null) throw new IllegalArgumentException("errorMessage");
        _errors.add(new ErrorGeneric(this.header, new ValidationResult(errorCode, MessageFormat.format(errorMessage, parameters))));
        return this;
    }

    /**
     * This adds one error to the Errors collection and saves the exception's data to the DebugData property
     *
     * @param ex            The exception that you want to turn into a IStatusGeneric error.
     * @param errorMessage  The user-friendly text for the error message
     * @param propertyNames optional. A list of property names that this error applies to
     * @return
     */
    @Override
    public StatusGeneric<T> addError(Exception ex, String errorMessage, String... propertyNames) {
        if (errorMessage == null) throw new IllegalArgumentException("errorMessage");
        var errorGeneric = new ErrorGeneric(this.header, new ValidationResult(errorMessage, Arrays.asList(propertyNames)));
        errorGeneric.copyExceptionToDebugData(ex);
        _errors.add(errorGeneric);
        return this;
    }

    /**
     * This adds one ValidationResult to the Errors collection
     *
     * @param validationResult
     */
    @Override
    public void addValidationResult(ValidationResult validationResult) {
        _errors.add(new ErrorGeneric(this.header, validationResult));
    }

    /**
     * This appends a collection of ValidationResults to the Errors collection
     *
     * @param validationResults
     */
    @Override
    public void addValidationResults(List<ValidationResult> validationResults) {
        _errors.addAll(validationResults.stream().map(x -> new ErrorGeneric(this.header, x)).collect(Collectors.toList()));
    }
}
