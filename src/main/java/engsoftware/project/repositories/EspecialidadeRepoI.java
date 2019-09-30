package engsoftware.project.repositories;

import engsoftware.project.models.Especialidade;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EspecialidadeRepoI extends CrudRepository<Especialidade, Long> {

    Optional<Especialidade> findByNome(String nome);
}
