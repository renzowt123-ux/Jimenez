package com.jimenez.pedido.service.impi;

import com.jimenez.pedido.dto.ProductoResponseDTO;
import com.jimenez.pedido.entity.Producto;
import com.jimenez.pedido.mapper.ProductoMapper;
import com.jimenez.pedido.repository.ProductoRepository;
import com.jimenez.pedido.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    @Autowired
    private ProductoRepository repository;
    
    @Autowired
    private ProductoMapper mapper;
    
    @Override
    @Transactional(readOnly = true)
    public Page<ProductoResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ProductoResponseDTO> search(String texto, Pageable pageable) {
        if (texto == null || texto.isBlank()) {
            return repository.findAll(pageable)
                    .map(mapper::toDTO);
        }
        return repository.findByNombreContainingIgnoreCase(texto, pageable)
                .map(mapper::toDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO findById(String id) {
        return mapper.toDTO(repository.findById(id).orElseThrow());
    }
    
    @Override
    public ProductoResponseDTO create(Producto producto) {
        producto.setCreatedAt(System.currentTimeMillis());
        producto.setUpdatedAt(System.currentTimeMillis());
        return mapper.toDTO(repository.save(producto));
    }
    
    @Override
    public ProductoResponseDTO update(String id, Producto producto) {
        Producto existente = repository.findById(id).orElseThrow();
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setCategoriaId(producto.getCategoriaId());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setActivo(producto.getActivo());
        existente.setUpdatedAt(System.currentTimeMillis());
        return mapper.toDTO(repository.save(existente));
    }
    
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
