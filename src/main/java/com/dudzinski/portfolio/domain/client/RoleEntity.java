package com.dudzinski.portfolio.domain.client;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_role")
public class RoleEntity {

    @Id
    @Column(name = "id", precision = 15, scale = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private RoleType name;

    @Column(name = "description")
    private String description;

    public RoleEntity(RoleType name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity roleEntity = (RoleEntity) o;
        return Objects.equals(id, roleEntity.id) && Objects.equals(name, roleEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
