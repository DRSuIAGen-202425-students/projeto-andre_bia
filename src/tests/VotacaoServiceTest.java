// VotacaoServiceTest.java
package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import services.VotacaoService;

public class VotacaoServiceTest {

    @Test
    public void testIniciarVotacao() {
        VotacaoService svc = new VotacaoService();
        svc.iniciarVotacao();
        assertTrue(svc.isVotacaoAtiva());
    }

    @Test
    public void testEncerrarVotacao() {
        VotacaoService svc = new VotacaoService();
        svc.iniciarVotacao();
        svc.encerrarVotacao();
        assertFalse(svc.isVotacaoAtiva());
    }
}
