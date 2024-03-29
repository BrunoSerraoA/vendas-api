package brunoserraoa.com.github.vendas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDto {
    
    private Long produto;
    private Integer quantidade;
}
