package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.handler;


import com.rabb.clientsmanagement.domain.exception.InvalidCredentialsException;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ClienteResponsePrinter;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class LoginHandler implements OperationHandler {

  private final ClienteController userController;
  private final ConsoleIO console;
  private final ClienteResponsePrinter printer;

  @Override
  public void handle() {
    final String email    = console.readRequired("Email   : ");
    final String password = console.readRequired("Password: ");
    try {
      final ClienteResponse user = userController.login(new LoginRequest(email, password));
      console.println("\n  Login successful. Welcome!");
      printer.print(user);
    } catch (final InvalidCredentialsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}