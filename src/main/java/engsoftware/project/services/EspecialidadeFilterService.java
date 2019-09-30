package engsoftware.project.services;

import engsoftware.project.models.Especialidade;
import engsoftware.project.services.filters.especialidade.EspecialidadeFilter;
import engsoftware.project.services.filters.especialidade.EspecialidadeNomeFilter;
import engsoftware.project.services.filters.especialidade.FilterObjectEspecialidade;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EspecialidadeFilterService {
    public Set<Especialidade> filterEspecialidade(Set<Especialidade> especialidades, FilterObjectEspecialidade filterObjectEspecialidade) {
        System.out.println(filterObjectEspecialidade);
        EspecialidadeFilter EspecialidadeNomeFilter = new EspecialidadeNomeFilter(filterObjectEspecialidade.getNome());
        return EspecialidadeNomeFilter.filter(especialidades);
    }
}
