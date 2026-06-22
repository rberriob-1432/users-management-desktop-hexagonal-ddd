package com.rabb.clientsmanagement.domain.exception;
import com.rabb.clientsmanagement.domain.exception.DomainException;
public final class InvalidClienteIdException extends DomainException {

    private static final String MESSAGE_EMPTY = "The cliente id must not be empty.";

    private InvalidClienteIdException(final String message) {
        super(message);
    }

    public static InvalidClienteIdException becauseValueIsEmpty() {
        return new InvalidClienteIdException(MESSAGE_EMPTY);
    }

    public static InvalidClienteIdException becauseFormatIsInvalid(final String value) {
        return new InvalidClienteIdException(String.format("The cliente id '%s' has an invalid format. It must be a valid UUID.", value));
    }
}