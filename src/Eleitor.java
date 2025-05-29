public class Eleitor {
    private String username;
    private String senha;
    private boolean votou;

    public Eleitor(String username, String senha, boolean votou) {
        this.username = username;
        this.senha = senha;
        this.votou = votou;
    }

    public boolean isVotou() {
        return votou;
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }

    public String getUsername() {
        return username;
    }
}
