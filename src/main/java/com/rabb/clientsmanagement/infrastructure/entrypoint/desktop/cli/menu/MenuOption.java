package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.menu;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuOption {

  LIST_CLIENTES(1, "List all clientes"),
  FIND_CLIENTE(2, "Find cliente by ID"),
  CREATE_CLIENTE(3, "Create cliente"),
  UPDATE_CLIENTE(4, "Update cliente"),
  DELETE_CLIENTE(5, "Delete cliente"),
  LOGIN(6, "Login"),
  EXIT(0, "Exit");

  private final int number;
  private final String description;

  public static Optional<MenuOption> fromNumber(final int number) {
    for (final MenuOption option : values()) {
      if (option.number == number) {
        return Optional.of(option);
      }
    }
    return Optional.empty();
  }
}

