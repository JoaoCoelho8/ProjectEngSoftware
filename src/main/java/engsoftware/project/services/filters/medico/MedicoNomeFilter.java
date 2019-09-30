package engsoftware.project.services.filters.medico;

import engsoftware.project.models.Medico;

import java.util.Set;
import java.util.stream.Collectors;

public class MedicoNomeFilter implements MedicoFilter {
    private String nomeToFilter;

    public MedicoNomeFilter(String nomeToFilter) {
        this.nomeToFilter = nomeToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if (nomeToFilter == null) return medicos;

        return medicos.stream()
                .filter(medico -> medico.getNome().equals(this.nomeToFilter))
                .collect(Collectors.toSet());
    }
}
