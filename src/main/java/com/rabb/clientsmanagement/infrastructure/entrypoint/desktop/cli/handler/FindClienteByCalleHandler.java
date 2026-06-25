package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class FindClienteByCalleHandler implements OperationHandler {

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

        final int choiceCiudad = console.readInt("\n  Select a city number: ");

        if (choiceCiudad < 1 || choiceCiudad > ciudades.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedCiudad = ciudades.get(choiceCiudad - 1);

        final List<String> calles = clienteController.getAllCallesByCiudad(selectedCiudad);

        if (calles.isEmpty()) {
            console.println("  No streets found in the selected city.");
            return;
        }

        console.println();
        console.println("  ==========================================");
        console.println("    Available Streets in " + selectedCiudad);
        console.println("  ==========================================");
        for (int i = 0; i < calles.size(); i++) {
            console.printf("    [%d] %s%n", i + 1, calles.get(i));
        }
        console.println("  ==========================================");

        final int choiceCalle = console.readInt("\n  Select a street number: ");

        if (choiceCalle < 1 || choiceCalle > calles.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedCalle = calles.get(choiceCalle - 1);
        console.printf("%n  Clients in street: %s, %s%n", selectedCalle, selectedCiudad);

        final List<ClienteResponse> clientes = clienteController.getClientesByCalleAndCiudad(selectedCalle, selectedCiudad);
        printer.printList(clientes);
    }
}