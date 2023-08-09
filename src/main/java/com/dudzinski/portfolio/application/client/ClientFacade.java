package com.dudzinski.portfolio.application.client;

import com.dudzinski.portfolio.application.client.dto.ClientLoginRequestDTO;
import com.dudzinski.portfolio.domain.client.ClientEntity;
import com.dudzinski.portfolio.domain.client.ClientService;
import com.dudzinski.portfolio.domain.client.RoleType;
import com.dudzinski.portfolio.domain.client.dto.LoginResponseDTO;
import com.dudzinski.portfolio.domain.client.dto.NewClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService clientService;

    public LoginResponseDTO login(ClientLoginRequestDTO loginRequestDTO) {
        return clientService.login(loginRequestDTO);
    }

    public void register(String login, String password) {
        clientService.register(login, password);
    }

    public Optional<ClientEntity> findByUsername(String username) {
        return clientService.findByUsername(username);
    }

    public void createNewUser(NewClientDTO client, RoleType roleType) {
        clientService.createNewUser(client, roleType);
    }

}
