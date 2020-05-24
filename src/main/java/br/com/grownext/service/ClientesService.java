package br.com.grownext.service;

import br.com.grownext.model.Cliente;
import br.com.grownext.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository repository;

    public ClientesService(ClientesRepository repository) {
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);

        this.repository.persisttir(cliente);
    }

    public void validarCliente(Cliente cliente) {
        // aplica validações
    }
}
