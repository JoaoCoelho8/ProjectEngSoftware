package engsoftware.project.services.filters.paciente;


import engsoftware.project.models.Paciente;
import engsoftware.project.services.filters.FilterI;

import java.util.Set;

public interface PacienteFilter extends FilterI<Paciente> {
    Set<Paciente> filter(Set<Paciente> entities);
}
