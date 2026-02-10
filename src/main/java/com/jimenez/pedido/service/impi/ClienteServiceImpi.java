package com.jimenez.pedido.service.impi;

import com.jimenez.pedido.entity.Clientes;
import com.jimenez.pedido.repository.ClienteRepository;
import com.jimenez.pedido.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpi implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<Clientes> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Clientes findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Clientes create(Clientes clientes) {
        return repository.save(clientes);
    }

    @Override
    @Transactional
    public Clientes update(Long id, Clientes cliente) {
        Clientes existente = findById(id);
        existente.setRazonSocial(cliente.getRazonSocial());
        existente.setDocumento(cliente.getDocumento());
        existente.setDireccion(cliente.getDireccion());
        existente.setTelefono(cliente.getTelefono());
        return repository.save(existente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
