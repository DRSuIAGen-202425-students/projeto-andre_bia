import java.util.*;

public class VotacaoService {
    private Map<String, Integer> votosPorEleitor = new HashMap<>();

    public boolean votar(Eleitor eleitor, int numeroCandidato) {
        if (eleitor.isVotou()) return false;

        votosPorEleitor.put(eleitor.getUsername(), numeroCandidato);
        eleitor.setVotou(true);
        return true;
    }
}
