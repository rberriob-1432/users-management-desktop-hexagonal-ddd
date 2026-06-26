package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class FindClienteByNameHandler implements OperationHandler {

    private final ClienteController clienteController;
    private final ConsoleIO console;
    private final ClienteResponsePrinter printer;

    @Override
    public void handle() {
        final List<String> names = clienteController.getAllNames();

        if (names.isEmpty()) {
            console.println("  No cities found in the database.");
            return;
        }

        console.println();
        console.println("  ==========================================");
        console.println("    Available Names");
        console.println("  ==========================================");
        for (int i = 0; i < names.size(); i++) {
            console.printf("    [%d] %s%n", i + 1, names.get(i));
        }
        console.println("  ==========================================");

        final int choice = console.readInt("\n  Select a name number: ");

        if (choice < 1 || choice > names.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedName = names.get(choice - 1);
        console.printf("%n  Clients in name: %s%n", selectedName);

        final List<ClienteResponse> clientes = clienteController.getClientesByName(selectedName);
        printer.printList(clientes);
    }
}