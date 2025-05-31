package services;

import model.Candidato;
import repository.CandidatoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResultadosService {

    private final CandidatoRepository candidatoRepository;

    public ResultadosService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    public void mostrarResultados() {
        List<Candidato> candidatos = candidatoRepository.listarCandidatos();
        int totalVotos = calcularTotalVotos(candidatos);

        if (totalVotos == 0) {
            System.out.println("⚠️ Nenhum voto foi registado.");
            return;
        }

        System.out.println("\n📊 Resultados da Votação:");
        for (Candidato c : candidatos) {
            int votos = c.getNumeroVotos();
            double percentagem = (votos * 100.0) / totalVotos;
            System.out.printf("🧑 %s (%s): %d votos (%.2f%%)%n", c.getNome(), c.getPartido(), votos, percentagem);
        }

        Candidato vencedor = determinarVencedor(candidatos);
        System.out.printf("\n🏆 Vencedor: %s (%s) com %d votos.%n",
                vencedor.getNome(), vencedor.getPartido(), vencedor.getNumeroVotos());
    }

    private int calcularTotalVotos(List<Candidato> candidatos) {
        return candidatos.stream()
                .mapToInt(Candidato::getNumeroVotos)
                .sum();
    }

    private Candidato determinarVencedor(List<Candidato> candidatos) {
        return candidatos.stream()
                .max(Comparator.comparingInt(Candidato::getNumeroVotos))
                .orElse(null);
    }

    public List<String> gerarResumoResultados() {
        List<Candidato> candidatos = candidatoRepository.listarCandidatos();
        List<String> resultado = new ArrayList<>();
        int totalVotos = candidatos.stream().mapToInt(Candidato::getNumeroVotos).sum();

        candidatos.sort(Comparator.comparingInt(Candidato::getNumeroVotos).reversed());

        for (Candidato c : candidatos) {
            double percentagem = totalVotos == 0 ? 0 : (c.getNumeroVotos() * 100.0) / totalVotos;
            resultado.add(c.getNome() + " - " + c.getNumeroVotos() + " votos (" + String.format("%.2f", percentagem) + "%)");
        }

        if (!candidatos.isEmpty()) {
            Candidato vencedor = candidatos.get(0);
            resultado.add("🏆 Vencedor: " + vencedor.getNome());
        }

        return resultado;
    }
}
