package brunoserraoa.com.github.vendas.model.repository;

import brunoserraoa.com.github.vendas.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
