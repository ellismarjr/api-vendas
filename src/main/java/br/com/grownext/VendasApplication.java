package br.com.grownext;

import br.com.grownext.domain.entity.Cliente;
import br.com.grownext.domain.repositorio.ClientesRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.GsonBuilderUtils;
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
            clientesRespositorio.save(new Cliente("Fulano"));
            clientesRespositorio.save(new Cliente("Cliente Junior"));

            System.out.println("LISTANDO CLIENTES");
            List<Cliente> result = clientesRespositorio.encontrarPorNome("Junior");
            result.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
