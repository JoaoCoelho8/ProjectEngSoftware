package engsoftware.project.services.filters.medico;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class MedicoHoraFimFilter implements MedicoFilter {

    private LocalTime horaFimToFilter;

    public MedicoHoraFimFilter(LocalTime horaFimToFilter) {
        this.horaFimToFilter = horaFimToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if (horaFimToFilter == null) return medicos;

        Set<Medico> filteredMedicos = new HashSet<>();
        for (Medico medico : medicos) {
            for (WorkTime workTime : medico.getWorkTimes()) {
                if (workTime.getEnd().isBefore(this.horaFimToFilter)) {
                    filteredMedicos.add(medico);
                }
            }
        }
        return filteredMedicos;
    }

}
