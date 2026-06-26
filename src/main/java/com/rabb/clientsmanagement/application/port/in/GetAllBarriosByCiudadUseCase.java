package com.rabb.clientsmanagement.application.port.in;

import java.util.List;

public interface GetAllBarriosByCiudadUseCase {
    List<String> execute(String ciudad);
}