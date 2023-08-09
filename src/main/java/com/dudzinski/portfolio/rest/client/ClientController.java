package com.dudzinski.portfolio.rest.client;

import com.dudzinski.portfolio.application.client.ClientFacade;
import com.dudzinski.portfolio.application.client.dto.ClientLoginRequestDTO;
import com.dudzinski.portfolio.application.client.dto.ClientRegisterRequestDTO;
import com.dudzinski.portfolio.domain.client.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = ClientControllerConstants.RESOURCE_LOGIN)
class ClientController {

    private final ClientFacade clientFacade;

    @PostMapping("register")
    void register(@RequestBody @Valid ClientRegisterRequestDTO requestDTO) {
        clientFacade.register(requestDTO.getLogin(), requestDTO.getPassword());
    }

    @PostMapping
    public LoginResponseDTO login(@RequestBody @Valid ClientLoginRequestDTO requestDTO) {
        return clientFacade.login(requestDTO);
    }

}
