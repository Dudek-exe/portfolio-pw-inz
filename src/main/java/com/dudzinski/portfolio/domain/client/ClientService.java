package com.dudzinski.portfolio.domain.client;

import com.dudzinski.portfolio.application.client.dto.ClientLoginRequestDTO;
import com.dudzinski.portfolio.domain.client.dto.LoginResponseDTO;
import com.dudzinski.portfolio.domain.client.dto.NewClientDTO;

public interface ClientService {

    LoginResponseDTO login(ClientLoginRequestDTO requestDTO);

    void createNewUser(NewClientDTO client, RoleType roleType);

    boolean isPresentByLogin(String login);
}
