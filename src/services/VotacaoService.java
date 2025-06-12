package services;

import model.Candidato;
import model.Eleitor;
import model.Votacao;
import model.Voto;
import repository.CandidatoRepository;
import repository.EleitorRepository;
import repository.VotoRepository;

public class VotacaoService {

    private Votacao votacao;
    private CandidatoRepository candidatoRepository;
    private EleitorRepository eleitorRepository;
    private VotoRepository votoRepository;
    private int contadorVotos = 0;

    public VotacaoService(Votacao votacao,
                          CandidatoRepository candidatoRepository,
                          EleitorRepository eleitorRepository,
                          VotoRepository votoRepository) {
        this.votacao = votacao;
        this.candidatoRepository = candidatoRepository;
        this.eleitorRepository = eleitorRepository;
        this.votoRepository = votoRepository;
    }

    public void iniciarVotacao() {
        if (!votacao.isAtiva()) {
            votacao.iniciarVotacao();
            System.out.println("✅ Votação iniciada com sucesso.");
        } else {
            System.out.println("⚠️ A votação já está ativa.");
        }
    }

    public void encerrarVotacao() {
        if (votacao.isAtiva()) {
            votacao.encerrarVotacao();

            for (Eleitor eleitor : eleitorRepository.listarEleitores()) {
                eleitor.setVotou(false);
            }

            System.out.println("🛑 Votação encerrada com sucesso e estado dos eleitores reiniciado.");
        } else {
            System.out.println("⚠️ A votação já está encerrada ou não foi iniciada.");
        }
    }

    public boolean isVotacaoAtiva() {
        return votacao.isAtiva();
    }

    public void votar(Eleitor eleitor, Candidato candidato) {

        if (!votacao.isAtiva()) {
            throw new IllegalStateException("⚠️ A votação não está ativa.");
        }

        if (eleitor.isVotou()) {
            throw new IllegalStateException("❌ Este eleitor já votou.");
        }

        if(candidato == null) {
            throw new NullPointerException("❌ Este candidato não existe.");
        }

        // Regista o voto
        candidato.incrementarVoto();
        eleitor.setVotou(true);

        // Atualiza repositórios
        eleitorRepository.atualizarEleitor(eleitor);
        candidatoRepository.atualizarCandidato(candidato);

        // Regista no histórico de votos
        Voto voto = new Voto(++contadorVotos, eleitor.getId(), candidato.getId());
        votoRepository.adicionarVoto(voto);

        System.out.println("🗳️ Voto registado com sucesso!");
    }
}
