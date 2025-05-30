// ResultadosServiceTest.java
package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import services.ResultadosService;
import model.Candidato;

import java.util.Map;

public class ResultadosServiceTest {

    @Test
    public void testResultadoApuramento() {
        ResultadosService svc = new ResultadosService();

        Candidato c1 = new Candidato("A", "Partido A");
        Candidato c2 = new Candidato("B", "Partido B");

        svc.registarVoto(c1);
        svc.registarVoto(c1);
        svc.registarVoto(c2);

        Map<Candidato, Integer> resultados = svc.getResultados();
        assertEquals(2, resultados.get(c1));
        assertEquals(1, resultados.get(c2));
    }

    @Test
    public void testVencedor() {
        ResultadosService svc = new ResultadosService();

        Candidato c1 = new Candidato("A", "Partido A");
        Candidato c2 = new Candidato("B", "Partido B");

        svc.registarVoto(c1);
        svc.registarVoto(c2);
        svc.registarVoto(c2);

        Candidato vencedor = svc.getVencedor();
        assertEquals("B", vencedor.getNome());
    }
}
