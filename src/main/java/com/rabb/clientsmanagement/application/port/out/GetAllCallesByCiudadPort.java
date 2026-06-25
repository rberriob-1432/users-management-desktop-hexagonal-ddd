package com.rabb.clientsmanagement.application.port.out;

import java.util.List;

public interface GetAllCallesByCiudadPort {
    List<String> getAllCallesByCiudad(String ciudad);
}