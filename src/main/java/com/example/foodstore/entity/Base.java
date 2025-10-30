package com.example.foodstore.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@MappedSuperclass //Esto le indica a Hibernate/JPA que los campos de Base deben ser parte del mapeo de las entidades hijas.
public abstract class Base {
    @Builder.Default
    protected boolean eliminado = false ;
}
