package com.asil.repositories;

import com.asil.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario,Long> {
}
