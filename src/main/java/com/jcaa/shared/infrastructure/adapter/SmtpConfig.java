package com.jcaa.shared.infrastructure.adapter;

public record SmtpConfig(
    String host, int port, String username, String password, String fromAddress, String fromName)
{

}
