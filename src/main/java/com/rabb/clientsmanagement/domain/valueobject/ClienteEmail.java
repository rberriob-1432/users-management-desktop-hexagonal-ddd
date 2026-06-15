package com.rabb.clientsmanagement.domain.valueobject;
import com.rabb.clientsmanagement.domain.exception.InvalidClienteEmailException;
import java.util.Objects;
import java.util.regex.Pattern;

public record ClienteEmail(String value) {

  private static final Pattern EMAIL_PATTERN =
      Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

  public ClienteEmail {
    final String normalizedValue =
        Objects.requireNonNull(value, "ClienteEmail cannot be null").trim().toLowerCase();
    validateNotEmpty(normalizedValue);
    validateFormat(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidClienteEmailException.becauseValueIsEmpty();
    }
  }

  private static void validateFormat(final String normalizedValue) {
    if (!EMAIL_PATTERN.matcher(normalizedValue).matches()) {
      throw InvalidClienteEmailException.becauseFormatIsInvalid(normalizedValue);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
