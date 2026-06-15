package com.rabb.clientsmanagement.domain.enums;


import com.rabb.clientsmanagement.domain.exception.InvalidClienteRoleException;

public enum ClienteRole {
  ADMIN,
  MEMBER,
  REVIEWER;

  public static ClienteRole fromString(final String value) {
    for (final ClienteRole role : values()) {
      if (role.name().equalsIgnoreCase(value)) {
        return role;
      }
    }
    throw InvalidClienteRoleException.becauseValueIsInvalid(value);
  }
}
