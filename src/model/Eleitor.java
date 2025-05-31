package model;

public class Eleitor extends Utilizador {

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
