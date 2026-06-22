package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetAllCiudadesUseCase;
import com.rabb.clientsmanagement.application.port.out.GetAllCiudadesPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllCiudadesService implements GetAllCiudadesUseCase {
    private final GetAllCiudadesPort getAllCiudadesPort;

    @Override
    public List<String> execute() {
        return getAllCiudadesPort.getAllCiudades();
    }
}