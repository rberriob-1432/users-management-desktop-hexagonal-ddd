package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;
import com.rabb.clientsmanagement.domain.exception.ClienteAlreadyExistsException;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.CreateClienteRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateClienteHandler implements OperationHandler {

  private final ClienteController clienteController;
  private final ConsoleIO console;
  private final ClienteResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("ID                              : ");
    final String name = console.readRequired("Name                            : ");
    final String email = console.readRequired("Email                           : ");
    final String password = console.readRequired("Password                        : ");
    final String role = console.readRequired("Role (ADMIN / MEMBER / REVIEWER): ");

    final String barrio = console.readRequired("Barrio                          : ");
    final String calle = console.readRequired("Calle                           : ");
    final String city = console.readRequired("City                            : ");

    try {
      final ClienteResponse created =
              clienteController.createCliente(new CreateClienteRequest(
                      id, name, email, password, role, barrio, calle, city));
      console.println("\n  Cliente created successfully.");
      printer.print(created);
    } catch (final ClienteAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}