package brunoserraoa.com.github.vendas.service.pedido;

import java.util.Optional;

import brunoserraoa.com.github.vendas.dto.PedidoDto;
import brunoserraoa.com.github.vendas.enums.StatusPedido;
import brunoserraoa.com.github.vendas.model.entity.Pedido;

public interface PedidoService {
    
    Pedido incluir(PedidoDto pedidoDto);

    Optional<Pedido> pedidoCompleto(Long id);

    void alterarStatus(Long id, StatusPedido statusPedido);
    
}
