package engsoftware.project.repositories;

import engsoftware.project.models.WorkTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;

@Repository
public interface WorkTimeRepoI extends CrudRepository<WorkTime, Long> {

    Iterable<WorkTime> findAllByDay(DayOfWeek dayOfWeek);
}
