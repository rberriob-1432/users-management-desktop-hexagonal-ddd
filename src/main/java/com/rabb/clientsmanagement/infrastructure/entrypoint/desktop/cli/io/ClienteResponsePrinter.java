package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io;

import java.util.List;

import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public final class ClienteResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-10s : %s%n";

  private final ConsoleIO console;

  public void print(final ClienteResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",     response.id());
    console.printf(ROW_FORMAT, "Name",   response.name());
    console.printf(ROW_FORMAT, "Email",  response.email());
    console.printf(ROW_FORMAT, "Role",   response.role());
    console.printf(ROW_FORMAT, "Status", response.status());
    console.printf(ROW_FORMAT, "Barrio", response.barrio());
    console.printf(ROW_FORMAT, "Calle",  response.calle());
    console.printf(ROW_FORMAT, "City",   response.city());
    console.println(SEPARATOR);
  }

  public void printList(final List<ClienteResponse> clientes) {
    if (clientes.isEmpty()) {
      console.println("  No clientes found.");
      return;
    }
    console.printf("%n  Total: %d cliente(s)%n", clientes.size());
    clientes.forEach(this::print);
  }
}