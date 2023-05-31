package com.luxoft.demoshopwithspring.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS",
        uniqueConstraints = {@UniqueConstraint(name="PRODUCTS_UK", columnNames = {"name","brand","quality"})})
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "QUALITY", nullable = false)
    private String quality;

    @Column(name = "STOCK", nullable = false)
    private Long stock;

}