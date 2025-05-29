import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutenticacaoServiceTest {

    private AutenticacaoService autenticacaoService;

    @BeforeEach
    void setUp() {
        autenticacaoService = new AutenticacaoService();
    }

    @Test
    void deveAutenticarAdministradorComCredenciaisValidas() {
        boolean autenticado = autenticacaoService.login("admin", "admin123");
        assertTrue(autenticado);
    }

    @Test
    void naoDeveAutenticarComCredenciaisInvalidas() {
        boolean autenticado = autenticacaoService.login("admin", "senhaErrada");
        assertFalse(autenticado);
    }

    @Test
    void deveAutenticarEleitorComCredenciaisValidas() {
        boolean autenticado = autenticacaoService.login("eleitor1", "senha123");
        assertTrue(autenticado);
    }
}
