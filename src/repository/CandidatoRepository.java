package repository;

import model.Candidato;

import java.util.ArrayList;
import java.util.List;

public class CandidatoRepository {

    private List<Candidato> candidatos;

    public CandidatoRepository() {
        this.candidatos = new ArrayList<>();
    }

    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    public boolean atualizarCandidato(Candidato candidatoAtualizado) {
        for (int i = 0; i < candidatos.size(); i++) {
            if (candidatos.get(i).getId() == candidatoAtualizado.getId()) {
                candidatos.set(i, candidatoAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean removerCandidatoPorId(int id) {
        return candidatos.removeIf(c -> c.getId() == id);
    }

    public List<Candidato> listarTodos() {
        return new ArrayList<>(candidatos);
    }

    public Candidato encontrarPorId(int id) {
        for (Candidato c : candidatos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
