package com.rabb.clientsmanagement.application.service.mapper;

import com.rabb.clientsmanagement.application.service.dto.command.CreateClienteCommand;
import com.rabb.clientsmanagement.application.service.dto.command.DeleteClienteCommand;
import com.rabb.clientsmanagement.application.service.dto.command.UpdateClienteCommand;
import com.rabb.clientsmanagement.application.service.dto.query.GetClienteByIdQuery;
import com.rabb.clientsmanagement.domain.enums.ClienteRole;
import com.rabb.clientsmanagement.domain.enums.ClienteStatus;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClienteApplicationMapper {

    public ClienteModel fromCreateCommandToModel(final CreateClienteCommand command) {
        return ClienteModel.create(
                new ClienteId(command.id()),
                new ClienteName(command.name()),
                new ClienteEmail(command.email()),
                ClientePassword.fromPlainText(command.password()),
                ClienteRole.fromString(command.role()),
                ClienteStatus.fromString(command.status()),
                new ClienteCalle(command.calle()),
                new ClienteBarrio(command.barrio()),
                new com.rabb.clientsmanagement.domain.valueobject.ClienteCiudad(command.ciudad())
        );
    }

    public ClienteModel fromUpdateCommandToModel(final UpdateClienteCommand command, final String currentPassword) {
        final ClientePassword password = command.password() != null
                ? ClientePassword.fromPlainText(command.password())
                : ClientePassword.fromHash(currentPassword);

        return ClienteModel.create(
                new ClienteId(command.id()),
                new ClienteName(command.name()),
                new ClienteEmail(command.email()),
                password,
                ClienteRole.fromString(command.role()),
                ClienteStatus.fromString(command.status()),
                new ClienteCalle(command.calle()),
                new ClienteBarrio(command.barrio()),
                new com.rabb.clientsmanagement.domain.valueobject.ClienteCiudad(command.city())
        );
    }

    public ClienteId fromGetClienteByIdQueryToClienteId(final GetClienteByIdQuery query) {
        return new ClienteId(query.id());
    }

    public ClienteId fromDeleteCommandToClienteId(final DeleteClienteCommand command) {
        return new ClienteId(command.id());
    }
}