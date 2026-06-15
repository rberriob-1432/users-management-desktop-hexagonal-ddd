package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;
import com.rabb.clientsmanagement.domain.exception.ClienteNotFoundException;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.UpdateClienteRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateClienteHandler implements OperationHandler {

  private final ClienteController clienteController;
  private final ConsoleIO console;
  private final ClienteResponsePrinter printer;

  @Override
  public void handle() {
    final String id       = console.readRequired("Cliente ID                                       : ");
    final String name     = console.readRequired("New name                                      : ");
    final String email    = console.readRequired("New email                                     : ");
    final String password = console.readOptional("New password (leave blank to keep current)    : ");
    final String role     = console.readRequired("Role   (ADMIN / MEMBER / REVIEWER)            : ");
    final String status   = console.readRequired("Status (ACTIVE / INACTIVE / PENDING / BLOCKED): ");

    // NUEVOS CAMPOS REQUERIDOS
    final String barrio   = console.readRequired("New Barrio                                    : ");
    final String calle    = console.readRequired("New Calle                                     : ");
    final String city     = console.readRequired("New City                                      : ");

    try {
      final ClienteResponse updated = clienteController.updateCliente(
              new UpdateClienteRequest(
                      id,
                      name,
                      email,
                      password.isBlank() ? null : password,
                      role,
                      status,
                      barrio,
                      calle,
                      city
              ));
      console.println("\n  Cliente updated successfully.");
      printer.print(updated);
    } catch (final ClienteNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}