package com.asil.services;

import com.asil.models.Producto;
import com.asil.repositories.IProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {
    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Producto> findById(Long id) {

        return productoRepository.findById(id);
    }

    @Transactional()
    @Override
    public Producto save(Producto producto) {
       return productoRepository.save(producto);
    }
   @Transactional
    @Override
    public Producto update(Long id, Producto producto) {
     Producto productoExistente=productoRepository.findById(id)
             .orElseThrow(() -> new EntityNotFoundException("El producto no existe"));

       productoExistente.setNombre(producto.getNombre());
       productoExistente.setPrecio(producto.getPrecio());
       productoExistente.setDescripcion(producto.getDescripcion());
       return productoRepository.save(productoExistente);


    }


    @Transactional()
    @Override
    public void delete(Long id) {
         if(productoRepository.existsById(id)){
                  productoRepository.deleteById(id);
             }else{
             throw new EntityNotFoundException("Producto no encontrado");
         }

    }
}
