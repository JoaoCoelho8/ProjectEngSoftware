package engsoftware.project.services.filters.consulta;

import engsoftware.project.models.Consulta;
import engsoftware.project.services.filters.FilterI;

import java.util.Set;

public interface ConsultaFilter extends FilterI<Consulta> {
    Set<Consulta> filter(Set<Consulta> entities);
}
