package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.rabb.clientsmanagement.domain.exception.ClienteNotFoundException;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindClienteByIdHandler implements OperationHandler {

  private final ClienteController clienteController;
  private final ConsoleIO console;
  private final ClienteResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("Cliente ID: ");
    try {
      final ClienteResponse cliente = clienteController.findClienteById(id);
      printer.print(cliente);
    } catch (final ClienteNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}