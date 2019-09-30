package engsoftware.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Consulta extends BaseModel {

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private Medico medico;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private Paciente paciente;

    private LocalDateTime horario;
    private LocalDateTime fimExpectavel;

    @OneToOne
    private Especialidade tipo;

    public Consulta(Paciente nrUtente, LocalDateTime horario, Medico medico) {
        this.paciente = nrUtente;
        this.setHorario(horario);
        this.medico = medico;
    }


    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
        this.fimExpectavel = horario.plusMinutes(30);
    }
}
