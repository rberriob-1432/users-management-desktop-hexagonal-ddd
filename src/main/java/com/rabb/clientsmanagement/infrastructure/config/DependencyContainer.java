package com.rabb.clientsmanagement.infrastructure.config;

import com.jcaa.shared.infrastructure.config.AppProperties;
import com.jcaa.shared.infrastructure.config.ValidatorProvider;
import com.jcaa.shared.infrastructure.persistence.DatabaseConfig;
import com.jcaa.shared.infrastructure.persistence.DatabaseConnectionFactory;
import com.jcaa.shared.infrastructure.email.SmtpConfig;
import com.rabb.clientsmanagement.application.port.in.*;
import com.rabb.clientsmanagement.application.service.*;
import com.rabb.clientsmanagement.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.repository.ClienteRepositoryPostgreSQL;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller.ClienteController;
import jakarta.validation.Validator;
import java.sql.Connection;

public final class DependencyContainer {

    private static final String DB_HOST = "db.host";
    private static final String DB_PORT = "db.port";
    private static final String DB_NAME = "db.name";
    private static final String DB_USER = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private static final String SMTP_HOST = "smtp.host";
    private static final String SMTP_PORT = "smtp.port";
    private static final String SMTP_USER = "smtp.username";
    private static final String SMTP_PASSWORD = "smtp.password";
    private static final String SMTP_FROM = "smtp.from.address";
    private static final String SMTP_FROM_NAME = "smtp.from.name";

    private final ClienteController clienteController;

    public DependencyContainer() {
        final AppProperties properties = new AppProperties();

        final Connection connection = buildDatabaseConnection(properties);
        final ClienteRepositoryPostgreSQL clienteRepository = new ClienteRepositoryPostgreSQL(connection);

        final JavaMailEmailSenderAdapter emailSender =
                new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));

        final com.rabb.clientsmanagement.application.service.EmailNotificationServiceCliente emailNotification =
                new com.rabb.clientsmanagement.application.service.EmailNotificationServiceCliente(emailSender);

        final Validator validator = ValidatorProvider.buildValidator();

        final CreateClienteUseCase createClienteUseCase =
                new CreateClienteService(clienteRepository, clienteRepository, emailNotification, validator);

        final UpdateClienteUseCase updateClienteUseCase =
                new UpdateClienteService(
                        clienteRepository,
                        clienteRepository,
                        clienteRepository,
                        emailNotification,
                        validator);

        final DeleteClienteUseCase deleteClienteUseCase =
                new DeleteClienteService(clienteRepository, clienteRepository, validator);

        final GetClienteByIdUseCase getClienteByIdUseCase =
                new GetClienteByIdService(clienteRepository, validator);

        final GetAllClientesUseCase getAllClientesUseCase =
                new GetAllClientesService(clienteRepository);

        final LoginUseCase loginUseCase =
                new LoginService(clienteRepository, validator);

        final GetAllCiudadesUseCase getAllCiudadesUseCase =
                new GetAllCiudadesService(clienteRepository);

        final GetClientesByCiudadUseCase getClientesByCiudadUseCase =
                new GetClientesByCiudadService(clienteRepository);

        final GetAllBarriosUseCase getAllBarriosUseCase =
                new GetAllBarriosService(clienteRepository);

        final GetClientesByBarrioUseCase getClientesByBarrioUseCase =
                new GetClientesByBarrioService(clienteRepository);
        final GetAllNamesUseCase getAllNamesUseCase =
                new GetAllNamesService(clienteRepository);

        final GetClientesByNameUseCase getClientesByNameUseCase =
                new GetClientesByNameService(clienteRepository);

        this.clienteController =
                new ClienteController(
                        createClienteUseCase,
                        updateClienteUseCase,
                        deleteClienteUseCase,
                        getClienteByIdUseCase,
                        getAllClientesUseCase,
                        loginUseCase,
                        getAllCiudadesUseCase,
                        getClientesByCiudadUseCase,
                        getAllBarriosUseCase,
                        getClientesByBarrioUseCase,
                        getAllNamesUseCase,
                        getClientesByNameUseCase
                );
    }

    public ClienteController clienteController() {
        return clienteController;
    }

    private static Connection buildDatabaseConnection(AppProperties properties) {

        final DatabaseConfig config =
                new DatabaseConfig(
                        properties.get(DB_HOST),
                        properties.getInt(DB_PORT),
                        properties.get(DB_NAME),
                        properties.get(DB_USER),
                        properties.get(DB_PASSWORD));

        return DatabaseConnectionFactory.createConnection(config);
    }

    private static SmtpConfig buildSmtpConfig(AppProperties properties) {

        return new SmtpConfig(
                properties.get(SMTP_HOST),
                properties.getInt(SMTP_PORT),
                properties.get(SMTP_USER),
                properties.get(SMTP_PASSWORD),
                properties.get(SMTP_FROM),
                properties.get(SMTP_FROM_NAME));
    }
}