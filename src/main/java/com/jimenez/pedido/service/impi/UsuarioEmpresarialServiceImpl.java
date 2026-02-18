package com.jimenez.pedido.service.impi;

import com.jimenez.pedido.dto.UsuarioEmpresarialResponseDTO;
import com.jimenez.pedido.entity.UsuarioEmpresarial;
import com.jimenez.pedido.mapper.UsuarioEmpresarialMapper;
import com.jimenez.pedido.repository.UsuarioEmpresarialRepository;
import com.jimenez.pedido.service.UsuarioEmpresarialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioEmpresarialServiceImpl implements UsuarioEmpresarialService {
    
    @Autowired
    private UsuarioEmpresarialRepository repository;
    
    @Autowired
    private UsuarioEmpresarialMapper mapper;
    
    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioEmpresarialResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioEmpresarialResponseDTO> search(String texto, Pageable pageable) {
        if (texto == null || texto.isBlank()) {
            return repository.findAll(pageable)
                    .map(mapper::toDTO);
        }
        return repository.findByRucContainingIgnoreCaseOrRazonSocialContainingIgnoreCase(
                texto, texto, pageable)
                .map(mapper::toDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UsuarioEmpresarialResponseDTO findById(Integer id) {
        return mapper.toDTO(repository.findById(id).orElseThrow());
    }
    
    @Override
    public UsuarioEmpresarialResponseDTO create(UsuarioEmpresarial usuario) {
        usuario.setId(null);
        usuario.setCreatedAt(System.currentTimeMillis());
        return mapper.toDTO(repository.save(usuario));
    }
    
    @Override
    public UsuarioEmpresarialResponseDTO update(Integer id, UsuarioEmpresarial usuario) {
        UsuarioEmpresarial existente = repository.findById(id).orElseThrow();
        existente.setRuc(usuario.getRuc());
        existente.setRazonSocial(usuario.getRazonSocial());
        existente.setEstado(usuario.getEstado());
        existente.setRol(usuario.getRol());
        return mapper.toDTO(repository.save(existente));
    }
    
    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UsuarioEmpresarial findByRuc(String ruc) {
        return repository.findByRuc(ruc).orElseThrow();
    }
}
