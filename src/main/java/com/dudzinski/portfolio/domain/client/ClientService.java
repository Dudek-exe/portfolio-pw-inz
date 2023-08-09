package com.dudzinski.portfolio.domain.client;

import com.dudzinski.portfolio.application.client.dto.ClientLoginRequestDTO;
import com.dudzinski.portfolio.domain.client.dto.LoginResponseDTO;
import com.dudzinski.portfolio.domain.client.dto.NewClientDTO;

import java.util.Optional;

public interface ClientService {

    LoginResponseDTO login(ClientLoginRequestDTO requestDTO);

    void register(String login, String password);

    Optional<ClientEntity> findByUsername(String username);

    void createNewUser(NewClientDTO client, RoleType roleType);
}
