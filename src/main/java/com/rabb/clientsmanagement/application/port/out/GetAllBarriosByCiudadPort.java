package com.rabb.clientsmanagement.application.port.out;

import java.util.List;

public interface GetAllBarriosByCiudadPort {
    List<String> getAllBarriosByCiudad(String ciudad);
}