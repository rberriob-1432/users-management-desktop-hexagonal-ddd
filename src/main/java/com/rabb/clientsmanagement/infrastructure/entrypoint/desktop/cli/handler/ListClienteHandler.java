package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;


import java.util.List;

import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListClienteHandler implements OperationHandler {

  private final ClienteController userController;
  private final ClienteResponsePrinter printer;

  @Override
  public void handle() {
    final List<ClienteResponse> users = userController.listAllClientes();//que paso aqui?
    printer.printList(users);
  }
}