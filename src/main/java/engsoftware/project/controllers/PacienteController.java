package engsoftware.project.controllers;

import engsoftware.project.models.Paciente;
import engsoftware.project.services.PacienteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;


    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Paciente> getAllPaciente() {
        return pacienteService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Paciente getById(@PathVariable("id") Long id) {
        return pacienteService.findById(id).get();
    }

    @RequestMapping(value = "/nrutente/{nrUtenteSaude}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Paciente getByNrUtente(@PathVariable("nrUtenteSaude") String nrUtenteSaude) {
        return pacienteService.findByNrUtenteSaude(nrUtenteSaude).get();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente) {
        //logger.info(paciente.toString()+" "+nrUtenteSaude);

        return pacienteService.savePaciente(paciente);
    }


    @PostMapping(value = "/remove/{nrUtenteSaude}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> removePaciente(@PathVariable("nrUtenteSaude") String nrUtenteSaude) {
        Optional<Paciente> pacienteOptional = pacienteService.removePaciente(nrUtenteSaude);
        if (pacienteOptional.isPresent()) {
            return ResponseEntity.ok(pacienteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
