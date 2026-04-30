package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.ProductoRequestDTO;
import com.pastelarte.pastelarte_api.dto.ProductoResponseDTO;
import com.pastelarte.pastelarte_api.entities.Producto;
import com.pastelarte.pastelarte_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<ProductoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO buscar(Integer id) {
        Producto producto = repository.findById(id).orElse(null);

        if (producto == null) {
            return null;
        }

        return convertirAResponse(producto);
    }

    public ProductoResponseDTO guardar(ProductoRequestDTO dto) {

        Producto producto = new Producto();

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria());

        return convertirAResponse(repository.save(producto));
    }

    public ProductoResponseDTO actualizar(Integer id, ProductoRequestDTO dto) {

        Producto producto = repository.findById(id).orElse(null);

        if (producto == null) {
            return null;
        }

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria());

        return convertirAResponse(repository.save(producto));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private ProductoResponseDTO convertirAResponse(Producto producto) {

        ProductoResponseDTO dto = new ProductoResponseDTO();

        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setCategoria(producto.getCategoria());

        return dto;
    }
}