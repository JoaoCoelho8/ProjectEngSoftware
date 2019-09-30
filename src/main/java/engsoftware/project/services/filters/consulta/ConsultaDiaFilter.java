package engsoftware.project.services.filters.consulta;

import engsoftware.project.models.Consulta;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaDiaFilter implements ConsultaFilter {

    private DayOfWeek diaToFilter;

    public ConsultaDiaFilter(DayOfWeek diaToFilter) {
        this.diaToFilter = diaToFilter;
    }

    @Override
    public Set<Consulta> filter(Set<Consulta> consultas) {
        if (diaToFilter == null) return consultas;

        return consultas.stream()
                .filter(consulta -> consulta.getHorario().getDayOfWeek().equals(this.diaToFilter))
                .collect(Collectors.toSet());
    }
}
