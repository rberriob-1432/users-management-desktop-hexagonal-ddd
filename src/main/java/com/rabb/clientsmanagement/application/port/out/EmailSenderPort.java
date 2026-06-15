package com.rabb.clientsmanagement.application.port.out;

import com.rabb.clientsmanagement.domain.model.EmailDestinationModel;

public interface EmailSenderPort {
  void send(EmailDestinationModel destination);
}
