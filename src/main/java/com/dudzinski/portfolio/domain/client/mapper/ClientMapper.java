package com.dudzinski.portfolio.domain.client.mapper;

import com.dudzinski.portfolio.domain.client.ClientEntity;
import com.dudzinski.portfolio.domain.client.dto.NewClientDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

public class ClientMapper {
    public static ClientEntity mapNewUserRequestToUser(NewClientDTO client, BCryptPasswordEncoder bcryptEncoder) {

        return ClientEntity.builder().externalId((client.getExternalId()))
                .login(client.getUsername())
                .password(bcryptEncoder.encode(client.getPassword()))
                .roleEntities(new HashSet<>())
                .build();
    }
}
