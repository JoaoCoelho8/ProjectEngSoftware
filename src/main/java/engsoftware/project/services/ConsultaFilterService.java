package engsoftware.project.services;

import engsoftware.project.models.Consulta;
import engsoftware.project.services.filters.AndFilter;
import engsoftware.project.services.filters.consulta.*;
import engsoftware.project.services.filters.FilterI;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ConsultaFilterService {

    public Set<Consulta> filterConsultas(Set<Consulta> consultas, FilterObjectConsulta filterObject) {
        System.out.println(filterObject);
        FilterI<Consulta> consultaMedicoFilter = new ConsultaMedicoFilter(filterObject.getMedico());
        FilterI<Consulta> consultaNr_utenteFilter = new ConsultaNrUtenteFilter(filterObject.getNrUtente());
        FilterI<Consulta> medicoAndNr_utenteFilter = new AndFilter<>(consultaMedicoFilter, consultaNr_utenteFilter);
        FilterI<Consulta> consultaEspecialidadeFilter = new ConsultaEspecialidadeFilter(filterObject.getEspecialidade());
        FilterI<Consulta> medicoAndNr_utenteAndEspecialidadeFilter = new AndFilter<>(medicoAndNr_utenteFilter, consultaEspecialidadeFilter);
        FilterI<Consulta> consultaDiaFilter = new ConsultaDiaFilter(filterObject.getDia());
        FilterI<Consulta> medicoAndNr_utenteAndEspecialidadeAndDiaFilter = new AndFilter<>(medicoAndNr_utenteAndEspecialidadeFilter, consultaDiaFilter);
        FilterI<Consulta> consultaHoraFilter = new ConsultaHoraFilter(filterObject.getHora());
        FilterI<Consulta> medicoAndNr_utenteAndEspecialidadeAndDiaAndHoraFilter = new AndFilter<>(medicoAndNr_utenteAndEspecialidadeAndDiaFilter, consultaHoraFilter);
        return medicoAndNr_utenteAndEspecialidadeAndDiaAndHoraFilter.filter(consultas);
    }
}
