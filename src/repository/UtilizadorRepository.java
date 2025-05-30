package repository;

import model.Utilizador;

import java.util.ArrayList;
import java.util.List;

public class UtilizadorRepository {

    private List<Utilizador> utilizadores;

    public UtilizadorRepository() {
        this.utilizadores = new ArrayList<>();
    }

    public void adicionarUtilizador(Utilizador utilizador) {
        utilizadores.add(utilizador);
    }

    public void editarUtilizador(Utilizador utilizadorAtualizado) {
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getId() == utilizadorAtualizado.getId()) {
                utilizadores.set(i, utilizadorAtualizado);
                return;
            }
        }
        System.out.println("❌ Utilizador não encontrado para edição.");
    }

    public void removerUtilizadorPorId(int id) {
        utilizadores.removeIf(u -> u.getId() == id);
    }

    public Utilizador encontrarPorUsername(String username) {
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public List<Utilizador> listarTodos() {
        return new ArrayList<>(utilizadores);
    }
}
