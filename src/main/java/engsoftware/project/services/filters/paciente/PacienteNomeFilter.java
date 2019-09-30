package engsoftware.project.services.filters.paciente;

import engsoftware.project.models.Paciente;

import java.util.Set;
import java.util.stream.Collectors;

public class PacienteNomeFilter implements PacienteFilter {

    private String nomeToFilter;

    public PacienteNomeFilter(String nomeToFilter) {
        this.nomeToFilter = nomeToFilter;
    }

    @Override
    public Set<Paciente> filter(Set<Paciente> pacientes) {
        if (nomeToFilter == null) return pacientes;

        return pacientes.stream()
                .filter(paciente -> paciente.getNome().equals(this.nomeToFilter))
                .collect(Collectors.toSet());
    }
}
