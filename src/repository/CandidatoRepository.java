package repository;

import model.Candidato;
import util.FileStorageUtil;

import java.util.ArrayList;
import java.util.List;

public class CandidatoRepository {

    private List<Candidato> candidatos;
    private int ultimoId;
    private final String ficheiro = "candidatos.dat";

    public CandidatoRepository() {
        this.candidatos = FileStorageUtil.carregar(ficheiro);
        if (candidatos == null) {
            this.candidatos = new ArrayList<>();
            this.ultimoId = 0;
        } else {
            this.ultimoId = candidatos.stream()
                    .mapToInt(Candidato::getId)
                    .max()
                    .orElse(0);
        }
    }

    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
        if (candidato.getId() > ultimoId) {
            ultimoId = candidato.getId();
        }
        guardar();
    }

    public void atualizarCandidato(Candidato candidatoAtualizado) {
        for (int i = 0; i < candidatos.size(); i++) {
            if (candidatos.get(i).getId() == candidatoAtualizado.getId()) {
                candidatos.set(i, candidatoAtualizado);
                guardar();
                return;
            }
        }
    }

    public void removerCandidato(int id) {
        candidatos.removeIf(c -> c.getId() == id);
        guardar();
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
        guardar();
    }

    private void guardar() {
        FileStorageUtil.guardar(ficheiro, candidatos);
    }
}