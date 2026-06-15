package com.rabb.clientsmanagement.infrastructure.adapter.persistence.repository;

import com.rabb.clientsmanagement.application.port.out.*;
import com.rabb.clientsmanagement.domain.exception.ClienteNotFoundException;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.ClienteEmail;
import com.rabb.clientsmanagement.domain.valueobject.ClienteId;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.dto.ClientePersistenceDto;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.mapper.ClientePersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class ClienteRepositoryPostgreSQL
        implements SaveClientePort,
        UpdateClientePort,
        GetClienteByIdPort,
        GetClienteByEmailPort,
        GetAllClientesPort,
        DeleteClientePort {

  private static final String SQL_INSERT =
          "INSERT INTO clientes "
                  + "(id, name, email, password, role, status, barrio, calle, city, created_at, updated_at) "
                  + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
          "UPDATE clientes "
                  + "SET name = ?, email = ?, password = ?, role = ?, status = ?, barrio = ?, calle = ?, city = ?, updated_at = NOW() "
                  + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
          "SELECT id, name, email, password, role, status, barrio, calle, city, created_at, updated_at "
                  + "FROM clientes "
                  + "WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_EMAIL =
          "SELECT id, name, email, password, role, status, barrio, calle, city, created_at, updated_at "
                  + "FROM clientes "
                  + "WHERE email = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
          "SELECT id, name, email, password, role, status, barrio, calle, city, created_at, updated_at "
                  + "FROM clientes "
                  + "ORDER BY name ASC";

  private static final String SQL_DELETE =
          "DELETE FROM clientes "
                  + "WHERE id = ?";

  private final Connection connection;

  @Override
  public ClienteModel save(final ClienteModel user) {
    final ClientePersistenceDto dto = ClientePersistenceMapper.fromModelToDto(user);
    executeSave(dto);
    return findByIdOrFail(user.getId());
  }

  @Override
  public ClienteModel update(final ClienteModel user) {
    final ClientePersistenceDto dto = ClientePersistenceMapper.fromModelToDto(user);
    executeUpdate(dto);
    return findByIdOrFail(user.getId());
  }

  @Override
  public Optional<ClienteModel> getById(final ClienteId userId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setString(1, userId.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(ClientePersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByIdFailed(userId.value(), exception);
    }
  }

  @Override
  public Optional<ClienteModel> getByEmail(final ClienteEmail email) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
      statement.setString(1, email.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(ClientePersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByEmailFailed(email.value(), exception);
    }
  }

  @Override
  public List<ClienteModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return ClientePersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final ClienteId userId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, userId.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeleteFailed(userId.value(), exception);
    }
  }

  private void executeSave(final ClientePersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.id());
      statement.setString(2, dto.name());
      statement.setString(3, dto.email());
      statement.setString(4, dto.password());
      statement.setString(5, dto.role());
      statement.setString(6, dto.status());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseSaveFailed(dto.id(), exception);
    }
  }

  private void executeUpdate(final ClientePersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.name());
      statement.setString(2, dto.email());
      statement.setString(3, dto.password());
      statement.setString(4, dto.role());
      statement.setString(5, dto.status());
      statement.setString(6, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdateFailed(dto.id(), exception);
    }
  }

  private ClienteModel findByIdOrFail(final ClienteId userId) {
    return getById(userId)
            .orElseThrow(() -> ClienteNotFoundException.becauseIdWasNotFound(userId.value()));
  }
}
