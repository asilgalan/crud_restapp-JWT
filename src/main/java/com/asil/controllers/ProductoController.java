package com.asil.controllers;

import com.asil.models.Producto;
import com.asil.services.IProductoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.query.sqm.EntityTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public List<Producto> list(){
        return productoService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){

      Optional<Producto> productoOptional= productoService.findById(id);
     if(productoOptional.isPresent()){
       return ResponseEntity.ok(productoOptional.orElseThrow());
     }
         return ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.save(producto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id,@RequestBody Producto producto,BindingResult result){
        Optional<Producto> existeProducto=productoService.findById(id);
        if(result.hasErrors()){
            return validation(result);
        }
        if(existeProducto.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productoService.update(id,producto));
        }
      return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            productoService.delete(id);
           return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }


    }

    public ResponseEntity<?> validation(BindingResult result){
        Map<String,String> errors=new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(),"El campo " + err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
