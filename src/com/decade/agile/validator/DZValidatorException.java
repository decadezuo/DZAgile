package com.decade.agile.validator;

public class DZValidatorException extends java.lang.Exception{
    private static final long serialVersionUID = 1L;

    public DZValidatorException() {
        super();
    }

    /**
     * @param detailMessage
     * @param throwable
     */
    public DZValidatorException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    /**
     * @param detailMessage
     */
    public DZValidatorException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * @param throwable
     */
    public DZValidatorException(Throwable throwable) {
        super(throwable);
    }
}
