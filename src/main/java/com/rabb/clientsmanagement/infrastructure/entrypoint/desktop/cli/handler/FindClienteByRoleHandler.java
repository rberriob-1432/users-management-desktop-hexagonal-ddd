package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class FindClienteByRoleHandler implements OperationHandler {

    private final ClienteController clienteController;
    private final ConsoleIO console;
    private final ClienteResponsePrinter printer;

    @Override
    public void handle() {

        final List<String> roles = clienteController.getAllRoles();

        if (roles.isEmpty()) {
            console.println("  No roles found in the database.");
            return;
        }

        console.println();
        console.println("  ==========================================");
        console.println("    Available Roles");
        console.println("  ==========================================");

        for (int i = 0; i < roles.size(); i++) {
            console.printf("    [%d] %s%n", i + 1, roles.get(i));
        }

        console.println("  ==========================================");

        final int choice = console.readInt("\n  Select a role number: ");

        if (choice < 1 || choice > roles.size()) {
            console.println("  Invalid selection. Please try again.");
            return;
        }

        final String selectedRole = roles.get(choice - 1);

        console.printf("%n  Clients with role: %s%n", selectedRole);

        final List<ClienteResponse> clientes =
                clienteController.getClientesByRole(selectedRole);

        printer.printList(clientes);
    }
}