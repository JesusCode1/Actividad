/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cibertec.ApiProductoCategoria.service;

import com.cibertec.ApiProductoCategoria.dao.ProductoRepository;
import com.cibertec.ApiProductoCategoria.entity.Producto;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoRepository productoRepository;

    
    @Override
    public List<Producto> findAll() {
        return (List<Producto>)productoRepository.findAll();
    }

    
    @Override
    public Page<Producto> findPage(int page, int size) {
        return productoRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository
                .findById(id)
                .orElseThrow(()->new EntityNotFoundException("Producto no encontrado con la id: "+id.toString()));
    }

    @Override
    public Producto findBynombreProducto(String nombreProducto) {
        return productoRepository.findBynombreProducto(nombreProducto);
    }

    @Override
    public Producto add(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Producto producto) {
        var ProductoDB = productoRepository.findById(producto.getId()).get();
        ProductoDB.setNombreProducto(producto.getNombreProducto());
        ProductoDB.setDescripcion(producto.getDescripcion());
        ProductoDB.setPrecio(producto.getPrecio());
        ProductoDB.setStock(producto.getStock());
        ProductoDB.setEstado(producto.getEstado());
        
        return productoRepository.save(ProductoDB);
    }

    @Override
    public void delete(Long id) {
        var ProductoDB = productoRepository.findById(id).get();
        
        productoRepository.delete(ProductoDB);
        
        
    }
}
