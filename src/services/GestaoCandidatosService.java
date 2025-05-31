package services;

import model.Candidato;
import repository.CandidatoRepository;

import java.util.List;

public class GestaoCandidatosService {

    private final CandidatoRepository candidatoRepo;

    public GestaoCandidatosService(CandidatoRepository candidatoRepo) {
        this.candidatoRepo = candidatoRepo;
    }

    public void inserir(String nome, String partido) {
        int novoId = candidatoRepo.gerarNovoId();
        Candidato candidato = new Candidato(novoId, nome, partido);
        candidatoRepo.adicionarCandidato(candidato);
        System.out.println("✅ Candidato adicionado.");
    }

    public void editar(int id, String novoNome, String novoPartido) {
        Candidato candidato = candidatoRepo.getCandidatoPorId(id);
        if (candidato != null) {
            candidato.setNome(novoNome);
            candidato.setPartido(novoPartido);
            candidatoRepo.atualizarCandidato(candidato);
            System.out.println("✅ Candidato atualizado.");
        } else {
            System.out.println("❌ Candidato não encontrado.");
        }
    }

    public void remover(int id) {
        candidatoRepo.removerCandidato(id);
    }

    public List<Candidato> listarCandidatos() {
        return candidatoRepo.listarCandidatos();
    }

    public Candidato getCandidatoPorId(int id) {
        return candidatoRepo.getCandidatoPorId(id);
    }
}
