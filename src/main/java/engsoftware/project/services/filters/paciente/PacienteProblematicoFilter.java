package engsoftware.project.services.filters.paciente;

import engsoftware.project.models.Paciente;

import java.util.Set;
import java.util.stream.Collectors;

public class PacienteProblematicoFilter implements PacienteFilter {

    private boolean problematicoToFilter;

    public PacienteProblematicoFilter(boolean problematicoToFilter) {

        this.problematicoToFilter = problematicoToFilter;
    }

    @Override
    public Set<Paciente> filter(Set<Paciente> pacientes) {
        if (problematicoToFilter) {
            return pacientes;
        }
        return pacientes.stream()
                .filter(paciente -> paciente.isProblematico() == this.problematicoToFilter)
                .collect(Collectors.toSet());
    }
}
