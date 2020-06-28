package br.com.grownext.rest.controller;

import br.com.grownext.domain.entity.Cliente;
import br.com.grownext.domain.repository.ClientesRespository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
public class ClienteController {

    private ClientesRespository clientesRespository;

    public ClienteController(ClientesRespository clientesRespository) {
        this.clientesRespository = clientesRespository;
    }

    @GetMapping("/cliente/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientesRespository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientesRespository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientesRespository.findById(id);
        if (cliente.isPresent()) {
            clientesRespository.delete((cliente.get()));
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("clientes/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clientesRespository.findById(id).map(clienteExistente ->  {
            cliente.setId(clienteExistente.getId());
            clientesRespository.save(cliente);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("clientes")
    @ResponseBody
    public ResponseEntity find( Cliente filtro) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, exampleMatcher);
        List<Cliente> clientes = clientesRespository.findAll(example);
        return ResponseEntity.ok(clientes);
    }
}
