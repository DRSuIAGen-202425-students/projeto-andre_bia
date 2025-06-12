package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idVoto;
    private int idEleitor;
    private int idCandidato;
    private LocalDateTime dataHora;

    public Voto(int idVoto, int idEleitor, int idCandidato) {
        this.idVoto = idVoto;
        this.idEleitor = idEleitor;
        this.idCandidato = idCandidato;
        this.dataHora = LocalDateTime.now();
    }

    public int getIdVoto() {
        return idVoto;
    }

    public int getIdEleitor() {
        return idEleitor;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public void setIdEleitor(int idEleitor) {
        this.idEleitor = idEleitor;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Voto{" +
                "idVoto=" + idVoto +
                ", idEleitor=" + idEleitor +
                ", idCandidato=" + idCandidato +
                ", dataHora=" + dataHora +
                '}';
    }
}
