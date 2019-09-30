package engsoftware.project.controllers;

import engsoftware.project.models.Medico;
import engsoftware.project.services.MedicoService;
import engsoftware.project.services.filters.medico.FilterObjectMedico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    private MedicoService medicoService;

    private Logger logger = LoggerFactory.getLogger(ConsultaController.class);

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Medico> getAllMedico(@ModelAttribute FilterObjectMedico filterObjectMedico) {
        logger.info(filterObjectMedico.toString());
        return medicoService.getFilteredMedico(filterObjectMedico);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Medico getById(@PathVariable("id") Long id) {
        Optional<Medico> courseDTOOptional = medicoService.findById(id);
        return courseDTOOptional.orElse(null);
    }


    @RequestMapping(value = "/nome/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Medico getByName(@PathVariable("name") String name) {
        return medicoService.findByName(name).get();
    }


    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> saveMedico(@RequestBody Medico medico) {
        return medicoService.saveMedico(medico);
    }


    @PostMapping(value = "/remove/{nameMedico}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> removeMedico(@PathVariable("nameMedico") String nameMedico) {
        Optional<Medico> medicoOptional = medicoService.removeMedico(nameMedico);
        if (medicoOptional.isPresent()) {
            return ResponseEntity.ok(medicoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
