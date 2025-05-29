import java.util.HashMap;
import java.util.Map;

public class AutenticacaoService {
    private Map<String, String> credenciais = new HashMap<>();

    public AutenticacaoService() {
        // Dados de exemplo
        credenciais.put("admin", "admin123");
        credenciais.put("eleitor1", "senha123");
        credenciais.put("eleitor2", "senha456");
    }

    public boolean login(String username, String password) {
        return credenciais.containsKey(username) && credenciais.get(username).equals(password);
    }
}
