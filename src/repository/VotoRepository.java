package repository;

import model.Voto;

import java.util.ArrayList;
import java.util.List;

public class VotoRepository {

    private List<Voto> votos;

    public VotoRepository() {
        this.votos = new ArrayList<>();
    }

    /**
     * Regista um novo voto.
     *
     * @param voto voto a registar
     */
    public void adicionarVoto(Voto voto) {
        votos.add(voto);
    }

    /**
     * Verifica se um eleitor já votou.
     *
     * @param idEleitor ID do eleitor
     * @return true se já votou, false caso contrário
     */
    public boolean eleitorJaVotou(int idEleitor) {
        return votos.stream().anyMatch(v -> v.getIdEleitor() == idEleitor);
    }

    /**
     * Lista todos os votos.
     *
     * @return lista de votos
     */
    public List<Voto> listarTodos() {
        return new ArrayList<>(votos);
    }
}
