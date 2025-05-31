package repository;

import model.Eleitor;

import java.util.ArrayList;
import java.util.List;

public class EleitorRepository {

    private final List<Eleitor> eleitores = new ArrayList<>();
    private int ultimoId = 0;

    public void adicionarEleitor(Eleitor eleitor) {
        eleitores.add(eleitor);
        if (eleitor.getId() > ultimoId) {
            ultimoId = eleitor.getId();
        }
    }

    public void atualizarEleitor(Eleitor eleitorAtualizado) {
        for (int i = 0; i < eleitores.size(); i++) {
            if (eleitores.get(i).getId() == eleitorAtualizado.getId()) {
                eleitores.set(i, eleitorAtualizado);
                return;
            }
        }
    }

    public void removerEleitor(int id) {
        eleitores.removeIf(e -> e.getId() == id);
    }

    public Eleitor getEleitorPorId(int id) {
        for (Eleitor e : eleitores) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Eleitor> listarEleitores() {
        return new ArrayList<>(eleitores);
    }

    public int gerarNovoId() {
        return ++ultimoId;
    }

    public Eleitor encontrarPorUsername(String username) {
        for (Eleitor e : eleitores) {
            if (e.getUsername().equals(username)) {
                return e;
            }
        }
        return null;
    }

    public void limparTodos() {
        eleitores.clear();
        ultimoId = 0;
    }
}
