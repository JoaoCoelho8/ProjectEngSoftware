package engsoftware.project.services.filters.medico;

import engsoftware.project.models.Especialidade;
import lombok.Data;

import java.time.LocalTime;

@Data
public class FilterObjectMedico {
    private String nome;
    private Especialidade especialidade;
    private LocalTime dia;
    private LocalTime horaInicio;
    private LocalTime horaFim;
}
