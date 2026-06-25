package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetAllEstatusUseCase;

import com.rabb.clientsmanagement.application.port.out.GetAllEstatusPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllEstatusService implements GetAllEstatusUseCase {
    private final GetAllEstatusPort getAlEstatusPort;

    @Override
    public List<String> execute() {
        return getAlEstatusPort.getAllEstatus();
    }
}