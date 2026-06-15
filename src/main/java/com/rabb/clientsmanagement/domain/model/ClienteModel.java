package com.rabb.clientsmanagement.domain.model;

import com.rabb.clientsmanagement.domain.enums.ClienteRole;
import com.rabb.clientsmanagement.domain.enums.ClienteStatus;
import com.rabb.clientsmanagement.domain.valueobject.*;
import lombok.Value;

@Value
public class ClienteModel {

  ClienteId id;
  ClienteName name;
  ClienteEmail email;
  ClientePassword password;
  ClienteRole role;
  ClienteStatus status;
  ClienteBarrio barrio;
  ClienteCalle calle;
  ClienteCity city;

  public static ClienteModel create(
          final ClienteId id,
          final ClienteName name,
          final ClienteEmail email,
          final ClientePassword password,
          final ClienteRole role,
          final ClienteBarrio barrio,
          final ClienteCalle calle,
          final ClienteCity city
  ) {
    return new ClienteModel(id, name, email, password, role, ClienteStatus.PENDING, barrio, calle, city);
  }

  public ClienteModel activate() {
    return new ClienteModel(id, name, email, password, role, ClienteStatus.ACTIVE, barrio, calle, city);
  }

  public ClienteModel deactivate() {
    return new ClienteModel(id, name, email, password, role, ClienteStatus.INACTIVE, barrio, calle, city);
  }
}