package brunoserraoa.com.github.vendas.controller;

import java.util.List;

import javax.validation.Valid;

import brunoserraoa.com.github.vendas.exception.ApiErros;
import brunoserraoa.com.github.vendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import brunoserraoa.com.github.vendas.model.entity.Cliente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Obter detalhes de um cliente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado."),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o Id informado.", response = ApiErros.class)
    })
    public Cliente pesquisarPorId(@PathVariable Long id) {
        return clienteService.pesquisarPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todos os clientes cadastrados.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista de clientes cadastrados.")})
    public List<Cliente> pesquisarTodos() {
        return clienteService.pesquisarTodos();
    }

    @PostMapping
    @ApiOperation(value = "Salva um novo cliente.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação", response = ApiErros.class)
    })
    public Cliente incluir(@Valid @RequestBody Cliente cliente) {
        return clienteService.incluir(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualiza um cliente existente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação.", response = ApiErros.class),
            @ApiResponse(code = 404, message = "Cliente não encontrado.", response = ApiErros.class)
    })
    public Cliente atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.atualizar(cliente, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui um cliente existente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente excluído com sucesso."),
            @ApiResponse(code = 404, message = "Cliente não encontrado.", response = ApiErros.class)
    })
    public void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }
}
