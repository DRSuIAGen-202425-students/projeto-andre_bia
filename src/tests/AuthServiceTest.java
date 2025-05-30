// AuthServiceTest.java
package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import services.AutenticacaoService;
import model.Utilizador;
import model.Eleitor;

public class AuthServiceTest {

    @Test
    public void testLoginComCredenciaisValidas() {
        AutenticacaoService auth = new AutenticacaoService();
        Utilizador u = auth.login("eleitor1", "password123");
        assertNotNull(u);
        assertEquals("eleitor1", u.getUsername());
    }

    @Test
    public void testLoginComCredenciaisInvalidas() {
        AutenticacaoService auth = new AutenticacaoService();
        Utilizador u = auth.login("admin", "senhaErrada");
        assertNull(u);
    }
}
