package com.asil.repositories;

import com.asil.models.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoRepository extends CrudRepository<Producto,Long> {

}
