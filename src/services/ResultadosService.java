package services;

import model.Candidato;
import repository.CandidatoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultadosService {

    private CandidatoRepository candidatoRepository;

    public ResultadosService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    /**
     * Calcula o total de votos de todos os candidatos.
     *
     * @return total de votos
     */
    public int calcularTotalVotos() {
        int total = 0;
        for (Candidato c : candidatoRepository.listarTodos()) {
            total += c.getNumeroVotos();
        }
        return total;
    }

    /**
     * Calcula a percentagem de votos de cada candidato.
     *
     * @return mapa com candidato e respetiva percentagem
     */
    public Map<Candidato, Double> calcularPercentagens() {
        List<Candidato> candidatos = candidatoRepository.listarTodos();
        int totalVotos = calcularTotalVotos();

        Map<Candidato, Double> percentagens = new HashMap<>();
        if (totalVotos == 0) {
            // Sem votos ainda, percentagens a zero
            for (Candidato c : candidatos) {
                percentagens.put(c, 0.0);
            }
            return percentagens;
        }

        for (Candidato c : candidatos) {
            double percent = (c.getNumeroVotos() * 100.0) / totalVotos;
            percentagens.put(c, percent);
        }
        return percentagens;
    }

    /**
     * Determina o vencedor da votação (candidato com mais votos).
     *
     * @return candidato vencedor ou null se empate ou nenhum voto
     */
    public Candidato determinarVencedor() {
        List<Candidato> candidatos = candidatoRepository.listarTodos();

        if (candidatos.isEmpty()) {
            return null;
        }

        Candidato vencedor = null;
        int maxVotos = -1;
        boolean empate = false;

        for (Candidato c : candidatos) {
            if (c.getNumeroVotos() > maxVotos) {
                maxVotos = c.getNumeroVotos();
                vencedor = c;
                empate = false;
            } else if (c.getNumeroVotos() == maxVotos) {
                empate = true;
            }
        }

        return empate ? null : vencedor;
    }
}
