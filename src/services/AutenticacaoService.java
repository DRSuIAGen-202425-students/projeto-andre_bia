package services;

import model.Utilizador;
import repository.UtilizadorRepository;

public class AutenticacaoService {

    private UtilizadorRepository utilizadorRepository;

    public AutenticacaoService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    /**
     * Tenta autenticar um utilizador com o username e password fornecidos.
     *
     * @param username Nome de utilizador
     * @param password Palavra-passe
     * @return Utilizador autenticado, ou null se falhar
     */
    public Utilizador login(String username, String password) {
        Utilizador utilizador = utilizadorRepository.encontrarPorUsername(username);

        if (utilizador != null && utilizador.getPassword().equals(password)) {
            System.out.println("✅ Login bem-sucedido! Bem-vindo, " + utilizador.getNome());
            return utilizador;
        }

        System.out.println("❌ Credenciais inválidas.");
        return null;
    }
}
