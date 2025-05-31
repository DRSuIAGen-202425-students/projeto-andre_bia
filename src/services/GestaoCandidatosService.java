package services;

import model.Candidato;
import repository.CandidatoRepository;

public class GestaoCandidatosService {

    private final CandidatoRepository candidatoRepository;

    public GestaoCandidatosService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    public void inserir(String nome, String partido) {
        int novoId = candidatoRepository.gerarNovoId();
        Candidato candidato = new Candidato(novoId, nome, partido);
        candidatoRepository.adicionarCandidato(candidato);
        System.out.println("✅ Candidato adicionado com sucesso.");
    }

    public void editar(int id, String novoNome, String novoPartido) {
        Candidato candidato = candidatoRepository.getCandidatoPorId(id);
        if (candidato != null) {
            candidato.setNome(novoNome);
            candidato.setPartido(novoPartido);
            candidatoRepository.atualizarCandidato(candidato);
            System.out.println("✅ Candidato atualizado com sucesso.");
        } else {
            System.out.println("❌ Candidato não encontrado.");
        }
    }

    public void remover(int id) {
        Candidato candidato = candidatoRepository.getCandidatoPorId(id);
        if (candidato != null) {
            candidatoRepository.removerCandidato(id);
            System.out.println("✅ Candidato removido com sucesso.");
        } else {
            System.out.println("❌ Candidato não encontrado.");
        }
    }
}
