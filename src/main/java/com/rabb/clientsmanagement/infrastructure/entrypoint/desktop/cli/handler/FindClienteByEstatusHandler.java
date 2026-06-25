package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class FindClienteByEstatusHandler implements OperationHandler {

    private final ClienteController clienteController;
    private final ConsoleIO console;
    private final ClienteResponsePrinter printer;

    @Override
    public void handle() {
        final List<String> status = clienteController.getAllEstatus();

        if (status.isEmpty()) {
            console.println("  No status found in the database.");
            return;
        }

        console.println();
        console.println("  ==========================================");
        console.println("    Available Status");
        console.println("  ==========================================");
        for (int i = 0; i < status.size(); i++) {
            console.printf("    [%d] %s%n", i + 1, status.get(i));
        }
        console.println("  ==========================================");

        final int choice = console.readInt("\n  Select a city number: ");

        if (choice < 1 || choice > status.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedEstatus = status.get(choice - 1);
        console.printf("%n  Clients in city: %s%n", selectedEstatus);

        final List<ClienteResponse> clientes = clienteController.getClientesByEstatus(selectedEstatus);
        printer.printList(clientes);
    }
}