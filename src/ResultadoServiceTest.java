import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultadoServiceTest {

    @Test
    void deveApresentarResultadosCorretamente() {
        ResultadoService resultadoService = new ResultadoService();
        resultadoService.registrarVoto(101);
        resultadoService.registrarVoto(101);
        resultadoService.registrarVoto(102);

        Resultado resultado = resultadoService.calcularResultados();
        assertEquals(2, resultado.getVotos(101));
        assertEquals(1, resultado.getVotos(102));
        assertEquals(101, resultado.getVencedor());
    }
}
