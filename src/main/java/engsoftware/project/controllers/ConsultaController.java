package engsoftware.project.controllers;

import engsoftware.project.models.Consulta;
import engsoftware.project.services.ConsultaService;
import engsoftware.project.services.filters.consulta.FilterObjectConsulta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    private Logger logger = LoggerFactory.getLogger(ConsultaController.class);

    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Consulta> getAllConsulta(@ModelAttribute FilterObjectConsulta filterObjectConsulta) {
        return consultaService.getFilteredConsulta(filterObjectConsulta);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Consulta getById(@PathVariable("id") Long id) {
        Optional<Consulta> courseDTOOptional = consultaService.findById(id);
        return courseDTOOptional.orElse(null);
    }

    @PostMapping(value = "/{nrUtenteSaude}/{nameMedico}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> saveConsulta(@RequestBody Consulta consulta, @PathVariable("nrUtenteSaude") String nrUtenteSaude, @PathVariable("nameMedico") String nameMedico) {
        logger.info(consulta.toString() + " " + nrUtenteSaude);
        Optional<Consulta> consultaOptional = consultaService.saveConsulta(consulta, nrUtenteSaude, nameMedico);
        if (consultaOptional.isPresent()) {
            return ResponseEntity.ok(consultaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //remove consuta
    @PostMapping(value = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> removeConsulta(@PathVariable("id") Long id) {
        Optional<Consulta> consultaOptional = consultaService.removeConsulta(id);
        if (consultaOptional.isPresent()) {
            return ResponseEntity.ok(consultaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
