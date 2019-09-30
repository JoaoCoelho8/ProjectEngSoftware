package engsoftware.project.services;

import engsoftware.project.models.Especialidade;
import engsoftware.project.models.Medico;
import engsoftware.project.services.filters.especialidade.FilterObjectEspecialidade;

import java.util.Optional;
import java.util.Set;

public interface EspecialidadeServiceI {

    Set<Especialidade> getSetEspecialidade();

    Set<Especialidade> getFilteredEspecialidade(FilterObjectEspecialidade filterObjectEspecialidade);

    Optional<Especialidade> findById(Long id);

    Optional<Especialidade> findByName(String nameEspecialidade);

    Optional<Medico> findByMedico(String nameMedico);

    Set<Especialidade> findAll();

    Especialidade save(Especialidade especialidade);

    Optional<Especialidade> saveEspecialidade(Especialidade especialidade, String nameMedico);

    Optional<Especialidade> removeEspecialidade(String nameEspecialidade);
}
