package com.asil.controllers;

import com.asil.models.Role;
import com.asil.models.Usuario;
import com.asil.repositories.IRoleRepository;
import com.asil.services.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping
    public List<Usuario> lista(){
        return usuarioService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody  Usuario usuario, BindingResult result){

        if(result.hasErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.save(usuario));

    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String,String> errors=new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField()," El campo "+ err.getField()+ " " +err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
