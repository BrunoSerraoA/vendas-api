package brunoserraoa.com.github.vendas.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import brunoserraoa.com.github.vendas.dto.AtualizacaoStatusPedidoDto;
import brunoserraoa.com.github.vendas.dto.InformacaoItemPedidoDto;
import brunoserraoa.com.github.vendas.dto.InformacaoPedidoDto;
import brunoserraoa.com.github.vendas.dto.PedidoDto;
import brunoserraoa.com.github.vendas.exception.ApiErros;
import brunoserraoa.com.github.vendas.exception.ResourceNotFoundException;
import brunoserraoa.com.github.vendas.model.entity.ItemPedido;
import brunoserraoa.com.github.vendas.model.entity.Pedido;
import brunoserraoa.com.github.vendas.service.pedido.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import brunoserraoa.com.github.vendas.enums.StatusPedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    // @Autowired - comentado para demonstrar como injetar um classe manualmente
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ApiOperation(value = "Salva um novo pedido.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido salvo com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação", response = ApiErros.class)
    })
    public Long incluir(@RequestBody @Valid PedidoDto pedidoDTO) {
        Pedido pedido = pedidoService.incluir(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Mostra o pedido.")
    @ApiResponses({@ApiResponse(code = 200, message = "Mostra o pedido cadastrado.")})
    public InformacaoPedidoDto pedidoCompleto(@PathVariable Long id) {
        return pedidoService.pedidoCompleto(id)
                .map(pedido -> builderInformacaoPedidoDTO(pedido))
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado."));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualiza status do pedido.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Status atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação.", response = ApiErros.class),
            @ApiResponse(code = 404, message = "Pedido não encontrado.", response = ApiErros.class)
    })
    public void alterarStatus(@PathVariable Long id,
                              @RequestBody AtualizacaoStatusPedidoDto atualizacaoStatusPedidoDTO) {
        String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
        pedidoService.alterarStatus(id, StatusPedido.valueOf(novoStatus));
    }


    private InformacaoPedidoDto builderInformacaoPedidoDTO(Pedido pedido) {
        return InformacaoPedidoDto.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(builderInformacaoItemPedidoDTO(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDto> builderInformacaoItemPedidoDTO(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens))
            return Collections.emptyList();

        return itens.stream()
                .map(item -> InformacaoItemPedidoDto.builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }

}