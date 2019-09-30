package engsoftware.project.services.filters.consulta;

import engsoftware.project.models.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class FilterObjectConsulta {
    private Paciente nrUtente;
    private Especialidade especialidade;
    private DayOfWeek dia;
    private LocalTime hora;
    private Medico medico;
}
