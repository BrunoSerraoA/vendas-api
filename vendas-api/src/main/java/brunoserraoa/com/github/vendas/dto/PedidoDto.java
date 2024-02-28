package brunoserraoa.com.github.vendas.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    @NotNull(message = "Informe o código do cliente.")
    private Long cliente;

    @NotNull(message = "Campo total do pedido é obrigatório.")
    private BigDecimal total;

    // TODO criar a validação personalizada @NotEmptyList
    // @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDto> itens;

}
