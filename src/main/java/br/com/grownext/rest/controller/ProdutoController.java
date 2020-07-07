package br.com.grownext.rest.controller;

import br.com.grownext.domain.entity.Produto;
import br.com.grownext.domain.repository.ProdutosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    private ProdutosRepository produtosRepository;

    public ProdutoController(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        return produtosRepository.save(produto);
    }

    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id) {
        return produtosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @GetMapping
    public List findAll(Produto filtro) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, exampleMatcher);
        return produtosRepository.findAll(example);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void delete(@PathVariable Integer id) {
        produtosRepository.findById(id)
                .map(produto -> {
                    produtosRepository.delete(produto);
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produto) {
        produtosRepository.findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtosRepository.save(produto);
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }
}
