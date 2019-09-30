package engsoftware.project.services;

import engsoftware.project.models.Medico;
import engsoftware.project.services.filters.AndFilter;
import engsoftware.project.services.filters.FilterI;
import engsoftware.project.services.filters.medico.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MedicoFilterService {

    public Set<Medico> filterMedicos(Set<Medico> medicos, FilterObjectMedico filterObjectMedico) {
        System.out.println(filterObjectMedico);
        FilterI<Medico> medicoNomeFilter = new MedicoNomeFilter(filterObjectMedico.getNome());
        FilterI<Medico> medicoEspecialidadeFilter = new MedicoEspecialidadeFilter(filterObjectMedico.getEspecialidade());
        FilterI<Medico> nomeAndEspecialidadeFilter = new AndFilter<>(medicoNomeFilter, medicoEspecialidadeFilter);
        FilterI<Medico> medicoHoraInicioFilter = new MedicoHoraInicioFilter(filterObjectMedico.getHoraInicio());
        FilterI<Medico> nomeAndEspecialidadeAndInicioFilter = new AndFilter<>(nomeAndEspecialidadeFilter, medicoHoraInicioFilter);
        FilterI<Medico> medicoHoraFimFilter = new MedicoHoraFimFilter(filterObjectMedico.getHoraFim());
        FilterI<Medico> nomeAndEspecialidadeAndInicioAndFimFilter = new AndFilter<>(nomeAndEspecialidadeAndInicioFilter, medicoHoraFimFilter);
        return nomeAndEspecialidadeAndInicioAndFimFilter.filter(medicos);
    }
}
