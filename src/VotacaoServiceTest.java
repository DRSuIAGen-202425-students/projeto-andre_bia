import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VotacaoServiceTest {

    @Test
    void eleitorPodeVotarUmaUnicaVez() {
        VotacaoService votacaoService = new VotacaoService();
        Eleitor eleitor = new Eleitor("eleitor1", "senha123", false);
        boolean votou = votacaoService.votar(eleitor, 101);
        assertTrue(votou);
        assertTrue(eleitor.isVotou());
    }

    @Test
    void eleitorNaoPodeVotarDuasVezes() {
        VotacaoService votacaoService = new VotacaoService();
        Eleitor eleitor = new Eleitor("eleitor2", "senha456", false);
        votacaoService.votar(eleitor, 101);
        boolean segundaTentativa = votacaoService.votar(eleitor, 102);
        assertFalse(segundaTentativa);
    }
}
