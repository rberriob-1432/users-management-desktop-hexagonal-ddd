package com.rabb.clientsmanagement.infrastructure.adapter.persistence.repository;

import com.rabb.clientsmanagement.application.port.out.*;
import com.rabb.clientsmanagement.domain.exception.ClienteNotFoundException;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.ClienteEmail;
import com.rabb.clientsmanagement.domain.valueobject.ClienteId;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.dto.ClientePersistenceDto;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.mapper.ClientePersistenceMapper;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class ClienteRepositoryPostgreSQL implements
        SaveClientePort,
        UpdateClientePort,
        GetClienteByIdPort,
        GetClienteByEmailPort,
        GetAllClientesPort,
        DeleteClientePort,
        GetAllCiudadesPort,
        GetClientesByCiudadPort,
        GetAllBarriosPort,
        GetClientesByBarrioPort,
        GetAllNamesPort,
        GetClientesByNamePort
{

    private static final String SQL_INSERT =
            "INSERT INTO clientes (id, name, email, password, role, status, calle, barrio, ciudad, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?::cliente_role, ?::cliente_status, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE =
            "UPDATE clientes SET name=?, email=?, password=?, role=?::cliente_role, status=?::cliente_status, calle=?, barrio=?, ciudad=?, updated_at=NOW() " +
                    "WHERE id=?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, email, password, role, status, created_at, updated_at, calle, barrio, ciudad " +
                    "FROM clientes WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_BY_EMAIL =
            "SELECT id, name, email, password, role, status, created_at, updated_at, calle, barrio, ciudad " +
                    "FROM clientes WHERE email = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, email, password, role, status, created_at, updated_at, calle, barrio, ciudad " +
                    "FROM clientes ORDER BY name ASC";

    private static final String SQL_DELETE =
            "DELETE FROM clientes WHERE id = ?";

    private static final String SQL_SELECT_DISTINCT_CIUDADES =
            "SELECT DISTINCT ciudad FROM clientes WHERE ciudad IS NOT NULL ORDER BY ciudad ASC";

    private static final String SQL_SELECT_BY_CIUDAD =
            "SELECT id, name, email, password, role, status, created_at, updated_at, calle, barrio, ciudad " +
                    "FROM clientes WHERE LOWER(ciudad) = LOWER(?) ORDER BY name ASC";
    private static final String SQL_SELECT_DISTINCT_BARRIOS =
            "SELECT DISTINCT barrio FROM clientes WHERE barrio IS NOT NULL ORDER BY barrio ASC";

    private static final String SQL_SELECT_BY_BARRIO =
            "SELECT id, name, email, password, role, status, created_at, updated_at, calle, barrio, ciudad " +
                    "FROM clientes WHERE LOWER(barrio) = LOWER(?) ORDER BY name ASC";
    private static final String SQL_SELECT_DISTINCT_NAMES =
        "SELECT DISTINCT name FROM clientes WHERE name IS NOT NULL ORDER BY name ASC";

    private static final String SQL_SELECT_BY_NAME =
            "SELECT id, name, email, password, role, status, created_at, updated_at, calle, barrio, ciudad " +
                    "FROM clientes WHERE LOWER(name) = LOWER(?) ORDER BY name ASC";
    private final Connection connection;

    @Override
    public ClienteModel save(ClienteModel cliente) {
        ClientePersistenceDto dto = ClientePersistenceMapper.fromModelToDto(cliente);
        executeSave(dto);
        return findByIdOrFail(cliente.getId());
    }

    @Override
    public ClienteModel update(ClienteModel cliente) {
        ClientePersistenceDto dto = ClientePersistenceMapper.fromModelToDto(cliente);
        executeUpdate(dto);
        return findByIdOrFail(cliente.getId());
    }

    @Override
    public Optional<ClienteModel> getById(ClienteId clienteId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, clienteId.value());
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(ClientePersistenceMapper.fromResultSetToModel(rs));
        } catch (SQLException e) {
            throw PersistenceException.becauseFindByIdFailed(clienteId.value(), e);
        }
    }

    @Override
    public Optional<ClienteModel> getByEmail(ClienteEmail email) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            statement.setString(1, email.value());
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(ClientePersistenceMapper.fromResultSetToModel(rs));
        } catch (SQLException e) {
            throw PersistenceException.becauseFindByEmailFailed(email.value(), e);
        }
    }

    @Override
    public List<ClienteModel> getAll() {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            return ClientePersistenceMapper.fromResultSetToModelList(rs);
        } catch (SQLException e) {
            throw PersistenceException.becauseFindAllFailed(e);
        }
    }

    @Override
    public void delete(ClienteId clienteId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, clienteId.value());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw PersistenceException.becauseDeleteFailed(clienteId.value(), e);
        }
    }

    @Override
    public List<String> getAllCiudades() {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_DISTINCT_CIUDADES)) {
            ResultSet rs = statement.executeQuery();
            List<String> ciudades = new ArrayList<>();
            while (rs.next()) {
                ciudades.add(rs.getString("ciudad"));
            }
            return ciudades;
        } catch (SQLException e) {
            throw PersistenceException.becauseFindAllCiudadesFailed(e);
        }
    }

    @Override
    public List<ClienteModel> getByCiudad(String ciudad) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CIUDAD)) {
            statement.setString(1, ciudad);
            ResultSet rs = statement.executeQuery();
            return ClientePersistenceMapper.fromResultSetToModelList(rs);
        } catch (SQLException e) {
            throw PersistenceException.becauseFindByCiudadFailed(ciudad, e);
        }
    }

    @Override
    public List<String> getAllBarrios() {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_DISTINCT_BARRIOS)) {
            ResultSet rs = statement.executeQuery();
            List<String> barrios = new ArrayList<>();
            while (rs.next()) {
                barrios.add(rs.getString("barrio"));
            }
            return barrios;
        } catch (SQLException e) {
            throw PersistenceException.becauseFindAllBarriosFailed(e);
        }
    }

    @Override
    public List<ClienteModel> getByBarrio(String barrio) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_BARRIO)) {
            statement.setString(1, barrio);
            ResultSet rs = statement.executeQuery();
            return ClientePersistenceMapper.fromResultSetToModelList(rs);
        } catch (SQLException e) {
            throw PersistenceException.becauseFindByBarrioFailed(barrio, e);
        }
    }
    @Override
    public List<String> getAllNames() {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_DISTINCT_NAMES)) {
            ResultSet rs = statement.executeQuery();
            List<String> names = new ArrayList<>();
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
            return names;
        } catch (SQLException e) {
            throw PersistenceException.becauseFindAllNamesFailed(e);
        }
    }

    @Override
    public List<ClienteModel> getByName(String name) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_NAME)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            return ClientePersistenceMapper.fromResultSetToModelList(rs);
        } catch (SQLException e) {
            throw PersistenceException.becauseFindByNameFailed(name, e);
        }
    }
    private void executeSave(ClientePersistenceDto dto) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.name());
            statement.setString(3, dto.email());
            statement.setString(4, dto.password());
            statement.setObject(5, dto.role(), Types.OTHER);
            statement.setObject(6, dto.status(), Types.OTHER);
            statement.setString(7, dto.calle());
            statement.setString(8, dto.barrio());
            statement.setString(9, dto.ciudad());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw PersistenceException.becauseSaveFailed(dto.id(), e);
        }
    }

    private void executeUpdate(ClientePersistenceDto dto) {
        try (PreparedStatement st = connection.prepareStatement(SQL_UPDATE)) {
            st.setString(1, dto.name());
            st.setString(2, dto.email());
            st.setString(3, dto.password());
            st.setObject(4, dto.role(), Types.OTHER);
            st.setObject(5, dto.status(), Types.OTHER);
            st.setString(6, dto.calle());
            st.setString(7, dto.barrio());
            st.setString(8, dto.ciudad());
            st.setString(9, dto.id());
            st.executeUpdate();
        } catch (SQLException e) {
            throw PersistenceException.becauseUpdateFailed(dto.id(), e);
        }
    }

    private ClienteModel findByIdOrFail(ClienteId id) {
        return getById(id).orElseThrow(() ->
                ClienteNotFoundException.becauseIdWasNotFound(id.value()));
    }
}