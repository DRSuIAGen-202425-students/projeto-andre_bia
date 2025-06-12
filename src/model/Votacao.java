package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Votacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean ativa;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public Votacao() {
        this.ativa = false;
        this.dataInicio = null;
        this.dataFim = null;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void iniciarVotacao() {
        this.ativa = true;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = null;
    }

    public void encerrarVotacao() {
        this.ativa = false;
        this.dataFim = LocalDateTime.now();
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    @Override
    public String toString() {
        return "Votacao{" +
                "ativa=" + ativa +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }

}
