package engsoftware.project.services;

import engsoftware.project.models.Medico;
import engsoftware.project.repositories.MedicoRepoI;
import engsoftware.project.services.filters.medico.FilterObjectMedico;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MedicoService implements MedicoServiceI {

    private MedicoRepoI medicoRepoI;

    private MedicoFilterService medicoFilterService;

    public MedicoService(MedicoRepoI medicoRepoI, MedicoFilterService medicoFilterService) {
        this.medicoRepoI = medicoRepoI;
        this.medicoFilterService = medicoFilterService;
    }

    @Override
    public Set<Medico> getSetMedico() {
        Set<Medico> medicos = new HashSet<>();
        for (Medico medico : this.medicoRepoI.findAll()) {
            medicos.add(medico);
        }
        return medicos;
    }

    @Override
    public Set<Medico> getFilteredMedico(FilterObjectMedico filterObjectMedico) {
        return medicoFilterService.filterMedicos(findAll(), filterObjectMedico);
    }

    @Override
    public Set<Medico> findAll() {
        Set<Medico> medicos = new HashSet<>();
        for (Medico medico : this.medicoRepoI.findAll()) {
            medicos.add(medico);
        }
        return Collections.unmodifiableSet(medicos);
    }

    @Override
    public Optional<Medico> findByName(String nameMedico) {
        return medicoRepoI.findByNome(nameMedico);
    }

    @Override
    public Optional<Medico> findById(Long id) {

        return medicoRepoI.findById(id);
    }

    @Override
    public Medico save(Medico medico) {
        return this.medicoRepoI.save(medico);
    }


    @Override
    public ResponseEntity<Medico> saveMedico(Medico medico) {
        medicoRepoI.save(medico);
        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Medico> removeMedico(String nameMedico) {
        Optional<Medico> medicoOptional = this.medicoRepoI.findByNome(nameMedico);
        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();

            medicoRepoI.delete(medico);
            return medicoRepoI.findByNome(medico.getNome());
        }
        return Optional.empty();

    }
}
