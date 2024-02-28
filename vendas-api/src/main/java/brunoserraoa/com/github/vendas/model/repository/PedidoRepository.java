package brunoserraoa.com.github.vendas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brunoserraoa.com.github.vendas.model.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // TODO Desafio 2: estudar JPQL
    @Query("select p from Pedido p left join fetch p.itens where p.id= :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Long id);
}
