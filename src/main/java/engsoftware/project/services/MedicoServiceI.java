package engsoftware.project.services;

import engsoftware.project.models.Medico;
import engsoftware.project.services.filters.medico.FilterObjectMedico;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface MedicoServiceI {

    Set<Medico> getSetMedico();

    Set<Medico> getFilteredMedico(FilterObjectMedico filterObjectMedico);

    Set<Medico> findAll();

    Optional<Medico> findById(Long id);

    Optional<Medico> findByName(String nameMedico);

    Medico save(Medico medico);

    ResponseEntity<Medico> saveMedico(Medico medico);

    Optional<Medico> removeMedico(String nameMedico);

}
