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

        final List<String> barrios = clienteController.getAllBarriosByCiudad(selectedCiudad);

        if (barrios.isEmpty()) {
            console.println("  No neighborhood found in the selected city.");
            return;
        }

        console.println();
        console.println("  ==========================================");
        console.println("    Available neighborhoods in " + selectedCiudad);
        console.println("  ==========================================");
        for (int i = 0; i < barrios.size(); i++) {
            console.printf("    [%d] %s%n", i + 1, barrios.get(i));
        }
        console.println("  ==========================================");

        final int choiceBarrio = console.readInt("\n  Select a neighborhood number: ");

        if (choiceBarrio < 1 || choiceBarrio > barrios.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedBarrio = barrios.get(choiceBarrio - 1);
        console.printf("%n  Clients in neighborhood: %s, %s%n", selectedBarrio, selectedCiudad);

        final List<ClienteResponse> clientes = clienteController.getClientesByBarrioAndCiudad(selectedBarrio, selectedCiudad);
        printer.printList(clientes);
    }
}