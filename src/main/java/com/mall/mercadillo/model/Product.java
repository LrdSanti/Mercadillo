package com.mall.mercadillo.model;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product", indexes = @Index(name = "inx_name", columnList = "name", unique = true))
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

  

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture", length = 1000)
    private byte[] picture;

    // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JsonIgnoreProperties({ "hibernateLazyInitializer", "hibernate" })
    //al hacer marge descomentar categoria 
    // private Category category;

    // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JsonIgnoreProperties({ "hibernateLazyInitializer", "hibernate" })
    //al hacer marge descomentar maker 
    // private Maker maker;

    @Serial
    private static final long serialVersionUID = -731210493L;
    
}
