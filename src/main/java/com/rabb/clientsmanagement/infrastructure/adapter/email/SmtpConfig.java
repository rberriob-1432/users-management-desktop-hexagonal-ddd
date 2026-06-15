package com.rabb.clientsmanagement.infrastructure.adapter.email;

public record SmtpConfig(
    String host, int port, String clientename, String password, String fromAddress, String fromName)
{

}
