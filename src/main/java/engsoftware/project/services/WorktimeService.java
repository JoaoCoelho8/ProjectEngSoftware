package engsoftware.project.services;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;
import engsoftware.project.repositories.MedicoRepoI;
import engsoftware.project.repositories.WorkTimeRepoI;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WorktimeService implements WorkTimeServiceI {

    private WorkTimeRepoI workTimeRepoI;

    private MedicoRepoI medicoRepoI;

    public WorktimeService(WorkTimeRepoI workTimeRepoI, MedicoRepoI medicoRepoI) {
        this.workTimeRepoI = workTimeRepoI;
        this.medicoRepoI = medicoRepoI;
    }

    @Override
    public Set<WorkTime> getSetWorkTime() {
        Set<WorkTime> workTimes = new HashSet<>();
        for (WorkTime workTime : this.workTimeRepoI.findAll()) {
            workTimes.add(workTime);
        }
        return workTimes;
    }

    @Override
    public Set<WorkTime> findAll() {

        Set<WorkTime> workTimes = new HashSet<>();
        for (WorkTime workTime : this.workTimeRepoI.findAll()) {
            workTimes.add(workTime);
        }
        return Collections.unmodifiableSet(workTimes);
    }

    @Override
    public Optional<WorkTime> findById(Long id) {
        return this.workTimeRepoI.findById(id);
    }

    @Override
    public Iterable<WorkTime> findByDay(DayOfWeek dayOfWeek) {
        return this.workTimeRepoI.findAllByDay(dayOfWeek);
    }

    @Override
    public Iterable<WorkTime> findByMedico(String nameMedico) {
        Optional<Medico> medicoOptional = this.medicoRepoI.findByNome(nameMedico);
        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();
            return medico.getWorkTimes();
        }
        return null;
    }

    @Override
    public WorkTime save(WorkTime workTime) {
        return this.workTimeRepoI.save(workTime);
    }

    @Override
    public Optional<WorkTime> saveWorkTime(WorkTime workTime, String nameMedico) {
        Optional<Medico> medicoOptional = this.medicoRepoI.findByNome(nameMedico);
        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();

            medico.addWorkTimeToMedico(workTime);
            medicoRepoI.save(medico);
            return Optional.of(workTime);
        }
        return Optional.empty();
    }

    @Override
    public Optional<WorkTime> removeWorktime(Long id) {
        Optional<WorkTime> workTimeOptional = this.workTimeRepoI.findById(id);
        if (workTimeOptional.isPresent()) {
            WorkTime workTime = workTimeOptional.get();

            workTimeRepoI.delete(workTime);
            return workTimeRepoI.findById(id);
        }
        return Optional.empty();

    }
}
