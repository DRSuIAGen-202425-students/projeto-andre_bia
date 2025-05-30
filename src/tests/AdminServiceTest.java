// AdminServiceTest.java
package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import services.GestaoCandidatosService;
import services.GestaoEleitoresService;
import model.Candidato;
import model.Eleitor;

public class AdminServiceTest {

    @Test
    public void testInserirCandidato() {
        GestaoCandidatosService svc = new GestaoCandidatosService();
        Candidato c = new Candidato("João", "Partido A");
        svc.inserir(c);
        assertTrue(svc.listar().contains(c));
    }

    @Test
    public void testRemoverEleitor() {
        GestaoEleitoresService svc = new GestaoEleitoresService();
        Eleitor e = new Eleitor("Maria", "maria1", "pass123");
        svc.inserir(e);
        svc.remover(e);
        assertFalse(svc.listar().contains(e));
    }
}
