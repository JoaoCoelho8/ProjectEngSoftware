package engsoftware.project.services;

import engsoftware.project.models.Especialidade;
import engsoftware.project.models.Medico;
import engsoftware.project.repositories.EspecialidadeRepoI;
import engsoftware.project.repositories.MedicoRepoI;
import engsoftware.project.services.filters.especialidade.FilterObjectEspecialidade;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EspecialidadeService implements EspecialidadeServiceI {

    private EspecialidadeFilterService especialidadeFilterService;

    private EspecialidadeRepoI especialidadeRepoI;

    private MedicoRepoI medicoRepoI;

    public EspecialidadeService(EspecialidadeFilterService especialidadeFilterService, EspecialidadeRepoI especialidadeRepoI, MedicoRepoI medicoRepoI) {
        this.especialidadeFilterService = especialidadeFilterService;
        this.especialidadeRepoI = especialidadeRepoI;
        this.medicoRepoI = medicoRepoI;
    }

    @Override
    public Set<Especialidade> getSetEspecialidade() {
        Set<Especialidade> especialidades = new HashSet<>();
        for (Especialidade especialidade : this.especialidadeRepoI.findAll()) {
            especialidades.add(especialidade);
        }
        return especialidades;
    }

    @Override
    public Set<Especialidade> getFilteredEspecialidade(FilterObjectEspecialidade filterObjectEspecialidade) {
        return especialidadeFilterService.filterEspecialidade(findAll(), filterObjectEspecialidade);
    }


    @Override
    public Set<Especialidade> findAll() {

        Set<Especialidade> especialidades = new HashSet<>();
        for (Especialidade especialidade : this.especialidadeRepoI.findAll()) {
            especialidades.add(especialidade);
        }
        return Collections.unmodifiableSet(especialidades);
    }

    @Override
    public Optional<Especialidade> findById(Long id) {
        return this.especialidadeRepoI.findById(id);
    }

    @Override
    public Optional<Especialidade> findByName(String nameEspecialidade) {
        return this.especialidadeRepoI.findByNome(nameEspecialidade);
    }

    @Override
    public Optional<Medico> findByMedico(String nameMedico) {
        return medicoRepoI.findByNome(nameMedico);
    }

    @Override
    public Especialidade save(Especialidade especialidade) {
        return this.especialidadeRepoI.save(especialidade);
    }

    @Override
    public Optional<Especialidade> saveEspecialidade(Especialidade especialidade, String nameMedico) {
        Optional<Medico> medicoOptional = this.medicoRepoI.findByNome(nameMedico);
        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();

            medico.addEspecialidadeeToMedico(especialidade);
            medicoRepoI.save(medico);
            return especialidadeRepoI.findByNome(especialidade.getNome());
        }
        return Optional.of(especialidade);

    }

    @Override
    public Optional<Especialidade> removeEspecialidade(String nameEspecialidade) {
        Optional<Especialidade> especialidadeOptional = this.especialidadeRepoI.findByNome(nameEspecialidade);
        if (especialidadeOptional.isPresent()) {
            Especialidade especialidade = especialidadeOptional.get();

            Medico medico = especialidade.getMedico();
            medico.removeEspecialidadeToMedico(especialidade);
            medicoRepoI.save(medico);
            especialidadeRepoI.delete(especialidade);
            return especialidadeRepoI.findByNome(especialidade.getNome());
        }
        return Optional.empty();

    }

}
