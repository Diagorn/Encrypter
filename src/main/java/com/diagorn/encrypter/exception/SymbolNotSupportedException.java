package com.diagorn.encrypter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if a desired encrypted symbol is not supported
 *
 * @author diagorn
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SymbolNotSupportedException extends RuntimeException {
    public SymbolNotSupportedException() {
        super();
    }

    public SymbolNotSupportedException(String message) {
        super(message);
    }

    public SymbolNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SymbolNotSupportedException(Throwable cause) {
        super(cause);
    }

    protected SymbolNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
