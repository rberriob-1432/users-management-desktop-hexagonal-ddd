package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetAllBarriosUseCase;
import com.rabb.clientsmanagement.application.port.out.GetAllBarriosPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllBarriosService implements GetAllBarriosUseCase {
    private final GetAllBarriosPort getAllBarriosPort;

    @Override
    public List<String> execute() {
        return getAllBarriosPort.getAllBarrios();
    }
}