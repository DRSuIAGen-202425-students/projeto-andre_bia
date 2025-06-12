package repository;

import model.Eleitor;
import util.FileStorageUtil;  // Assumindo que esta é a classe que tens para guardar/carregar

import java.util.ArrayList;
import java.util.List;

public class EleitorRepository {

    private final String ficheiro = "eleitores.dat";
    private List<Eleitor> eleitores;
    private int ultimoId = 0;

    public EleitorRepository() {
        // Carrega a lista do ficheiro, ou cria nova se não existir
        List<Eleitor> dadosCarregados = FileStorageUtil.carregar(ficheiro);
        if (dadosCarregados != null) {
            eleitores = dadosCarregados;
            // Atualiza ultimoId para o maior id presente
            for (Eleitor e : eleitores) {
                if (e.getId() > ultimoId) {
                    ultimoId = e.getId();
                }
            }
        } else {
            eleitores = new ArrayList<>();
        }
    }

    public void adicionarEleitor(Eleitor eleitor) {
        eleitores.add(eleitor);
        if (eleitor.getId() > ultimoId) {
            ultimoId = eleitor.getId();
        }
        salvar();
    }

    public void atualizarEleitor(Eleitor eleitorAtualizado) {
        for (int i = 0; i < eleitores.size(); i++) {
            if (eleitores.get(i).getId() == eleitorAtualizado.getId()) {
                eleitores.set(i, eleitorAtualizado);
                salvar();
                return;
            }
        }
    }

    public void removerEleitor(int id) {
        eleitores.removeIf(e -> e.getId() == id);
        salvar();
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
        salvar();
    }

    public List<Eleitor> getTodosEleitores() {
        return new ArrayList<>(eleitores);
    }

    private void salvar() {
        FileStorageUtil.guardar(ficheiro, eleitores);
    }
}
