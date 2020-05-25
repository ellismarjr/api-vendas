package br.com.grownext;

import br.com.grownext.domain.entity.Cliente;
import br.com.grownext.domain.repositorio.ClientesRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {})
@RestController
public class VendasApplication {
    @Bean
    public CommandLineRunner init(@Autowired ClientesRespositorio clientesRespositorio) {
        return args -> {
            System.out.println("SALVANDO CLIENTES");
            clientesRespositorio.salvar(new Cliente("Ellismar"));
            clientesRespositorio.salvar(new Cliente("Cliente Junior"));

            System.out.println("LISTANDO CLIENTES");
            List<Cliente> todosClientes = clientesRespositorio.obterTodos();
            todosClientes.forEach(System.out::println);
//
//            System.out.println("ATUALIZANDO CLIENTES");
//            todosClientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado");
//                clientesRespositorio.atualizar(c);
//            });
//
//            System.out.println("BUSCANDO CLIENTE POR NOME");
//            clientesRespositorio.buscarPorNome("Cli").forEach(System.out::println);
//
//            System.out.println("Deletando clientes");
//            clientesRespositorio.obterTodos().forEach(c -> {
//                clientesRespositorio.deletar(c);
//            });
//
//            todosClientes = clientesRespositorio.obterTodos();
//            if (todosClientes.isEmpty()) {
//                System.out.println("nenhum cliente encontrado");
//            } else {
//                todosClientes.forEach(System.out::println);
//            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
