package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class FindClienteByCiudadHandler implements OperationHandler {

    private final ClienteController clienteController;
    private final ConsoleIO console;
    private final ClienteResponsePrinter printer;

    @Override
    public void handle() {
        final List<String> ciudades = clienteController.getAllCiudades();

        if (ciudades.isEmpty()) {
            console.println("  No cities found in the database.");
            return;
        }

        console.println();
        console.println("  ==========================================");
        console.println("    Available Cities");
        console.println("  ==========================================");
        for (int i = 0; i < ciudades.size(); i++) {
            console.printf("    [%d] %s%n", i + 1, ciudades.get(i));
        }
        console.println("  ==========================================");

        final int choice = console.readInt("\n  Select a city number: ");

        if (choice < 1 || choice > ciudades.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedCiudad = ciudades.get(choice - 1);
        console.printf("%n  Clients in city: %s%n", selectedCiudad);

        final List<ClienteResponse> clientes = clienteController.getClientesByCiudad(selectedCiudad);
        printer.printList(clientes);
    }
}