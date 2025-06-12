package repository;

import model.Utilizador;
import util.FileStorageUtil; // Assumindo que tens esta classe

import java.util.ArrayList;
import java.util.List;

public class UtilizadorRepository {

    private final String ficheiro = "utilizadores.dat";
    private List<Utilizador> utilizadores;
    private int ultimoId = 0;

    public UtilizadorRepository() {
        List<Utilizador> dadosCarregados = FileStorageUtil.carregar(ficheiro);
        if (dadosCarregados != null) {
            utilizadores = dadosCarregados;
            for (Utilizador u : utilizadores) {
                if (u.getId() > ultimoId) {
                    ultimoId = u.getId();
                }
            }
        } else {
            utilizadores = new ArrayList<>();
        }
    }

    public void adicionarUtilizador(Utilizador utilizador) {
        utilizadores.add(utilizador);
        if (utilizador.getId() > ultimoId) {
            ultimoId = utilizador.getId();
        }
        salvar();
    }

    public void editarUtilizador(Utilizador utilizadorAtualizado) {
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getId() == utilizadorAtualizado.getId()) {
                utilizadores.set(i, utilizadorAtualizado);
                salvar();
                return;
            }
        }
    }

    public void removerUtilizador(int id) {
        utilizadores.removeIf(u -> u.getId() == id);
        salvar();
    }

    public Utilizador login(String username, String password) {
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public Utilizador getUtilizadorPorId(int id) {
        for (Utilizador u : utilizadores) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public List<Utilizador> listarUtilizadores() {
        return new ArrayList<>(utilizadores);
    }

    public int gerarNovoId() {
        return ++ultimoId;
    }

    public void limparTodos() {
        utilizadores.clear();
        ultimoId = 0;
        salvar();
    }

    public Utilizador encontrarPorUsername(String username) {
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    private void salvar() {
        FileStorageUtil.guardar(ficheiro, utilizadores);
    }
}
