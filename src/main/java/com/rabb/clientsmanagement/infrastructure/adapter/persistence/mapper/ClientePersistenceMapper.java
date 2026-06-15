package com.rabb.clientsmanagement.infrastructure.adapter.persistence.mapper;


import com.rabb.clientsmanagement.domain.enums.ClienteRole;
import com.rabb.clientsmanagement.domain.enums.ClienteStatus;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.*;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.dto.ClientePersistenceDto;
import com.rabb.clientsmanagement.infrastructure.adapter.persistence.entity.ClienteEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ClientePersistenceMapper {

  public ClientePersistenceDto fromModelToDto(final ClienteModel user) {
    return new ClientePersistenceDto(
            user.getId().value(),
            user.getName().value(),
            user.getEmail().value(),
            user.getPassword().value(),
            user.getRole().name(),
            user.getStatus().name(),
            user.getBarrio().value(),
            user.getCalle().value(),
            user.getCity().value(),
            null,
            null
    );
  }

  public ClienteEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new ClienteEntity(
            resultSet.getString("id"),
            resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getString("role"),
            resultSet.getString("status"),
            resultSet.getString("barrio"), // Asegúrate de que existan en la DB
            resultSet.getString("calle"),
            resultSet.getString("city"),
            resultSet.getString("created_at"),
            resultSet.getString("updated_at"));
  }

  public ClienteModel fromEntityToModel(final ClienteEntity entity) {
    return new ClienteModel(
            new ClienteId(entity.id()),
            new ClienteName(entity.name()),
            new ClienteEmail(entity.email()),
            ClientePassword.fromHash(entity.password()),
            ClienteRole.fromString(entity.role()),
            ClienteStatus.fromString(entity.status()),
            new ClienteBarrio(entity.barrio()),
            new ClienteCalle(entity.calle()),
            new ClienteCity(entity.city())
    );
  }

  public ClienteModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<ClienteModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<ClienteModel> users = new ArrayList<>();
    while (resultSet.next()) {
      users.add(fromResultSetToModel(resultSet));
    }
    return users;
  }
}