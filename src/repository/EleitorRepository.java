package repository;

import model.Eleitor;

import java.util.ArrayList;
import java.util.List;

public class EleitorRepository {

    private List<Eleitor> eleitores;

    public EleitorRepository() {
        this.eleitores = new ArrayList<>();
    }

    public void adicionarEleitor(Eleitor eleitor) {
        eleitores.add(eleitor);
    }

    public boolean atualizarEleitor(Eleitor eleitorAtualizado) {
        for (int i = 0; i < eleitores.size(); i++) {
            if (eleitores.get(i).getId() == eleitorAtualizado.getId()) {
                eleitores.set(i, eleitorAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean removerEleitorPorId(int id) {
        return eleitores.removeIf(e -> e.getId() == id);
    }

    public List<Eleitor> listarTodos() {
        return new ArrayList<>(eleitores);
    }

    public Eleitor encontrarPorUsername(String username) {
        for (Eleitor e : eleitores) {
            if (e.getUsername().equals(username)) {
                return e;
            }
        }
        return null;
    }

    public Eleitor encontrarPorId(int id) {
        for (Eleitor e : eleitores) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
