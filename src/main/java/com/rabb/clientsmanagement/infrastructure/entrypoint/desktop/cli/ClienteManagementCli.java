package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli;

import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler.*;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public final class ClienteManagementCli {

    private static final String BANNER =
            """
            ==========================================
                 Clientes Management System
            ==========================================""";

    private static final String MENU_BORDER = "  ==========================================";

    private final ClienteController clienteController;
    private final ConsoleIO console;

    public void start() {
        console.println(BANNER);
        final ClienteResponsePrinter printer = new ClienteResponsePrinter(console);
        runLoop(buildHandlers(printer));
    }

    private void runLoop(final Map<MenuOption, OperationHandler> handlers) {
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n  Option: ");
            final Optional<MenuOption> option = MenuOption.fromNumber(choice);

            if (option.isEmpty()) {
                console.println("  Invalid option. Please try again.");
            } else if (option.get() == MenuOption.EXIT) {
                console.println("\n  Goodbye!\n");
                running = false;
            } else {
                executeHandler(handlers, option.get());
            }
        }
    }

    private void executeHandler(
            final Map<MenuOption, OperationHandler> handlers, final MenuOption option) {
        try {
            handlers.get(option).handle();
        } catch (final ConstraintViolationException exception) {
            console.println("  Validation errors:");
            exception.getConstraintViolations()
                    .forEach(violation -> console.println("    - " + violation.getMessage()));
        } catch (final RuntimeException exception) {
            console.println("  Unexpected error: " + exception.getMessage());
        }
    }

    private Map<MenuOption, OperationHandler> buildHandlers(final ClienteResponsePrinter printer) {
        final Map<MenuOption, OperationHandler> handlers = new java.util.HashMap<>();
        handlers.put(MenuOption.LIST_CLIENTE,    new ListClienteHandler(clienteController, printer));
        handlers.put(MenuOption.FIND_CLIENTE,    new FindClienteByIdHandler(clienteController, console, printer));
        handlers.put(MenuOption.CREATE_CLIENTE,  new CreateClienteHandler(clienteController, console, printer));
        handlers.put(MenuOption.UPDATE_CLIENTE,  new UpdateClienteHandler(clienteController, console, printer));
        handlers.put(MenuOption.DELETE_CLIENTE,  new DeleteClienteHandler(clienteController, console));
        handlers.put(MenuOption.LOGIN,           new LoginHandler(clienteController, console, printer));
        handlers.put(MenuOption.FIND_BY_CIUDAD,  new FindClienteByCiudadHandler(clienteController, console, printer));
        handlers.put(MenuOption.FIND_BY_BARRIO,  new FindClienteByBarrioHandler(clienteController, console, printer));
        handlers.put(MenuOption.FIND_BY_NAME,   new FindClienteByNameHandler(clienteController, console, printer));
        handlers.put(MenuOption.FIND_BY_CALLE,   new FindClienteByCalleHandler(clienteController, console, printer));
        handlers.put(MenuOption.FIND_BY_ESTATUS,  new FindClienteByEstatusHandler(clienteController, console, printer));
        handlers.put(MenuOption.FIND_BY_ROLE,   new FindClienteByRoleHandler(clienteController, console, printer));
        return handlers;
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Main Menu");
        console.println(MENU_BORDER);
        for (final MenuOption option : MenuOption.values()) {
            console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
        }
        console.println(MENU_BORDER);
    }
}