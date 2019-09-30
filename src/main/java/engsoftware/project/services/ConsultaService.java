package engsoftware.project.services;


import engsoftware.project.models.Consulta;
import engsoftware.project.models.Medico;
import engsoftware.project.models.Paciente;
import engsoftware.project.repositories.ConsultaRepoI;
import engsoftware.project.repositories.MedicoRepoI;
import engsoftware.project.repositories.PacienteRepoI;
import engsoftware.project.services.filters.consulta.FilterObjectConsulta;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ConsultaService implements ConsultaServiceI {

    private ConsultaFilterService consultaFilterService;

    private ConsultaRepoI consultaRepoI;

    private MedicoRepoI medicoRepoI;

    private PacienteRepoI pacienteRepoI;

    public ConsultaService(ConsultaFilterService consultaFilterService, ConsultaRepoI consultaRepoI, MedicoRepoI medicoRepoI, PacienteRepoI pacienteRepoI) {
        this.consultaFilterService = consultaFilterService;
        this.consultaRepoI = consultaRepoI;
        this.medicoRepoI = medicoRepoI;
        this.pacienteRepoI = pacienteRepoI;
    }

    @Override
    public Set<Consulta> getSetConsulta() {
        Set<Consulta> consultas = new HashSet<>();
        for (Consulta consulta : this.consultaRepoI.findAll()) {
            consultas.add(consulta);
        }
        return consultas;
    }

    @Override
    public Set<Consulta> getFilteredConsulta(FilterObjectConsulta filterObjectConsulta) {
        return consultaFilterService.filterConsultas(findAll(), filterObjectConsulta);
    }


    @Override
    public Set<Consulta> findAll() {
        Set<Consulta> consultas = new HashSet<>();
        for (Consulta consulta : this.consultaRepoI.findAll()) {
            consultas.add(consulta);
        }
        return Collections.unmodifiableSet(consultas);
    }

    @Override
    public Optional<Medico> findByName(String nameMedico) {
        return medicoRepoI.findByNome(nameMedico);
    }

    @Override
    public Optional<Paciente> findByNrUtenteSaude(String nrUtenteSaude) {
        return pacienteRepoI.findByNrUtenteSaude(nrUtenteSaude);
    }

    @Override
    public Optional<Consulta> findById(Long id) {

        return consultaRepoI.findById(id);
    }

    @Override
    public Consulta save(Consulta consulta) {
        return this.consultaRepoI.save(consulta);
    }

    @Override
    public Optional<Consulta> saveConsulta(Consulta consulta, String nrUtenteSaude, String nameMedico) {
        Optional<Paciente> pacienteOptional = this.pacienteRepoI.findByNrUtenteSaude(nrUtenteSaude);
        Optional<Medico> medicoOptional = this.medicoRepoI.findByNome(nameMedico);
        if (pacienteOptional.isPresent() && medicoOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            Medico medico = medicoOptional.get();
            if (medico.addConsutaToMedico(consulta)) {
                paciente.addConsutaToPaciente(consulta);
                pacienteRepoI.save(paciente);
                medicoRepoI.save(medico);
                consultaRepoI.save(consulta);
                return consultaRepoI.findById(consulta.getId());
            }

        }
        return Optional.empty();
    }


    @Override
    public Optional<Consulta> removeConsulta(Long id) {
        Optional<Consulta> consultaOptional = this.consultaRepoI.findById(id);
        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();

            consultaRepoI.delete(consulta);
            return consultaRepoI.findById(id);
        }
        return Optional.empty();

    }
}

