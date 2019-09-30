package engsoftware.project.services;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Medico;
import engsoftware.project.models.Paciente;
import engsoftware.project.services.filters.consulta.FilterObjectConsulta;

import java.util.Optional;
import java.util.Set;

public interface ConsultaServiceI {

    Set<Consulta> getSetConsulta();

    Set<Consulta> getFilteredConsulta(FilterObjectConsulta filterObjectConsulta);

    Set<Consulta> findAll();

    Optional<Consulta> findById(Long id);

    Optional<Medico> findByName(String nameMedico);

    Optional<Paciente> findByNrUtenteSaude(String nrUtenteSaude);

    Consulta save(Consulta consulta);

    Optional<Consulta> saveConsulta(Consulta consulta, String nrUtenteSaude, String nameMedico);

    Optional<Consulta> removeConsulta(Long id);

}
