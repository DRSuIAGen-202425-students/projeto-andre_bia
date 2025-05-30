package services;

import model.Candidato;
import repository.CandidatoRepository;

public class GestaoCandidatosService {

    private CandidatoRepository candidatoRepository;

    public GestaoCandidatosService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    public void inserirCandidato(Candidato candidato) {
        candidatoRepository.adicionarCandidato(candidato);
        System.out.println("✅ Candidato inserido com sucesso.");
    }

    public void editarCandidato(Candidato candidatoAtualizado) {
        boolean sucesso = candidatoRepository.atualizarCandidato(candidatoAtualizado);
        if (sucesso) {
            System.out.println("✅ Candidato atualizado com sucesso.");
        } else {
            System.out.println("❌ Candidato não encontrado para edição.");
        }
    }

    public void removerCandidato(int id) {
        boolean sucesso = candidatoRepository.removerCandidatoPorId(id);
        if (sucesso) {
            System.out.println("✅ Candidato removido com sucesso.");
        } else {
            System.out.println("❌ Candidato não encontrado para remoção.");
        }
    }
}
