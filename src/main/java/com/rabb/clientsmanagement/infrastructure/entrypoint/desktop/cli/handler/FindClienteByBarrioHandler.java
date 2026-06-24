package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class FindClienteByBarrioHandler implements OperationHandler {

    private final ClienteController clienteController;
    private final ConsoleIO console;
    private final ClienteResponsePrinter printer;

    @Override
    public void handle() {
        final List<String> barrios = clienteController.getAllBarrios();

        if (barrios.isEmpty()) {
            console.println("  No neighborhoods found in the database.");
            return;
        }

        console.println();
        console.println("  ==========================================");
        console.println("    Available Neighborhoods");
        console.println("  ==========================================");
        for (int i = 0; i < barrios.size(); i++) {
            console.printf("    [%d] %s%n", i + 1, barrios.get(i));
        }
        console.println("  ==========================================");

        final int choice = console.readInt("\n  Select a neighborhood number: ");

        if (choice < 1 || choice > barrios.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedBarrio = barrios.get(choice - 1);
        console.printf("%n  Clients in neighborhood: %s%n", selectedBarrio);

        final List<ClienteResponse> clientes = clienteController.getClientesByBarrio(selectedBarrio);
        printer.printList(clientes);
    }
}