package com.amazingenergy.core.genericstatus;

import com.google.common.collect.ImmutableList;

import java.util.Optional;

/**
 * This is the interface for creating and returning
 */
public interface StatusGeneric<T> {
    /**
     * This holds the list of errors. If the collection is empty, then there were no errors
     */
    ImmutableList<ErrorGeneric> getErrors();

    /**
     * This is true if there are no errors registered
     */
    boolean isValid();

    boolean hasErrors();

    /**
     * On success this returns any message set by GenericServices, or any method that returns a status
     * If there are errors it contains the message "Failed with NN errors"
     */
    String getMessage();

    void setMessage(String message);

    /**
     * This contains the return result, or if there are errors it will return default(T)
     *
     * @return
     */
    Optional<T> getResult();

    /**
     * This allows statuses to be combined
     *
     * @param status
     * @return
     */
    StatusGeneric<T> combineStatuses(StatusGeneric<T> status);

    /**
     * This is a simple method to output all the errors as a single string - null if no errors
     * Useful for feeding back all the errors in a single exception (also nice in unit testing)
     *
     * @param separator
     * @return
     */
    String getAllErrors(String separator);

}
