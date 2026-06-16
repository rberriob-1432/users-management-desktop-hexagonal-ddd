package com.rabb.clientsmanagement;

import java.util.Scanner;

import com.rabb.clientsmanagement.infrastructure.config.DependencyContainer;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.ClienteManagementCli;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(final String[] args) {
    log.info("Starting Clientes Management System...");
    final DependencyContainer container = new DependencyContainer();
    try (final Scanner scanner = new Scanner(System.in)) {
      new ClienteManagementCli(container.clienteController(), new ConsoleIO(scanner, System.out)).start();//error
    }
  }
}