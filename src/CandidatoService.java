import java.util.*;

public class CandidatoService {
    private Map<Integer, Candidato> candidatos = new HashMap<>();

    public void adicionarCandidato(Candidato candidato) {
        candidatos.put(candidato.getNumero(), candidato);
    }

    public void removerCandidato(int numero) {
        candidatos.remove(numero);
    }

    public List<Candidato> listarCandidatos() {
        return new ArrayList<>(candidatos.values());
    }
}
