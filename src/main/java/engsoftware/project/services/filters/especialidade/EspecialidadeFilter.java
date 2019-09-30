package engsoftware.project.services.filters.especialidade;

import engsoftware.project.models.Especialidade;

import java.util.Set;

public interface EspecialidadeFilter {
    Set<Especialidade> filter(Set<Especialidade> entities);
}
