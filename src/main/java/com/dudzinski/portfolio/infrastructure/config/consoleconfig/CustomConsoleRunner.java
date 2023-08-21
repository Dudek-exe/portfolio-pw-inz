package com.dudzinski.portfolio.infrastructure.config.consoleconfig;

import com.dudzinski.portfolio.domain.client.ClientService;
import com.dudzinski.portfolio.domain.client.RoleEntity;
import com.dudzinski.portfolio.domain.client.RoleType;
import com.dudzinski.portfolio.domain.client.dto.NewClientDTO;
import com.dudzinski.portfolio.domain.client.impl.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomConsoleRunner implements CommandLineRunner {

    private static final String NEW_USER_CREATED_INFO = "New default %s created";
    private static final String NEW_USER_CREATING_INFO = "%s account not found, creating new default account";
    private static final String ADMIN_STRING = "ADMIN";
    private static final String BASIC_USER_STRING = "USER";
    ClientService clientService;
    RoleRepository roleRepository;

    @Autowired
    public CustomConsoleRunner(ClientService clientService, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) {
        setUpDefaultRoles();
        setUpDefaultAdminAccount();
        setUpDefaultUserAccount();
    }

    private void setUpDefaultRoles() {
        createAdminRoleIfNotExists();
        createUserRoleIfNotExists();
    }

    private void createAdminRoleIfNotExists() {
        if (roleRepository.findByName(RoleType.ADMIN).isPresent()) {
            log.info("Found default ADMIN role");
        } else {
            RoleEntity roleEntity = new RoleEntity(RoleType.ADMIN, "Admin role");
            roleRepository.save(roleEntity);
            log.info("Created ADMIN role");
        }
    }

    private void createUserRoleIfNotExists() {
        if (roleRepository.findByName(RoleType.BASIC_USER).isPresent()) {
            log.info("Found default ADMIN role");
        } else {
            RoleEntity roleEntity = new RoleEntity(RoleType.BASIC_USER, "User role");
            roleRepository.save(roleEntity);
            log.info("Created USER role");
        }
    }

    private void setUpDefaultAdminAccount() {
        String adminUsername = "admin";
        if (clientService.isPresentByLogin(adminUsername)) {
            return;
        }
        log.info(String.format(NEW_USER_CREATING_INFO, ADMIN_STRING));
        NewClientDTO newAdminRequest = NewClientDTO.builder()
                .externalId("111")
                .name("Marek")
                .surname("Dudzinski")
                .email("marekAdminUser@gmail.pl")
                .username(adminUsername)
                .phoneNumber("987654321")
                .password("qwerty")
                .build();
        log.info(String.format(NEW_USER_CREATED_INFO, ADMIN_STRING));
        clientService.createNewUser(newAdminRequest, RoleType.ADMIN);
    }

    private void setUpDefaultUserAccount() {
        String userNameLogin = "User1";
        if (clientService.isPresentByLogin(userNameLogin)) {
            return;
        }
        log.info(String.format(NEW_USER_CREATING_INFO, BASIC_USER_STRING));
        NewClientDTO newUserRequest = NewClientDTO.builder()
                .externalId("222")
                .name("Marek")
                .surname("Dudzinski")
                .email("marekBasicUser@gmail.pl")
                .username(userNameLogin)
                .phoneNumber("123456789")
                .password("qwerty")
                .build();
        log.info(String.format(NEW_USER_CREATED_INFO, BASIC_USER_STRING));
        clientService.createNewUser(newUserRequest, RoleType.BASIC_USER);
    }
}
