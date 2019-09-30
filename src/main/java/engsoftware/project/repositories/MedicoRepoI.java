package engsoftware.project.repositories;

import engsoftware.project.models.Medico;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MedicoRepoI extends CrudRepository<Medico, Long> {

    Optional<Medico> findByNome(String nome);
}
