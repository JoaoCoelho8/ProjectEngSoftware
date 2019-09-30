package engsoftware.project.services.filters.especialidade;

import engsoftware.project.models.Especialidade;

import java.util.Set;
import java.util.stream.Collectors;

public class EspecialidadeNomeFilter implements EspecialidadeFilter {

    private String nomeToFilter;

    public EspecialidadeNomeFilter(String nomeToFilter) {
        this.nomeToFilter = nomeToFilter;
    }

    @Override
    public Set<Especialidade> filter(Set<Especialidade> especialidades) {
        if (nomeToFilter == null) return especialidades;

        return especialidades.stream()
                .filter(especialidade -> especialidade.getNome().equals(this.nomeToFilter))
                .collect(Collectors.toSet());
    }
}
