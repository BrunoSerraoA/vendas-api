package brunoserraoa.com.github.vendas.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoItemPedidoDto {

    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
