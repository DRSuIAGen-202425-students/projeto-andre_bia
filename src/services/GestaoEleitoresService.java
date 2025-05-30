package services;

import model.Eleitor;
import repository.EleitorRepository;

public class GestaoEleitoresService {

    private EleitorRepository eleitorRepository;

    public GestaoEleitoresService(EleitorRepository eleitorRepository) {
        this.eleitorRepository = eleitorRepository;
    }

    public void inserirEleitor(Eleitor eleitor) {
        eleitorRepository.adicionarEleitor(eleitor);
        System.out.println("✅ Eleitor inserido com sucesso.");
    }

    public void editarEleitor(Eleitor eleitorAtualizado) {
        boolean sucesso = eleitorRepository.atualizarEleitor(eleitorAtualizado);
        if (sucesso) {
            System.out.println("✅ Eleitor atualizado com sucesso.");
        } else {
            System.out.println("❌ Eleitor não encontrado para edição.");
        }
    }

    public void removerEleitor(int id) {
        boolean sucesso = eleitorRepository.removerEleitorPorId(id);
        if (sucesso) {
            System.out.println("✅ Eleitor removido com sucesso.");
        } else {
            System.out.println("❌ Eleitor não encontrado para remoção.");
        }
    }
}
