package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetAllNamesUseCase;
import com.rabb.clientsmanagement.application.port.out.GetAllNamesPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllNamesService implements GetAllNamesUseCase {
    private final GetAllNamesPort getAllNamesPort;

    @Override
    public List<String> execute() {
        return getAllNamesPort.getAllNames();
    }
}