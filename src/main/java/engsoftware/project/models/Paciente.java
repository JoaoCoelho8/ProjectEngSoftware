package engsoftware.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Paciente extends BaseModel {

    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "paciente")
    private Set<Consulta> consultas = new HashSet<>();

    private String nome;
    private int idade;
    private String nif;
    private boolean problematico;

    @Column(unique = true)
    private String nrUtenteSaude;

    public Paciente(String nome, int idade, String nif, boolean problematico, String nrUtenteSaude) {
        this.nome = nome;
        this.idade = idade;
        this.nif = nif;
        this.problematico = problematico;
        this.nrUtenteSaude = nrUtenteSaude;
    }

    // add consulta a um paciente
    public void addConsutaToPaciente(Consulta consulta) {
        if (!this.problematico) {
            consultas.add(consulta);
            consulta.setPaciente(this);
        }
    }

    public void removeConsultaFromPaciente(Consulta consulta) {
        consultas.remove(consulta);
        this.consultas.remove(consulta);
    }


}
