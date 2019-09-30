package engsoftware.project.services.filters.consulta;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Especialidade;

import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaEspecialidadeFilter implements ConsultaFilter {

    private Especialidade especialidadeToFilter;

    public ConsultaEspecialidadeFilter(Especialidade especialidadeToFilter) {
        this.especialidadeToFilter = especialidadeToFilter;
    }

    @Override
    public Set<Consulta> filter(Set<Consulta> consultas) {
        if (especialidadeToFilter == null) return consultas;

        return consultas.stream()
                .filter(consulta -> consulta.getTipo().equals(this.especialidadeToFilter))
                .collect(Collectors.toSet());
    }
}
