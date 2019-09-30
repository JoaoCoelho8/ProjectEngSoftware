package engsoftware.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medico extends BaseModel {

    private String nome;
    private String email;
    private String nrTelemovel;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "medico")
    private Set<Especialidade> especialidades = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "medico")
    private Set<Consulta> consultas = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "medico")
    private Set<WorkTime> workTimes = new HashSet<>();

    public Medico(String nome, String email, String nrTelemovel, Especialidade especialidade) {
        this.nome = nome;
        this.email = email;
        this.nrTelemovel = nrTelemovel;
        this.addEspecialidadeeToMedico(especialidade);
    }

    public Medico(String nome, String email, String nrTelemovel) {
        this.nome = nome;
        this.email = email;
        this.nrTelemovel = nrTelemovel;
    }

    // add consulta a um medico
    public boolean addConsutaToMedico(Consulta consulta) {
        if (trabalha(consulta.getHorario()) && disponivel(consulta.getHorario())) {
            consultas.add(consulta);
            consulta.setMedico(this);
            consulta.setTipo(this.getEspecialidadeMedico());
            return true;
        } else {
            return false;
        }
    }

    public Especialidade getEspecialidadeMedico() {
        for (Especialidade especialidade : this.getEspecialidades()) {
            return especialidade;
        }
        return null;
    }

    // add espe. a um medico
    public void addEspecialidadeeToMedico(Especialidade especialidade) {
        especialidades.add(especialidade);
        especialidade.setMedico(this);
    }

    // remover espe. a um medico
    public void removeEspecialidadeToMedico(Especialidade especialidade) {
        especialidades.remove(especialidade);
    }


    // add worktime a um medico
    public void addWorkTimeToMedico(WorkTime workTime) {
        workTimes.add(workTime);
        workTime.setMedico(this);
    }

    public void removeWorkTimeFromMedico(WorkTime workTime) {
        workTimes.remove(workTime);
    }


    private boolean trabalha(LocalDateTime dataConsulta) {
        for (WorkTime horarioActual : this.getWorkTimes()) {
            if (horarioActual.getDay().equals(dataConsulta.getDayOfWeek()) && horarioActual.getStart().isBefore(dataConsulta.toLocalTime()) && horarioActual.getEnd().isAfter(dataConsulta.toLocalTime()) ) {
                        return true;
                    }
            }
        return false;
    }

    private boolean disponivel(LocalDateTime dataConsulta) {
        for (Consulta consultaMedico : this.getConsultas()) {
            if (consultaMedico.getHorario().getDayOfWeek().equals(dataConsulta.getDayOfWeek())) {
                if (consultaMedico.getHorario().equals(dataConsulta)) {
                    return false;
                }
                if (consultaMedico.getFimExpectavel().toLocalTime().isAfter(dataConsulta.toLocalTime())) {
                    return false;
                }
                if (consultaMedico.getHorario().toLocalTime().isBefore(dataConsulta.toLocalTime().plusMinutes(30))) {
                    return false;
                }
            }
        }
        return true;
    }

}
