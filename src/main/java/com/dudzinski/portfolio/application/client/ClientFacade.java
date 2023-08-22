package com.dudzinski.portfolio.application.client;

import com.dudzinski.portfolio.application.client.dto.ClientLoginRequestDTO;
import com.dudzinski.portfolio.domain.client.ClientService;
import com.dudzinski.portfolio.domain.client.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService clientService;

    public LoginResponseDTO login(ClientLoginRequestDTO loginRequestDTO) {
        return clientService.login(loginRequestDTO);
    }

}
