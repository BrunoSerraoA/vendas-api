package brunoserraoa.com.github.vendas.model.repository;

import brunoserraoa.com.github.vendas.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
