package model;

public class Administrador extends Utilizador {

    public Administrador(int id, String nome, String username, String password) {
        super(id, nome, username, password, "ADMINISTRADOR");
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}
