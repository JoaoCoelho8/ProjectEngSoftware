package engsoftware.project.controllers;

import engsoftware.project.models.*;
import engsoftware.project.services.WorktimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/worktime")
public class WorkTimeController {

    private WorktimeService worktimeService;

    private Logger logger = LoggerFactory.getLogger(ConsultaController.class);

    public WorkTimeController(WorktimeService worktimeService) {
        this.worktimeService = worktimeService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<WorkTime> getAllWorkTime() {
        return worktimeService.findAll();
    }

    @RequestMapping(value = "/{nameMedico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<WorkTime> getByMedico(@PathVariable("nameMedico") String nameMedico) {
        return worktimeService.findByMedico(nameMedico);
    }


    @PostMapping(value = "/{nameMedico}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkTime> saveWorkTime(@RequestBody WorkTime workTime, @PathVariable("nameMedico") String nameMedico) {
        logger.info(workTime.toString() + " " + nameMedico);
        Optional<WorkTime> workTimeOptional = worktimeService.saveWorkTime(workTime, nameMedico);
        if (workTimeOptional.isPresent()) {
            return ResponseEntity.ok(workTimeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //remove consuta
    @PostMapping(value = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkTime> removeWorktime(@PathVariable("id") Long id) {
        Optional<WorkTime> workTimeOptional = worktimeService.removeWorktime(id);
        if (workTimeOptional.isPresent()) {
            return ResponseEntity.ok(workTimeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
