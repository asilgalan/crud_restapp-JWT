package com.asil.services;


import com.asil.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> findAll();
    Usuario save(Usuario usuario);

}
