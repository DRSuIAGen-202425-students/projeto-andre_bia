// VotoTest.java
package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import services.VotacaoService;
import model.Eleitor;
import model.Candidato;

public class VotoTest {

    @Test
    public void testVotarComSucesso() {
        VotacaoService svc = new VotacaoService();
        svc.iniciarVotacao();

        Eleitor e = new Eleitor("Joana", "joana", "senha");
        Candidato c = new Candidato("Carlos", "Partido X");

        boolean votou = svc.votar(e, c);
        assertTrue(votou);
    }

    @Test
    public void testVotarDuasVezesFalha() {
        VotacaoService svc = new VotacaoService();
        svc.iniciarVotacao();

        Eleitor e = new Eleitor("Pedro", "pedro", "senha");
        Candidato c = new Candidato("Ana", "Partido Y");

        assertTrue(svc.votar(e, c)); // primeiro voto
        assertFalse(svc.votar(e, c)); // tentativa de segundo voto
    }
}
