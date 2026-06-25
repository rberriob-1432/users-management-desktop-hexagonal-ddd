package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.rabb.clientsmanagement.application.port.in.*;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.CreateClienteRequest;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.UpdateClienteRequest;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.mapper.ClienteDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ClienteController {

    private final CreateClienteUseCase createClienteUseCase;
    private final UpdateClienteUseCase updateClienteUseCase;
    private final DeleteClienteUseCase deleteClienteUseCase;
    private final GetClienteByIdUseCase getClienteByIdUseCase;
    private final GetAllClientesUseCase getAllClientesUseCase;
    private final LoginUseCase loginUseCase;
    private final GetAllCiudadesUseCase getAllCiudadesUseCase;
    private final GetClientesByCiudadUseCase getClientesByCiudadUseCase;
    private final GetAllBarriosUseCase getAllBarriosUseCase;
    private final GetClientesByBarrioUseCase getClientesByBarrioUseCase;
    private final GetAllNamesUseCase getAllNamesUseCase;
    private final GetClientesByNameUseCase getClientesByNameUseCase;
    public List<ClienteResponse> listAllClientes() {
        final var clientes = getAllClientesUseCase.execute();
        return ClienteDesktopMapper.toResponseList(clientes);
    }

    public ClienteResponse findClienteById(final String id) {
        final var query = ClienteDesktopMapper.toGetByIdQuery(id);
        final var cliente = getClienteByIdUseCase.execute(query);
        return ClienteDesktopMapper.toResponse(cliente);
    }

    public ClienteResponse createCliente(final CreateClienteRequest request) {
        final var command = ClienteDesktopMapper.toCreateCommand(request);
        final var cliente = createClienteUseCase.execute(command);
        return ClienteDesktopMapper.toResponse(cliente);
    }

    public ClienteResponse updateCliente(final UpdateClienteRequest request) {
        final var command = ClienteDesktopMapper.toUpdateCommand(request);
        final var cliente = updateClienteUseCase.execute(command);
        return ClienteDesktopMapper.toResponse(cliente);
    }

    public void deleteCliente(final String id) {
        final var command = ClienteDesktopMapper.toDeleteCommand(id);
        deleteClienteUseCase.execute(command);
    }

    public ClienteResponse login(final LoginRequest request) {
        final var command = ClienteDesktopMapper.toLoginCommand(request);
        final var cliente = loginUseCase.execute(command);
        return ClienteDesktopMapper.toResponse(cliente);
    }

    public List<String> getAllCiudades() {
        return getAllCiudadesUseCase.execute();
    }

    public List<ClienteResponse> getClientesByCiudad(final String ciudad) {
        final var clientes = getClientesByCiudadUseCase.execute(ciudad);
        return ClienteDesktopMapper.toResponseList(clientes);
    }
    public List<String> getAllBarrios() {
        return getAllBarriosUseCase.execute();
    }

    public List<ClienteResponse> getClientesByBarrio(final String barrio) {
        final var clientes = getClientesByBarrioUseCase.execute(barrio);
        return ClienteDesktopMapper.toResponseList(clientes);
    }
    public List<String> getAllNames() {
        return getAllNamesUseCase.execute();
    }

    public List<ClienteResponse> getClientesByName(final String name) {
        final var clientes = getClientesByNameUseCase.execute(name);
        return ClienteDesktopMapper.toResponseList(clientes);
    }
    }