package com.asil.services;

import com.asil.models.Role;
import com.asil.models.Usuario;
import com.asil.repositories.IRoleRepository;
import com.asil.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }
    @Override
    public Usuario save(Usuario usuario) {
        Optional<Role> optionalRole=roleRepository.findByName("ROLE_USER");
        List<Role> roles=new ArrayList<>();
        optionalRole.ifPresent(roles::add);

        if(usuario.isAdmin()){
            Optional<Role> optionalRoleAdmin=roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        usuario.setRoles(roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }


}
