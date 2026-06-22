package com.rabb.clientsmanagement.infrastructure.adapter.persistence.mapper;

import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.enums.ClienteRole;
import com.rabb.clientsmanagement.domain.enums.ClienteStatus;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.dto.ClientePersistenceDto;
import com.rabb.clientsmanagement.domain.valueobject.*;
import lombok.experimental.UtilityClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ClientePersistenceMapper {

    public ClientePersistenceDto fromModelToDto(final ClienteModel cliente) {
        return new ClientePersistenceDto(
                cliente.getId().value(),
                cliente.getName().value(),
                cliente.getEmail().value(),
                cliente.getPassword().value(),
                cliente.getRole().name(),
                cliente.getStatus().name(),
                cliente.getCalle().value(),
                cliente.getBarrio().value(),
                cliente.getCiudad().value()
        );
    }

    public ClienteModel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return ClienteModel.create(
                new ClienteId(rs.getString("id")),
                new ClienteName(rs.getString("name")),
                new ClienteEmail(rs.getString("email")),
                ClientePassword.fromHash(rs.getString("password")),
                ClienteRole.fromString(rs.getString("role")),
                ClienteStatus.fromString(rs.getString("status")),
                new ClienteCalle(rs.getString("calle")),
                new ClienteBarrio(rs.getString("barrio")),
                new ClienteCiudad(rs.getString("ciudad"))
        );
    }

    public List<ClienteModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<ClienteModel> clientes = new ArrayList<>();
        while (resultSet.next()) {
            clientes.add(fromResultSetToModel(resultSet));
        }
        return clientes;
    }
}