package brunoserraoa.com.github.vendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brunoserraoa.com.github.vendas.exception.ResourceNotFoundException;
import brunoserraoa.com.github.vendas.model.entity.Cliente;
import brunoserraoa.com.github.vendas.model.repository.ClienteRepository;

@Service
public class ClienteService {

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente pesquisarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NAO_ENCONTRADO));
    }

    public List<Cliente> pesquisarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente incluir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente, Long id) {
        // Utilizando forma tradicional de desenvolvimento
        // Cliente encontrado = pesquisarPorId(id);
        // cliente.setId(encontrado.getId());
        // return clienteRepository.save(cliente);

        // Utilizando API Stream
        return clienteRepository.findById(id)
                .map(m -> {
                    cliente.setId(m.getId());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NAO_ENCONTRADO));
    }

    public void excluir(Long id) {
        // Utilizando forma tradicional de desenvolvimento
        // Cliente encontrado = pesquisarPorId(id);
        // clienteRepository.delete(encontrado);

         // Utilizando API Stream
        clienteRepository.findById(id)
                .map(m -> {
                    clienteRepository.delete(m);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NAO_ENCONTRADO));
    }
}
