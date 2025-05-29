import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CandidatoServiceTest {

    @Test
    void deveAdicionarCandidatoComSucesso() {
        CandidatoService service = new CandidatoService();
        Candidato c = new Candidato(1, "Maria", "Partido X", 101);
        service.adicionarCandidato(c);
        assertEquals(1, service.listarCandidatos().size());
    }

    @Test
    void deveRemoverCandidato() {
        CandidatoService service = new CandidatoService();
        Candidato c = new Candidato(1, "José", "Partido Y", 102);
        service.adicionarCandidato(c);
        service.removerCandidato(102);
        assertTrue(service.listarCandidatos().isEmpty());
    }
}
