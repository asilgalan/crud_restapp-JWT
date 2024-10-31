package com.asil.services;

import com.asil.models.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);


    Producto update(Long id,Producto producto);
    void delete(Long id);
}
