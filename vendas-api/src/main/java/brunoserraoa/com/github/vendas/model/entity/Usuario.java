package brunoserraoa.com.github.vendas.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIOS")
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo login obrigatório.")
    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    @NotEmpty(message = "Campo senha obrigatório.")
    private String senha;

    @Column(name = "admin")
    private boolean admin;

}