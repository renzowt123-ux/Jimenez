package com.jimenez.pedido.service.impi;

import com.jimenez.pedido.dto.CategoriaResponseDTO;
import com.jimenez.pedido.entity.Categoria;
import com.jimenez.pedido.mapper.CategoriaMapper;
import com.jimenez.pedido.repository.CategoriaRepository;
import com.jimenez.pedido.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    
    @Autowired
    private CategoriaRepository repository;
    
    @Autowired
    private CategoriaMapper mapper;
    
    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaResponseDTO> search(String texto, Pageable pageable) {
        if (texto == null || texto.isBlank()) {
            return repository.findAll(pageable)
                    .map(mapper::toDTO);
        }
        return repository.findByNombreContainingIgnoreCase(texto, pageable)
                .map(mapper::toDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO findById(String id) {
        return mapper.toDTO(repository.findById(id).orElseThrow());
    }
    
    @Override
    public CategoriaResponseDTO create(Categoria categoria) {
        categoria.setCreatedAt(System.currentTimeMillis());
        categoria.setUpdatedAt(System.currentTimeMillis());
        return mapper.toDTO(repository.save(categoria));
    }
    
    @Override
    public CategoriaResponseDTO update(String id, Categoria categoria) {
        Categoria existente = repository.findById(id).orElseThrow();
        existente.setNombre(categoria.getNombre());
        existente.setDescripcion(categoria.getDescripcion());
        existente.setUpdatedAt(System.currentTimeMillis());
        return mapper.toDTO(repository.save(existente));
    }
    
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
