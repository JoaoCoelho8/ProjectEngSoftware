package engsoftware.project.services;


import engsoftware.project.models.Paciente;
import engsoftware.project.repositories.PacienteRepoI;
import engsoftware.project.services.filters.paciente.FilterObjectPaciente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements PacienteServiceI {

    private PacienteRepoI pacienteRepoI;

    private PacienteFilterService pacienteFilterService;

    public PacienteService(PacienteRepoI pacienteRepoI, PacienteFilterService pacienteFilterService) {
        this.pacienteRepoI = pacienteRepoI;
        this.pacienteFilterService = pacienteFilterService;
    }

    @Override
    public Set<Paciente> getSetPaciente() {
        Set<Paciente> pacientes = new HashSet<>();
        for (Paciente paciente : this.pacienteRepoI.findAll()) {
            pacientes.add(paciente);
        }
        return pacientes;
    }

    @Override
    public Set<Paciente> getFilteredPaciente(FilterObjectPaciente filterObjectPaciente) {
        return pacienteFilterService.filterPacientes(findAll(), filterObjectPaciente);
    }

    @Override
    public Set<Paciente> findAll() {
        Set<Paciente> pacientes = new HashSet<>();
        for (Paciente paciente : this.pacienteRepoI.findAll()) {
            pacientes.add(paciente);
        }
        return Collections.unmodifiableSet(pacientes);
    }

    @Override
    public Optional<Paciente> findByNrUtenteSaude(String nrUtenteSaude) {
        return pacienteRepoI.findByNrUtenteSaude(nrUtenteSaude);
    }

    @Override
    public Optional<Paciente> findById(Long id) {

        return pacienteRepoI.findById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return this.pacienteRepoI.save(paciente);
    }


    @Override
    public ResponseEntity<Paciente> savePaciente(Paciente paciente) {
        pacienteRepoI.save(paciente);
        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Paciente> removePaciente(String nrUtenteSaude) {
        Optional<Paciente> pacienteOptional = this.pacienteRepoI.findByNrUtenteSaude(nrUtenteSaude);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();

            pacienteRepoI.delete(paciente);
            return pacienteRepoI.findByNrUtenteSaude(nrUtenteSaude);
        }
        return Optional.empty();
    }
}
