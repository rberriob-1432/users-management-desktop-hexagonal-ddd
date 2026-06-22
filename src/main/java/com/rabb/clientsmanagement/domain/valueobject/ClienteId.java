package com.rabb.clientsmanagement.domain.valueobject;
import com.rabb.clientsmanagement.domain.exception.InvalidClienteIdException;
import java.util.Objects;
import java.util.UUID;

public record ClienteId(String value) {

    public ClienteId {
        final String normalizedValue = Objects.requireNonNull(value, "ClienteId cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateFormat(normalizedValue);
        value = normalizedValue;
    }

    public static ClienteId nextId() {
        return new ClienteId(UUID.randomUUID().toString());
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidClienteIdException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(final String normalizedValue) {
        try {
            UUID.fromString(normalizedValue);
        } catch (final IllegalArgumentException e) {
            throw InvalidClienteIdException.becauseFormatIsInvalid(normalizedValue);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}