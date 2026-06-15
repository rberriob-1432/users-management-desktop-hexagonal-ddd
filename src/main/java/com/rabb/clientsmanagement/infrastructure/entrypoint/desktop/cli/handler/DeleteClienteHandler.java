package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.rabb.clientsmanagement.domain.exception.ClienteNotFoundException;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteClienteHandler implements OperationHandler {

  private final ClienteController userController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String id = console.readRequired("Cliente ID to delete: ");
    try {
      userController.deleteCliente(id);
      console.println("  Cliente deleted successfully.");
    } catch (final ClienteNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}