package model;

import java.io.Serializable;

public abstract class Utilizador implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String username;
    private String password;
    private String tipo; // "ADMINISTRADOR" ou "ELEITOR"

    public Utilizador(int id, String nome, String username, String password, String tipo) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
