package repository;

import model.Utilizador;

import java.util.ArrayList;
import java.util.List;

public class UtilizadorRepository {

    private final List<Utilizador> utilizadores = new ArrayList<>();
    private int ultimoId = 0;

    public void adicionarUtilizador(Utilizador utilizador) {
        utilizadores.add(utilizador);
        if (utilizador.getId() > ultimoId) {
            ultimoId = utilizador.getId();
        }
    }

    public void editarUtilizador(Utilizador utilizadorAtualizado) {
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getId() == utilizadorAtualizado.getId()) {
                utilizadores.set(i, utilizadorAtualizado);
                return;
            }
        }
    }

    public void removerUtilizador(int id) {
        utilizadores.removeIf(u -> u.getId() == id);
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
    }

    public Utilizador encontrarPorUsername(String username) {
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
