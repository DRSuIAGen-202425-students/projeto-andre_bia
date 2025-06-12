package repository;

import model.Voto;
import util.FileStorageUtil;

import java.util.ArrayList;
import java.util.List;

public class VotoRepository {

    private final String ficheiro = "votos.dat";
    private List<Voto> votos;

    public VotoRepository() {
        List<Voto> dadosCarregados = FileStorageUtil.carregar(ficheiro);
        if (dadosCarregados != null) {
            votos = dadosCarregados;
        } else {
            votos = new ArrayList<>();
        }
    }

    /**
     * Regista um novo voto.
     *
     * @param voto voto a registar
     */
    public void adicionarVoto(Voto voto) {
        votos.add(voto);
        salvar();
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

    private void salvar() {
        FileStorageUtil.guardar(ficheiro, votos);
    }
}
