package engsoftware.project.services.filters.consulta;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Paciente;

import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaNrUtenteFilter implements ConsultaFilter {

    private Paciente nrUtenteToFilter;

    public ConsultaNrUtenteFilter(Paciente nrUtenteToFilter) {
        this.nrUtenteToFilter = nrUtenteToFilter;
    }

    @Override
    public Set<Consulta> filter(Set<Consulta> consultas) {
        if (nrUtenteToFilter == null) return consultas;

        return consultas.stream()
                .filter(consulta -> consulta.getPaciente().equals(this.nrUtenteToFilter))
                .collect(Collectors.toSet());
    }
}
