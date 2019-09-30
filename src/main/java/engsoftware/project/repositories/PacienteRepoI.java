package engsoftware.project.repositories;

import engsoftware.project.models.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PacienteRepoI extends CrudRepository<Paciente, Long> {

    Optional<Paciente> findByNrUtenteSaude(String nrUtenteSaude);

}
