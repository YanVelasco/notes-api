package com.yanvelasco.notes_api.exceptions;

import java.io.Serial;

public class IsEmptyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public IsEmptyException(String message) {
        super(message);
    }
}
