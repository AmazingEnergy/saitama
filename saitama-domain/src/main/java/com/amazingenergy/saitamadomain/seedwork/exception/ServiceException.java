package com.amazingenergy.saitamadomain.seedwork.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends Exception {
    private int exceptionType = 0;//regular error

    public final static int EXCEPTION_VALIDATION = 99;
    public final static int EXCEPTION_PAYMENT_DECLINED = 100;
    public final static int EXCEPTION_TRANSACTION_DECLINED = 101;
    public final static int EXCEPTION_INVENTORY_MISMATCH = 120;

    private String messageCode = null;

    public ServiceException() {
        super();
    }

    public ServiceException(String messageCode) {
        super();
        this.messageCode = messageCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public ServiceException(int exceptionType) {
        super();
        this.exceptionType = exceptionType;
    }

    public ServiceException(int exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public ServiceException(int exceptionType, String message, String messageCode) {
        super(message);
        this.messageCode = messageCode;
        this.exceptionType = exceptionType;
    }
}
