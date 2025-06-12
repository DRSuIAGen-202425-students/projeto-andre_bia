package model;

import java.io.Serializable;

public class Administrador extends Utilizador implements Serializable {

    private static final long serialVersionUID = 1L;

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
