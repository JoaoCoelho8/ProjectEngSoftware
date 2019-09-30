package engsoftware.project.services.filters.medico;

import engsoftware.project.models.Medico;
import engsoftware.project.models.WorkTime;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class MedicoHoraInicioFilter implements MedicoFilter {

    private LocalTime horaInicioToFilter;

    public MedicoHoraInicioFilter(LocalTime horaInicioToFilter) {
        this.horaInicioToFilter = horaInicioToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if (horaInicioToFilter == null) return medicos;

        Set<Medico> filteredMedicos = new HashSet<>();
        for (Medico medico : medicos) {
            for (WorkTime workTime : medico.getWorkTimes()) {
                if (workTime.getEnd().isAfter(this.horaInicioToFilter)) {
                    filteredMedicos.add(medico);
                }
            }
        }
        return filteredMedicos;
    }
}
