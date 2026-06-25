package com.rabb.clientsmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

    private static final String MESSAGE_SAVE = "Failed to save cliente with ID: '%s'.";
    private static final String MESSAGE_UPDATE = "Failed to update cliente with ID: '%s'.";
    private static final String MESSAGE_FIND = "Failed to find cliente with ID: '%s'.";
    private static final String MESSAGE_EMAIL = "Failed to find cliente with email: '%s'.";
    private static final String MESSAGE_ALL = "Failed to retrieve all clientes.";
    private static final String MESSAGE_DELETE = "Failed to delete cliente with ID: '%s'.";
    private static final String MESSAGE_CONNECTION = "Could not establish database connection.";
    private static final String MESSAGE_ALL_CIUDADES = "Failed to retrieve all ciudades.";
    private static final String MESSAGE_BY_CIUDAD = "Failed to retrieve clientes for ciudad: '%s'.";
    private static final String MESSAGE_BY_BARRIO = "Failed to retrieve clientes for barrio: '%s'.";
    private static final String MESSAGE_ALL_BARRIOS = "Failed to retrieve all barrios.";
    private static final String MESSAGE_ALL_NAMES = "Failed to retrieve all names.";
    private static final String MESSAGE_BY_NAME = "Failed to retrieve clientes for name: '%s'.";
    private static final String MESSAGE_ALL_ESTATUS = "Failed to retrieve all estatus.";
    private static final String MESSAGE_BY_ESTATUS = "Failed to retrieve clientes for estatus: '%s'.";
    private PersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static PersistenceException becauseSaveFailed(final String clienteId, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_SAVE, clienteId), cause);
    }

    public static PersistenceException becauseUpdateFailed(
            final String clienteId, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_UPDATE, clienteId), cause);
    }

    public static PersistenceException becauseFindByIdFailed(
            final String clienteId, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_FIND, clienteId), cause);
    }

    public static PersistenceException becauseFindByEmailFailed(
            final String email, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_EMAIL, email), cause);
    }

    public static PersistenceException becauseFindAllFailed(final Throwable cause) {
        return new PersistenceException(MESSAGE_ALL, cause);
    }

    public static PersistenceException becauseDeleteFailed(
            final String clienteId, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_DELETE, clienteId), cause);
    }

    public static PersistenceException becauseConnectionFailed(final Throwable cause) {
        return new PersistenceException(MESSAGE_CONNECTION, cause);
    }

    public static PersistenceException becauseFindAllCiudadesFailed(final Throwable cause) {
        return new PersistenceException(MESSAGE_ALL_CIUDADES, cause);
    }

    public static PersistenceException becauseFindByCiudadFailed(
            final String ciudad, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_BY_CIUDAD, ciudad), cause);
    }

    public static PersistenceException becauseFindByBarrioFailed(
            final String barrio, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_BY_BARRIO, barrio), cause);
    }

    public static PersistenceException becauseFindAllBarriosFailed(final Throwable cause) {
        return new PersistenceException(MESSAGE_ALL_BARRIOS, cause);
    }
    public static PersistenceException becauseFindAllNamesFailed(final Throwable cause) {
        return new PersistenceException(MESSAGE_ALL_NAMES, cause);
    }

    public static PersistenceException becauseFindByNameFailed(
            final String name, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_BY_NAME, name), cause);
    }
    public static PersistenceException becauseFindAllEstatusFailed(final Throwable cause) {
        return new PersistenceException(MESSAGE_ALL_ESTATUS, cause);
    }

    public static PersistenceException becauseFindByEstatusFailed(
            final String estatus, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_BY_ESTATUS, estatus), cause);
    }
}