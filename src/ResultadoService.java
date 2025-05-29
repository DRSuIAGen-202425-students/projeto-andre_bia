import java.util.*;

public class ResultadoService {
    private Map<Integer, Integer> votos = new HashMap<>();

    public void registrarVoto(int numeroCandidato) {
        votos.put(numeroCandidato, votos.getOrDefault(numeroCandidato, 0) + 1);
    }

    public Resultado calcularResultados() {
        Resultado resultado = new Resultado();
        for (Map.Entry<Integer, Integer> entry : votos.entrySet()) {
            resultado.adicionarVoto(entry.getKey(), entry.getValue());
        }
        return resultado;
    }
}
