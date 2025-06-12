package model;

import java.io.Serializable;

public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String partido;
    private int numeroVotos;

    public Candidato(int id, String nome, String partido) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
        this.numeroVotos = 0; // inicia com 0 votos
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public void setNumeroVotos(int numeroVotos) {
        this.numeroVotos = numeroVotos;
    }

    public void incrementarVoto() {
        this.numeroVotos++;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", partido='" + partido + '\'' +
                ", numeroVotos=" + numeroVotos +
                '}';
    }

}
