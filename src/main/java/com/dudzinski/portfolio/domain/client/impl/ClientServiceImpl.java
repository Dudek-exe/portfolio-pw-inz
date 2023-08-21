package com.dudzinski.portfolio.domain.client.impl;

import com.dudzinski.portfolio.application.client.dto.ClientLoginRequestDTO;
import com.dudzinski.portfolio.domain.client.ClientEntity;
import com.dudzinski.portfolio.domain.client.ClientService;
import com.dudzinski.portfolio.domain.client.RoleType;
import com.dudzinski.portfolio.domain.client.dto.LoginResponseDTO;
import com.dudzinski.portfolio.domain.client.dto.NewClientDTO;
import com.dudzinski.portfolio.domain.client.mapper.ClientMapper;
import com.dudzinski.portfolio.infrastructure.config.security.token.TokenProviderImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final TokenProviderImpl tokenProviderImpl;
    private final BCryptPasswordEncoder bcryptEncoder;

    @Override
    public LoginResponseDTO login(ClientLoginRequestDTO requestDTO) {
        log.info("request on generate-token");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getLogin(),
                        requestDTO.getPassword()
                )

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginResponseDTO(tokenProviderImpl.generateToken(authentication));
    }

    @Override
    public void register(String login, String password) {
        //TODO IMPLEMENT ME
    }

    @Override
    public void createNewUser(NewClientDTO client, RoleType roleType) {
        ClientEntity newUser = ClientMapper.mapNewUserRequestToUser(client, bcryptEncoder);
        newUser.addRole(roleRepository.getByName(roleType));
        clientRepository.save(newUser);
    }

    @Override
    public boolean isPresentByLogin(String login) {
        return clientRepository.findByLogin(login).isPresent();
    }

}
