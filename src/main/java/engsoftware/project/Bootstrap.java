package engsoftware.project;

import engsoftware.project.models.*;
import engsoftware.project.repositories.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private EspecialidadeRepoI especialidadeRepoI;

    private WorkTimeRepoI workTimeRepoI;

    private MedicoRepoI medicoRepoI;

    private ConsultaRepoI consultaRepoI;

    private PacienteRepoI pacienteRepoI;

    public Bootstrap(EspecialidadeRepoI especialidadeRepoI, WorkTimeRepoI workTimeRepoI, MedicoRepoI medicoRepoI, ConsultaRepoI consultaRepoI, PacienteRepoI pacienteRepoI) {
        this.especialidadeRepoI = especialidadeRepoI;
        this.workTimeRepoI = workTimeRepoI;
        this.medicoRepoI = medicoRepoI;
        this.consultaRepoI = consultaRepoI;
        this.pacienteRepoI = pacienteRepoI;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Set<Especialidade> especialidade1 = new HashSet<>();
        Especialidade dentista = new Especialidade("Dentista", 35);
        especialidade1.add(dentista);
        Set<Especialidade> especialidade2 = new HashSet<>();
        Especialidade geral = new Especialidade("Geral", 20);
        especialidade2.add(geral);
        Set<Especialidade> especialidade3 = new HashSet<>();
        Especialidade cardiologista = new Especialidade("Cardiologista", 50);
        especialidade3.add(cardiologista);

        Set<Medico> medicos = new HashSet<>();
        Medico medico1 = new Medico("Luis Pessoa", "luis@gmail.com", "915345678", dentista);
        Medico medico2 = new Medico("Pedro Silva", "pedro@gmail.com", "915765432", geral);
        Medico medico3 = new Medico("Mario Andrade", "mario@gmail.com", "957654321", cardiologista);

        medicos.add(medico1);
        medicos.add(medico2);
        medicos.add(medico3);

        medico1.setEspecialidades(especialidade1);
        medico1.addEspecialidadeeToMedico(dentista);
        medico2.setEspecialidades(especialidade2);
        medico2.addEspecialidadeeToMedico(geral);
        medico3.setEspecialidades(especialidade3);
        medico3.addEspecialidadeeToMedico(cardiologista);

        Set<WorkTime> workTimes1 = new HashSet<>();
        WorkTime workTime1 = new WorkTime();
        workTime1.setDay(DayOfWeek.MONDAY);
        workTime1.setStart(LocalTime.of(9, 30));
        workTime1.setEnd(LocalTime.of(16, 0));
        workTimes1.add(workTime1);
        WorkTime workTime2 = new WorkTime();
        workTime2.setDay(DayOfWeek.WEDNESDAY);
        workTime2.setStart(LocalTime.of(13, 20));
        workTime2.setEnd(LocalTime.of(22, 0));
        workTimes1.add(workTime2);
        medico1.setWorkTimes(workTimes1);
        medico1.addWorkTimeToMedico(workTime1);
        medico1.addWorkTimeToMedico(workTime2);

        Set<WorkTime> workTimes2 = new HashSet<>();
        WorkTime workTime3 = new WorkTime();
        workTime3.setDay(DayOfWeek.TUESDAY);
        workTime3.setStart(LocalTime.of(11, 30));
        workTime3.setEnd(LocalTime.of(17, 15));
        workTimes2.add(workTime3);
        WorkTime workTime4 = new WorkTime();
        workTime4.setDay(DayOfWeek.THURSDAY);
        workTime4.setStart(LocalTime.of(6, 0));
        workTime4.setEnd(LocalTime.of(14, 0));
        workTimes2.add(workTime4);
        medico2.setWorkTimes(workTimes2);
        medico2.addWorkTimeToMedico(workTime3);
        medico2.addWorkTimeToMedico(workTime4);

        Set<WorkTime> workTimes3 = new HashSet<>();
        WorkTime workTime5 = new WorkTime();
        workTime5.setDay(DayOfWeek.FRIDAY);
        workTime5.setStart(LocalTime.of(12, 30));
        workTime5.setEnd(LocalTime.of(18, 0));
        workTimes3.add(workTime5);
        WorkTime workTime6 = new WorkTime();
        workTime6.setDay(DayOfWeek.SATURDAY);
        workTime6.setStart(LocalTime.of(7, 20));
        workTime6.setEnd(LocalTime.of(12, 30));
        workTimes3.add(workTime6);
        medico3.setWorkTimes(workTimes3);
        medico3.addWorkTimeToMedico(workTime5);
        medico3.addWorkTimeToMedico(workTime6);

        Set<Paciente> pacientes = new HashSet<>();
        Paciente paciente1 = new Paciente("Ana", 21, "234567890", true, "123456789");
        Paciente paciente2 = new Paciente("Luis", 71, "224365879", true, "180333111");
        Paciente paciente3 = new Paciente("Rafael", 35, "214365879", false, "189333111");
        Paciente paciente4 = new Paciente("Rui", 50, "264365879", false, "199333111");

        pacientes.add(paciente1);
        pacientes.add(paciente2);
        pacientes.add(paciente3);
        pacientes.add(paciente4);

        Set<Consulta> consultas1 = new HashSet<>();
        Set<Consulta> consultas2 = new HashSet<>();
        Set<Consulta> consultas3 = new HashSet<>();
        Consulta consulta1 = new Consulta();
        consulta1.setHorario(LocalDateTime.of(2019, 1, 21, 10, 0));
        consulta1.setMedico(medico1);
        consulta1.setPaciente(paciente1);
        consulta1.setTipo(medico1.getEspecialidadeMedico());
        Consulta consulta2 = new Consulta();
        consulta2.setHorario(LocalDateTime.of(2019, 1, 22, 12, 0));
        consulta2.setMedico(medico2);
        consulta2.setPaciente(paciente3);
        consulta2.setTipo(medico2.getEspecialidadeMedico());
        Consulta consulta3 = new Consulta();
        consulta3.setHorario(LocalDateTime.of(2019, 1, 25, 13, 0));
        consulta3.setMedico(medico3);
        consulta3.setPaciente(paciente2);
        consulta3.setTipo(medico3.getEspecialidadeMedico());

        consultas1.add(consulta1);
        consultas2.add(consulta2);
        consultas3.add(consulta3);

        medico1.setConsultas(consultas1);
        medico1.addConsutaToMedico(consulta1);

        medico2.setConsultas(consultas2);
        medico2.addConsutaToMedico(consulta2);

        medico3.setConsultas(consultas3);
        medico3.addConsutaToMedico(consulta3);

        medicoRepoI.saveAll(medicos);

        especialidadeRepoI.saveAll(especialidade1);
        especialidadeRepoI.saveAll(especialidade2);
        especialidadeRepoI.saveAll(especialidade3);

        workTimeRepoI.saveAll(workTimes1);
        workTimeRepoI.saveAll(workTimes2);
        workTimeRepoI.saveAll(workTimes3);

        pacienteRepoI.saveAll(pacientes);

        consultaRepoI.save(consulta1);
        consultaRepoI.save(consulta2);
        consultaRepoI.save(consulta3);

    }
}
