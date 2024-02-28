package brunoserraoa.com.github.vendas.service;

import java.util.List;

import brunoserraoa.com.github.vendas.exception.ResourceNotFoundException;
import brunoserraoa.com.github.vendas.model.entity.Produto;
import brunoserraoa.com.github.vendas.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto pesquisarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUTO_NAO_ENCONTRADO));
    }

    public List<Produto> pesquisarTodos() {
        return produtoRepository.findAll();
    }

    public Produto incluir(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Produto produto, Long id) {
        return produtoRepository.findById(id)
                .map(m -> {
                    produto.setId(m.getId());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new ResourceNotFoundException(PRODUTO_NAO_ENCONTRADO));
    }

    public void excluir(Long id) {
        produtoRepository.findById(id)
                .map(m -> {
                    produtoRepository.delete(m);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResourceNotFoundException(PRODUTO_NAO_ENCONTRADO));
    }

}
