package services;

import model.Eleitor;
import repository.EleitorRepository;
import repository.UtilizadorRepository;

public class GestaoEleitoresService {

    private final UtilizadorRepository utilizadorRepo;
    private final EleitorRepository eleitorRepo;

    public GestaoEleitoresService(UtilizadorRepository utilizadorRepo, EleitorRepository eleitorRepo) {
        this.utilizadorRepo = utilizadorRepo;
        this.eleitorRepo = eleitorRepo;
    }

    public void adicionarEleitor(String nome, String username, String password) {
        Eleitor novo = new Eleitor(utilizadorRepo.gerarNovoId(), nome, username, password);
        utilizadorRepo.adicionarUtilizador(novo);
        eleitorRepo.adicionarEleitor(novo);
        System.out.println("✅ Eleitor adicionado.");
    }

    public boolean editarEleitor(int id, String novoNome, String novaPassword) {
        Eleitor eleitor = eleitorRepo.getEleitorPorId(id);
        if (eleitor != null) {
            eleitor.setNome(novoNome);
            eleitor.setPassword(novaPassword);
            utilizadorRepo.editarUtilizador(eleitor);
            eleitorRepo.atualizarEleitor(eleitor);
            return true;
        }
        return false;
    }

    public void removerEleitor(int id) {
        eleitorRepo.removerEleitor(id);
        utilizadorRepo.removerUtilizador(id);
    }
}
