package engsoftware.project.repositories;

import engsoftware.project.models.Consulta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConsultaRepoI extends CrudRepository<Consulta, Long> {

    Optional<Consulta> findById(long id);
}
