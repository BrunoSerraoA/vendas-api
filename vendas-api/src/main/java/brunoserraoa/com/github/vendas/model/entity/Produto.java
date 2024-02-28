package brunoserraoa.com.github.vendas.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUTOS")
public class Produto {

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Getter
    @Column(name = "descricao", length = 100)
    @Length(min = 2, max = 100, message = "O campo PRODUTO deve ser entre 2 e 100 caracteres")
    @NotNull(message = "Campo DESCRICAO não deve ser nulo")
    @NotEmpty(message = "Campo DESCRICAO deve ser informado")
    private String descricao;
    
    @Getter
    @Column(name = "preco_unitario", precision = 10, scale = 2)
    @NotNull(message = "Campo PRECO deve ser informado")
    private BigDecimal preco;
    
}
