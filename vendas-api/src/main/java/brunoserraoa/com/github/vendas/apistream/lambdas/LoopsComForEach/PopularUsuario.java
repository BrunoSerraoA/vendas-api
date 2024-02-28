package brunoserraoa.com.github.vendas.apistream.lambdas.LoopsComForEach;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import brunoserraoa.com.github.vendas.apistream.lambdas.Usuario;

public class PopularUsuario {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("Ewerton Carreira", 150);
        Usuario user2 = new Usuario("Vinicius Marques", 120);
        Usuario user3 = new Usuario("Beatriz Seraphim", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        Consumer<Usuario> mostrador = new Consumer<Usuario>() {
            public void accept(Usuario u) {
                System.out.println(u.getNome());
            }
        };

        usuarios.forEach(mostrador);
    }
}
