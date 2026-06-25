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

        final List<String> estatus = clienteController.getAllEstatus();

        if (estatus.isEmpty()) {
            console.println("  No status found in the database.");
            return;
        }

        console.println();
        console.println("  ==========================================");
        console.println("    Available Status");
        console.println("  ==========================================");

        for (int i = 0; i < estatus.size(); i++) {
            console.printf("    [%d] %s%n", i + 1, estatus.get(i));
        }

        console.println("  ==========================================");

        final int choice = console.readInt("\n  Select a status number: ");

        if (choice < 1 || choice > estatus.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedEstatus = estatus.get(choice - 1);

        console.printf("%n  Clients with status: %s%n", selectedEstatus);

        final List<ClienteResponse> clientes =
                clienteController.getClientesByEstatus(selectedEstatus);

        printer.printList(clientes);
    }
}