package engsoftware.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Especialidade extends BaseModel {

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    private Medico medico;

    private String nome;
    private float preco;

    public Especialidade(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
    }

}
