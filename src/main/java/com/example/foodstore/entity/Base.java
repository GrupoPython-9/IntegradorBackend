package com.example.foodstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass //Esto le indica a Hibernate/JPA que los campos de Base deben ser parte del mapeo de las entidades hijas.
public abstract class Base {
    @Builder.Default
    protected boolean eliminado = false ;
}
