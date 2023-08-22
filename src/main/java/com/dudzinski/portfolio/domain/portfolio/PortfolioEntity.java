package com.dudzinski.portfolio.domain.portfolio;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_portfolio")
public class PortfolioEntity {

    @Id
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username_owner")
    private String usernameOwner;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PortfolioType type;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
}