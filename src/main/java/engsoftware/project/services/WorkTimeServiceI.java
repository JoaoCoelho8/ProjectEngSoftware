package engsoftware.project.services;

import engsoftware.project.models.WorkTime;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

public interface WorkTimeServiceI {

    Set<WorkTime> getSetWorkTime();

    Iterable<WorkTime> findByDay(DayOfWeek dayOfWeek);

    Optional<WorkTime> findById(Long id);

    Iterable<WorkTime> findByMedico(String nameMedico);

    Set<WorkTime> findAll();

    WorkTime save(WorkTime workTime);

    Optional<WorkTime> saveWorkTime(WorkTime workTime, String nameMedico);

    Optional<WorkTime> removeWorktime (Long id);

}
