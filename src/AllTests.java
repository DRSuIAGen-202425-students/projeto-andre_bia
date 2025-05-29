import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Esta classe agrupa todos os testes definidos
@Suite
@SelectClasses({
        AutenticacaoServiceTest.class,
        CandidatoServiceTest.class,
        VotacaoServiceTest.class,
        ResultadoServiceTest.class
})
public class AllTests {
    // Não é necessário nenhum método aqui.
}
