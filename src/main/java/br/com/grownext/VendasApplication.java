package br.com.grownext;

import br.com.grownext.domain.entity.Cliente;
import br.com.grownext.domain.entity.Pedido;
import br.com.grownext.domain.repository.ClientesRespository;
import br.com.grownext.domain.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {})
@RestController
public class VendasApplication {
    @Bean
    public CommandLineRunner init(@Autowired ClientesRespository clientesRespository, @Autowired PedidosRepository pedidosRepository) {
        return args -> {
            System.out.println("SALVANDO CLIENTES");
            Cliente fulano = new Cliente("Fulano");
            clientesRespository.save(fulano);


            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidosRepository.save(p);

//            Cliente cliente = clientesRespository.findClienteFetchPedidos(fulano.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            pedidosRepository.findByCliente(fulano).forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
