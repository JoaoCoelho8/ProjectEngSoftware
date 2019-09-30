package engsoftware.project.services;

import engsoftware.project.models.Paciente;
import engsoftware.project.services.filters.paciente.FilterObjectPaciente;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface PacienteServiceI {

    Set<Paciente> getSetPaciente();

    Set<Paciente> getFilteredPaciente(FilterObjectPaciente filterObjectPaciente);

    Set<Paciente> findAll();

    Optional<Paciente> findById(Long id);

    Optional<Paciente> findByNrUtenteSaude(String nr_utente_saude);

    Paciente save(Paciente paciente);

    ResponseEntity<Paciente> savePaciente (Paciente paciente);

    Optional<Paciente> removePaciente (String nrUtenteSaude);
}
