public class Candidato {
    private int id;
    private String nome;
    private String partido;
    private int numero;

    public Candidato(int id, String nome, String partido, int numero) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }
}
