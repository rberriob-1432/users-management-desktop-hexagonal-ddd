package com.rabb.clientsmanagement.application.service;
import com.rabb.clientsmanagement.application.port.in.GetAllRolesUseCase;
import com.rabb.clientsmanagement.application.port.out.GetAllRolesPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllRolesService implements GetAllRolesUseCase {
    private final GetAllRolesPort getAllRolesPort;

    @Override
    public List<String> execute() {
        return getAllRolesPort.getAllRoles();
    }
}