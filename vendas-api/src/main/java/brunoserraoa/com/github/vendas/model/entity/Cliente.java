package brunoserraoa.com.github.vendas.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTES")
public class Cliente {

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "nome", length = 40)
    @Length(min = 2, max = 40, message = "O campo NOME deve ser entre 2 e 40 caracteres")
    @NotNull(message = "Campo NOME não deve ser nulo")
    @NotEmpty(message = "O campo NOME é obrigatório.")
    private String nome;

    @Getter
    @Column(name = "endereco", length = 70)
    @Length(min = 2, max = 70, message = "O campo NOME deve ser entre 2 e 70 caracteres")
    @NotNull(message = "Campo ENDEREÇO não deve ser nulo")
    @NotEmpty(message = "O campo ENDEREÇO é obrigatório.")
    private String endereco;
    
    @Getter
    @Column(name = "email", length = 20)
    @Email(message = "Informe um E-MAIL válido.")
    private String email;
    
    @Getter
    @Column(name = "telefone", length = 15)
    @NotNull(message = "Campo TELEFONE não deve ser nulo")
    @NotEmpty(message = "O campo TELEFONE é obrigatório.")
    private String telefone;

    @Getter
    @Column(name = "cpf", length = 14)
    @CPF(message = "Informe um CPF válido.")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

}
