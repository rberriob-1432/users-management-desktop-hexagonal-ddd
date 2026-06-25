package com.rabb.clientsmanagement.application.port.in;
import java.util.List;

public interface GetAllRolesUseCase {
    List<String> execute();
}