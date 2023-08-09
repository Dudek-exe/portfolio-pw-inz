package com.dudzinski.portfolio.domain.client;

import com.dudzinski.portfolio.domain.car.CarEntity;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_client")
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private String externalId;

    @Column(unique = true, nullable = false, updatable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CarEntity> ownedCarEntities;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")})
    private Set<RoleEntity> roleEntities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_FAV_CRYPTO_CURRENCY", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "CRYPTOCURRENCY_ID")})
    private Set<CryptoCurrencyEntity> favouriteCryptoCurrencyEntity;

    public ClientEntity(Long id, String externalId, String login, String password, Set<CarEntity> ownedCarEntities, Set<RoleEntity> roleEntities, Set<CryptoCurrencyEntity> favouriteCryptoCurrencyEntity) {
        this.id = id;
        this.externalId = externalId;
        this.login = login;
        this.password = password;
        this.ownedCarEntities = ownedCarEntities;
        this.roleEntities = roleEntities;
        this.favouriteCryptoCurrencyEntity = favouriteCryptoCurrencyEntity;
    }

    public void addRole(RoleEntity roleEntity) {
        roleEntities.add(roleEntity);
    }

    public void addFavouriteCryptoCurrency(CryptoCurrencyEntity cryptoCurrencyEntity) {
        favouriteCryptoCurrencyEntity.add(cryptoCurrencyEntity);
    }

    public void removeFavouriteCryptoCurrency(CryptoCurrencyEntity cryptoCurrencyEntity) {
        favouriteCryptoCurrencyEntity.remove(cryptoCurrencyEntity);
    }


}
