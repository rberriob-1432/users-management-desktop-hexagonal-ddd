package com.rabb.clientsmanagement.domain.valueobject;

import com.rabb.clientsmanagement.domain.exception.InvalidClienteCiudadException;

import java.util.Objects;

public record ClienteCiudad(String value) {

    private static final int MINIMUM_LENGTH = 3;

    public ClienteCiudad(String value) {
        this.value = Objects.requireNonNull(value, "Cliente cannot be null").trim();
        validateNotEmpty(this.value);
        validateMinimumLength(this.value);
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidClienteCiudadException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidClienteCiudadException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}