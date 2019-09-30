package engsoftware.project.services.filters.paciente;

import lombok.Data;

@Data
public class FilterObjectPaciente {
    private String nome;
    private String nrUtenteSaude;
    private boolean problematico;
}
