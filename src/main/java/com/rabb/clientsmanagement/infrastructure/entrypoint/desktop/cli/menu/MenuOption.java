package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum MenuOption {

    LIST_CLIENTE(1, "List all clients"),
    FIND_CLIENTE(2, "Find client by ID"),
    CREATE_CLIENTE(3, "Create client"),
    UPDATE_CLIENTE(4, "Update client"),
    DELETE_CLIENTE(5, "Delete client"),
    LOGIN(6, "Login"),
    FIND_BY_CIUDAD(7, "Find clients by city"),
    FIND_BY_BARRIO(8, "Find clients by neighborhood"),
    FIND_BY_NAME(9, "Find clients by name"),
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
