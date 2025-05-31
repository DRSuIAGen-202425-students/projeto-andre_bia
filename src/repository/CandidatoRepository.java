package repository;

import model.Candidato;

import java.util.ArrayList;
import java.util.List;

public class CandidatoRepository {

    private final List<Candidato> candidatos = new ArrayList<>();
    private int ultimoId = 0;

    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
        if (candidato.getId() > ultimoId) {
            ultimoId = candidato.getId();
        }
    }

    public void atualizarCandidato(Candidato candidatoAtualizado) {
        for (int i = 0; i < candidatos.size(); i++) {
            if (candidatos.get(i).getId() == candidatoAtualizado.getId()) {
                candidatos.set(i, candidatoAtualizado);
                return;
            }
        }
    }

    public void removerCandidato(int id) {
        candidatos.removeIf(c -> c.getId() == id);
    }

    public Candidato getCandidatoPorId(int id) {
        for (Candidato c : candidatos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Candidato> listarCandidatos() {
        return new ArrayList<>(candidatos);
    }

    public int gerarNovoId() {
        return ++ultimoId;
    }

    public void limparTodos() {
        candidatos.clear();
        ultimoId = 0;
    }

}
