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
    ClienteCalle calle;
    ClienteBarrio barrio;
    ClienteCiudad ciudad;

    private ClienteModel(ClienteId id, ClienteName name, ClienteEmail email, ClientePassword password,
                         ClienteRole role, ClienteStatus status,  ClienteCalle calle,  ClienteBarrio barrio, ClienteCiudad ciudad) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.calle = calle;
        this.barrio = barrio;
        this.ciudad = ciudad;
    }

    public static ClienteModel create(
            final ClienteId id,
            final ClienteName name,
            final ClienteEmail email,
            final ClientePassword password,
            final ClienteRole role,
            final ClienteCalle calle,
            final ClienteBarrio barrio,
            final ClienteCiudad ciudad)
    {
        return new ClienteModel(id, name, email, password, role, ClienteStatus.PENDING, calle, barrio, ciudad);
    }

    public static ClienteModel create(
            final ClienteId id,
            final ClienteName name,
            final ClienteEmail email,
            final ClientePassword password,
            final ClienteRole role,
            final ClienteStatus status,
            final ClienteCalle calle,
            final ClienteBarrio barrio,
            final ClienteCiudad ciudad)
    {
        return new ClienteModel(id, name, email, password, role, status, calle, barrio, ciudad);
    }

    public ClienteModel activate() {
        return new ClienteModel(id, name, email, password, role, ClienteStatus.ACTIVE,calle, barrio, ciudad);
    }

    public ClienteModel deactivate() {
        return new ClienteModel(id, name, email, password, role, ClienteStatus.INACTIVE,calle, barrio, ciudad);
    }
}