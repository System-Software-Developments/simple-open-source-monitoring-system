package com.osms.monitoring.common;

public class OsmsException extends RuntimeException {

    public OsmsException(Throwable e) {
        super(e);
    }

    public OsmsException(String message) {
        super(new Throwable(message));
    }

}
