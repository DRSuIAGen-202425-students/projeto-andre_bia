package model;

import java.io.Serializable;

public class Eleitor extends Utilizador implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean votou;

    public Eleitor(int id, String nome, String username, String password) {
        super(id, nome, username, password, "ELEITOR");
        this.votou = false;
    }

    public boolean isVotou() {
        return votou;
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }

    @Override
    public String toString() {
        return "Eleitor{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", votou=" + votou +
                '}';
    }
}
