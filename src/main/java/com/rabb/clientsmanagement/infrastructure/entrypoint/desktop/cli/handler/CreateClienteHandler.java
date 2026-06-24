package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;
import com.rabb.clientsmanagement.domain.exception.ClienteAlreadyExistsException;
import com.rabb.clientsmanagement.domain.valueobject.ClienteId;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import java.util.UUID;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.CreateClienteRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateClienteHandler implements OperationHandler {

  private final ClienteController clienteController;
  private final ConsoleIO console;
  private final ClienteResponsePrinter printer;

  @Override
  public void handle() {
      final String id = readValidUuid();
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
                      id, name, email, role, "ACTIVE", calle, barrio, password, city));
      console.println("\n  Cliente created successfully.");
      printer.print(created);
    } catch (final ClienteAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
    private String readValidUuid() {
        final String suggested = ClienteId.nextId().value();
        console.println("  Suggested ID: " + suggested);
        console.println("  (Format: UUID, e.g. xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx)");

        while (true) {
            final String input = console.readRequired("ID                              : ");
            try {
                UUID.fromString(input);
                return input;
            } catch (final IllegalArgumentException e) {
                console.println("  Invalid UUID format. Please enter a valid UUID.");
                console.println("  Suggested ID: " + suggested);
            }
        }
    }
}