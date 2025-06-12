package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;
import services.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaVotacaoTest {

    private UtilizadorRepository utilizadorRepo;
    private EleitorRepository eleitorRepo;
    private CandidatoRepository candidatoRepo;
    private VotoRepository votoRepo;
    private Votacao votacao;

    private AutenticacaoService authService;
    private GestaoCandidatosService candidatosService;
    private GestaoEleitoresService eleitoresService;
    private VotacaoService votacaoService;
    private ResultadosService resultadosService;

    private Administrador admin;
    private Eleitor eleitor;

    @BeforeEach
    void setUp() {
        utilizadorRepo = new UtilizadorRepository();
        eleitorRepo = new EleitorRepository();
        candidatoRepo = new CandidatoRepository();
        votoRepo = new VotoRepository();
        votacao = new Votacao();

        authService = new AutenticacaoService(utilizadorRepo);
        candidatosService = new GestaoCandidatosService(candidatoRepo);
        eleitoresService = new GestaoEleitoresService(utilizadorRepo, eleitorRepo);
        votacaoService = new VotacaoService(votacao, candidatoRepo, eleitorRepo, votoRepo);
        resultadosService = new ResultadosService(candidatoRepo);

        admin = new Administrador(1, "Admin", "admin", "admin123");
        eleitor = new Eleitor(2, "Eleitor", "eleitor", "1234");

        utilizadorRepo.adicionarUtilizador(admin);
        utilizadorRepo.adicionarUtilizador(eleitor);
        eleitorRepo.adicionarEleitor(eleitor);
    }

    // RF01 – Autenticação de Utilizadores
    @Test
    void testAutenticacaoUtilizadores() {
        assertNotNull(authService.login("admin", "admin123"));
        assertNotNull(authService.login("eleitor", "1234"));
        assertNull(authService.login("admin", "errado"));
    }

    // RF02 – Gestão de Candidatos
    @Test
    void testGestaoCandidatos() {
        candidatosService.inserir("João", "Partido X");
        Candidato c = candidatosService.listarCandidatos().get(candidatosService.listarCandidatos().size() - 1);
        assertEquals("João", c.getNome());

        candidatosService.editar(c.getId(), "João Silva", "Partido Y");
        Candidato editado = candidatosService.getCandidatoPorId(c.getId());
        assertEquals("João Silva", editado.getNome());

        candidatosService.remover(c.getId());
        assertNull(candidatosService.getCandidatoPorId(c.getId()));
    }

    // RF03 – Gestão de Eleitores
    @Test
    void testGestaoEleitores() {
        eleitoresService.adicionarEleitor("Maria", "maria", "pass");
        Eleitor maria = eleitorRepo.getTodosEleitores().stream()
                .filter(e -> e.getUsername().equals("maria"))
                .findFirst().orElse(null);
        assertNotNull(maria);

        eleitoresService.editarEleitor(maria.getId(), "Maria Silva", "nova");
        Eleitor atualizado = eleitorRepo.getEleitorPorId(maria.getId());
        assertEquals("Maria Silva", atualizado.getNome());

        eleitoresService.removerEleitor(maria.getId());
        assertNull(eleitorRepo.getEleitorPorId(maria.getId()));
    }

    // RF04 – Início e Encerramento da Votação
    @Test
    void testIniciarEncerrarVotacao() {
        assertFalse(votacaoService.isVotacaoAtiva());
        votacaoService.iniciarVotacao();
        assertTrue(votacaoService.isVotacaoAtiva());
        votacaoService.encerrarVotacao();
        assertFalse(votacaoService.isVotacaoAtiva());
    }

    // RF05 & RF07 – Processo de Votação e Prevenção de Voto Duplo
    @Test
    void testProcessoDeVotacaoEUnicidade() {
        candidatosService.inserir("Joana", "Partido Z");
        Candidato candidato = candidatosService.listarCandidatos().get(0);

        votacaoService.iniciarVotacao();
        assertDoesNotThrow(() -> votacaoService.votar(eleitor, candidato));
        assertTrue(eleitor.isVotou());

        // Tentar votar novamente
        assertThrows(IllegalStateException.class, () -> votacaoService.votar(eleitor, candidato));
    }

    // RF06 – Apuramento de Resultados
    @Test
    void testApuramentoResultados() {
        candidatosService.inserir("Lucas", "Partido K");
        candidatosService.inserir("Beatriz", "Partido L");
        Candidato c1 = candidatosService.listarCandidatos().get(0);
        Candidato c2 = candidatosService.listarCandidatos().get(1);

        votacaoService.iniciarVotacao();
        votacaoService.votar(eleitor, c1);

        List<String> resultados = resultadosService.gerarResumoResultados();
        assertTrue(resultados.stream().anyMatch(l -> l.contains(c1.getNome())));
    }


    @Test
    void testVotarComEleitorNulo() {
        candidatosService.inserir("Zara", "Partido Azul");
        Candidato candidato = candidatosService.listarCandidatos().get(0);
        votacaoService.iniciarVotacao();

        assertThrows(NullPointerException.class, () -> votacaoService.votar(null, candidato));
    }

    @Test
    void testVotarComCandidatoNulo() {
        votacaoService.iniciarVotacao();
        assertThrows(NullPointerException.class, () -> votacaoService.votar(eleitor, null));
    }

    @Test
    void testVotarAntesDeIniciarVotacao() {
        candidatosService.inserir("Leo", "Partido Verde");
        Candidato candidato = candidatosService.listarCandidatos().get(0);

        Exception ex = assertThrows(IllegalStateException.class, () -> votacaoService.votar(eleitor, candidato));
        assertEquals("⚠️ A votação não está ativa.", ex.getMessage());
    }

    @Test
    void testVotarSemCandidatosDisponiveis() {
        votacaoService.iniciarVotacao();

        Exception ex = assertThrows(NullPointerException.class, () -> votacaoService.votar(eleitor, null));
        assertEquals("❌ Este candidato não existe.", ex.getMessage()); // Caso já tenha votado, senão adapta
    }

    @Test
    void testIniciarVotacaoDuasVezes() {
        votacaoService.iniciarVotacao();
        votacaoService.iniciarVotacao(); // Deve apenas imprimir mensagem, não lançar exceção
        assertTrue(votacaoService.isVotacaoAtiva());
    }

    @Test
    void testEncerrarVotacaoDuasVezes() {
        votacaoService.iniciarVotacao();
        votacaoService.encerrarVotacao();
        votacaoService.encerrarVotacao(); // Deve apenas imprimir mensagem, não lançar exceção
        assertFalse(votacaoService.isVotacaoAtiva());
    }

    @Test
    void testEditarEleitorInexistente() {
        boolean resultado = eleitoresService.editarEleitor(9999, "Fantasma", "invalida");
        assertFalse(resultado);
    }

    @Test
    void testRemoverEleitorInexistente() {
        eleitoresService.removerEleitor(-1); // Não lança exceção, só ignora
        assertNull(eleitorRepo.getEleitorPorId(-1));
    }

}
