package com.amazingenergy.core.command;

import com.amazingenergy.core.Notification;
import com.amazingenergy.core.genericstatus.StatusGenericHandlerImpl;
import org.apache.commons.lang3.StringUtils;

public abstract class BaseCommand<T> extends StatusGenericHandlerImpl<T> {
    /**
     * This creates a StatusGenericHandler, with optional header (see Header property, and CombineStatuses)
     *
     * @param header
     */
    public BaseCommand(String header) {
        super(header);
    }

    public BaseCommand() {
        super("");
    }

    protected void addError(Notification notification) {
        if (notification.hasErrorCode()) {
            if (!notification.getValidationResults().isEmpty()) {
                throw new IllegalStateException("Only support one error message per error code");
            }
            if (StringUtils.isEmpty(notification.getErrorMessage())) {
                throw new IllegalStateException("When set ErrorCode, ErrorMessage must have value");
            }

            addError(notification.getErrorCode(), notification.getErrorMessage());
            return;
        }

        notification.getValidationResults().forEach(validationResult -> addError(validationResult.getErrorMessage(), validationResult.getMemberNames()));
    }

}
