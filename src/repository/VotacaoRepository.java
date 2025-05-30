package repository;

import model.Votacao;

public class VotacaoRepository {

    private Votacao votacao;

    public VotacaoRepository() {
        // Inicialmente, votação não está ativa e sem datas definidas
        this.votacao = new Votacao();
    }

    public Votacao getVotacao() {
        return votacao;
    }

    public void setVotacao(Votacao votacao) {
        this.votacao = votacao;
    }

    public boolean isVotacaoAtiva() {
        return votacao.isAtiva();
    }

    public void iniciarVotacao() {
        votacao.iniciarVotacao();
        votacao.setDataInicio(System.currentTimeMillis());
        votacao.setDataFim(null);
    }

    public void encerrarVotacao() {
        votacao.encerrarVotacao();
        votacao.setDataFim(System.currentTimeMillis());
    }
}
