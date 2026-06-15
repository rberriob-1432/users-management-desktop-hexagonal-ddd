package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.out.EmailSenderPort;
import com.rabb.clientsmanagement.domain.exception.EmailSenderException;
import com.rabb.clientsmanagement.domain.model.EmailDestinationModel;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Level;

@Log
@RequiredArgsConstructor
public final class EmailNotificationService {

  private static final String SUBJECT_CREATED = "Tu cuenta ha sido creada — Gestión de Usuarios";
  private static final String SUBJECT_UPDATED =
      "Tu cuenta ha sido actualizada — Gestión de Usuarios";

  private static final String TOKEN_NAME     = "name";
  private static final String TOKEN_EMAIL    = "email";
  private static final String TOKEN_PASSWORD = "password";
  private static final String TOKEN_ROLE     = "role";
  private static final String TOKEN_STATUS   = "status";
  private final EmailSenderPort emailSenderPort;
  private static final String TOKEN_BARRIO   = "barrio";
  private static final String TOKEN_CALLE    = "calle";
  private static final String TOKEN_CITY     = "city";

  public void notifyClienteCreated(final ClienteModel cliente, final String plainPassword) {
    final String template = loadTemplate("cliente-created.html");
    final String body =
            renderTemplate(
                    template,
                    Map.of(
                            TOKEN_NAME,     cliente.getName().value(),
                            TOKEN_EMAIL,    cliente.getEmail().value(),
                            TOKEN_PASSWORD, plainPassword,
                            TOKEN_ROLE,     cliente.getRole().name(),
                            TOKEN_BARRIO,   cliente.getBarrio().value(),
                            TOKEN_CALLE,    cliente.getCalle().value(),
                            TOKEN_CITY,     cliente.getCity().value()));

    final EmailDestinationModel destination = buildDestination(cliente, SUBJECT_CREATED, body);
    sendOrLog(destination);
  }

  public void notifyClienteUpdated(final ClienteModel cliente) {
    final String template = loadTemplate("cliente-updated.html");
    final String body =
            renderTemplate(
                    template,
                    Map.of(
                            TOKEN_NAME,   cliente.getName().value(),
                            TOKEN_EMAIL,  cliente.getEmail().value(),
                            TOKEN_ROLE,   cliente.getRole().name(),
                            TOKEN_STATUS, cliente.getStatus().name(),
                            TOKEN_BARRIO, cliente.getBarrio().value(),
                            TOKEN_CALLE,  cliente.getCalle().value(),
                            TOKEN_CITY,   cliente.getCity().value()));

    final EmailDestinationModel destination = buildDestination(cliente, SUBJECT_UPDATED, body);
    sendOrLog(destination);
  }

  private static EmailDestinationModel buildDestination(
      final ClienteModel cliente, final String subject, final String body) {
    return new EmailDestinationModel(
        cliente.getEmail().value(), cliente.getName().value(), subject, body);
  }

  private String loadTemplate(final String templateName) {
    final String path = "/templates/" + templateName;
    try (InputStream inputStream = openResourceStream(path)) {
      if (Objects.isNull(inputStream)) {
        throw EmailSenderException.becauseSendFailed(
            new IllegalStateException("Template not found: " + path));
      }
      return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    } catch (final IOException ioException) {
      throw EmailSenderException.becauseSendFailed(ioException);
    }
  }

  InputStream openResourceStream(final String path) {
    return getClass().getResourceAsStream(path);
  }

  private String renderTemplate(String template, final Map<String, String> values) {
    String result = template;
    for (final Map.Entry<String, String> tokenEntry : values.entrySet()) {
      final String token = "{{" + tokenEntry.getKey() + "}}";
      result = result.replace(token, tokenEntry.getValue());
    }
    return result;
  }

  private void sendOrLog(final EmailDestinationModel destination) {
    try {
      emailSenderPort.send(destination);
    } catch (final EmailSenderException senderException) {
      log.log(
          Level.WARNING,
          "[EmailNotificationService] No se pudo enviar correo a: {0}. Causa: {1}",
          new Object[] {destination.getDestinationEmail(), senderException.getMessage()});
      throw senderException;
    }
  }
}
