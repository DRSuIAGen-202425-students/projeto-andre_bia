import java.util.*;

public class Resultado {
    private Map<Integer, Integer> votos = new HashMap<>();

    public void adicionarVoto(int numeroCandidato, int quantidade) {
        votos.put(numeroCandidato, quantidade);
    }

    public int getVotos(int numeroCandidato) {
        return votos.getOrDefault(numeroCandidato, 0);
    }

    public int getVencedor() {
        return votos.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1); // Nenhum voto
    }
}
