package com.asil.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{NotBlank.producto.nombre}")
    @Size(min = 2,max = 20)
    private String nombre;

    @NotNull(message = "{NotNull.producto.precio}")
    @Min(20)
    @Max(1000000)
    private Integer precio;
    @NotBlank(message = "{NotBlank.producto.descripcion}")
    private String descripcion;


}
