package brunoserraoa.com.github.vendas.apistream.lambdas.LoopsDeManeiraAntiga;

import java.util.Arrays;
import java.util.List;

import brunoserraoa.com.github.vendas.apistream.lambdas.Usuario;

public class PopularUsuario {
    
    public static void main(String[] args) {
        Usuario user1 = new Usuario("Ewerton Carreira", 150);
        Usuario user2 = new Usuario("Vinicius Marques", 120);
        Usuario user3 = new Usuario("Beatriz Seraphim", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2 ,user3);

        for(Usuario usuarioParaImpressao : usuarios) {
            System.out.println(usuarioParaImpressao.getNome());
        }
        
    }
}
