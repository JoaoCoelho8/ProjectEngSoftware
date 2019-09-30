package engsoftware.project.models;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MedicoTest {

    @Test
    public void testAddConsultaToMedico() {

        Especialidade dentista = new Especialidade("Dentista", 30);

        Medico medico1 = new Medico("Joao Coelho", "joao@gmail.com", "912345678", dentista);

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

        Consulta consulta1 = new Consulta();
        consulta1.setHorario(LocalDateTime.of(2019, 1, 21, 10, 0));

        Consulta consulta2 = new Consulta();
        consulta2.setHorario(LocalDateTime.of(2019, 1, 21, 10, 5));

        Consulta consulta3 = new Consulta();
        consulta3.setHorario(LocalDateTime.of(2019, 1, 22, 14, 0));

        assertEquals(0, medico1.getConsultas().size());
        medico1.addConsutaToMedico(consulta1);
        assertEquals(1, medico1.getConsultas().size());
        medico1.addConsutaToMedico(consulta2);
        assertEquals(1, medico1.getConsultas().size());
        medico1.addConsutaToMedico(consulta2);
        assertEquals(1, medico1.getConsultas().size());
    }
}
