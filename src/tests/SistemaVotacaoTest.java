//package tests;
//
//import model.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import repository.*;
//import services.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SistemaVotacaoTest {
//
//    private UtilizadorRepository utilizadorRepo;
//    private EleitorRepository eleitorRepo;
//    private CandidatoRepository candidatoRepo;
//    private VotoRepository votoRepo;
//
//    private AutenticacaoService autenticacaoService;
//    private GestaoCandidatosService gestaoCandidatosService;
//    private GestaoEleitoresService gestaoEleitoresService;
//    private VotacaoService votacaoService;
//    private ResultadosService resultadosService;
//
//    @BeforeEach
//    public void setup() {
//        utilizadorRepo = new UtilizadorRepository();
//        eleitorRepo = new EleitorRepository();
//        candidatoRepo = new CandidatoRepository();
//        votoRepo = new VotoRepository();
//        Votacao votacao = new Votacao();
//
//        autenticacaoService = new AutenticacaoService(utilizadorRepo);
//        gestaoCandidatosService = new GestaoCandidatosService(candidatoRepo);
//        gestaoEleitoresService = new GestaoEleitoresService(eleitorRepo);
//        votacaoService = new VotacaoService(votacao, candidatoRepo, eleitorRepo, votoRepo);
//        resultadosService = new ResultadosService(candidatoRepo);
//
//        // Dados iniciais
//        Administrador admin = new Administrador(1, "Admin", "admin", "1234");
//        Eleitor eleitor = new Eleitor(2, "Eleitor", "eleitor", "pass");
//        utilizadorRepo.adicionarUtilizador(admin);
//        utilizadorRepo.adicionarUtilizador(eleitor);
//        eleitorRepo.adicionarEleitor(eleitor);
//        candidatoRepo.adicionarCandidato(new Candidato(1, "Candidato A", "Partido A"));
//    }
//
//    @Test
//    public void testLoginComCredenciaisValidas() {
//        Utilizador utilizador = autenticacaoService.login("admin", "1234");
//        assertNotNull(utilizador);
//        assertTrue(utilizador instanceof Administrador);
//    }
//
//    @Test
//    public void testLoginComCredenciaisInvalidas() {
//        Utilizador utilizador = autenticacaoService.login("admin", "errado");
//        assertNull(utilizador);
//    }
//
//    @Test
//    public void testInserirEditarRemoverCandidato() {
//        Candidato c = new Candidato(2, "Novo", "Partido B");
//        gestaoCandidatosService.inserir(c);
//        assertEquals(2, candidatoRepo.listarCandidatos().size());
//
//        c.setNome("Editado");
//        gestaoCandidatosService.editarCandidato(c);
//        assertEquals("Editado", candidatoRepo.encontrarPorId(2).getNome());
//
//        gestaoCandidatosService.removerCandidato(2);
//        assertEquals(1, candidatoRepo.listarTodos().size());
//    }
//
//    @Test
//    public void testInserirEditarRemoverEleitor() {
//        Eleitor e = new Eleitor(3, "Novo Eleitor", "user", "pass");
//        gestaoEleitoresService.inserirEleitor(e);
//        assertEquals(2, eleitorRepo.listarTodos().size());
//
//        e.setNome("Editado");
//        gestaoEleitoresService.editarEleitor(e);
//        assertEquals("Editado", eleitorRepo.encontrarPorId(3).getNome());
//
//        gestaoEleitoresService.removerEleitor(3);
//        assertEquals(1, eleitorRepo.listarTodos().size());
//    }
//
//    @Test
//    public void testVotacaoFluxoCompleto() {
//        Eleitor eleitor = eleitorRepo.encontrarPorId(2);
//        Candidato candidato = candidatoRepo.encontrarPorId(1);
//
//        votacaoService.iniciarVotacao();
//        assertTrue(votacaoService.isVotacaoAtiva());
//
//        boolean sucesso = votacaoService.votar(eleitor, candidato);
//        assertTrue(sucesso);
//        assertTrue(eleitor.isVotou());
//        assertEquals(1, candidato.getNumeroVotos());
//
//        // tentativa de votar novamente
//        boolean tentativa2 = votacaoService.votar(eleitor, candidato);
//        assertFalse(tentativa2);
//
//        votacaoService.encerrarVotacao();
//        assertFalse(votacaoService.isVotacaoAtiva());
//
//        // tentar votar após o encerramento
//        boolean tentativa3 = votacaoService.votar(eleitor, candidato);
//        assertFalse(tentativa3);
//    }
//
//    @Test
//    public void testApuramentoResultados() {
//        Eleitor eleitor = eleitorRepo.encontrarPorId(2);
//        Candidato candidato = candidatoRepo.encontrarPorId(1);
//        votacaoService.iniciarVotacao();
//        votacaoService.votar(eleitor, candidato);
//
//        int total = resultadosService.calcularTotalVotos();
//        assertEquals(1, total);
//
//        Candidato vencedor = resultadosService.determinarVencedor();
//        assertNotNull(vencedor);
//        assertEquals(candidato.getId(), vencedor.getId());
//
//        double percentagem = resultadosService.calcularPercentagemVotos(vencedor);
//        assertEquals(100.0, percentagem);
//    }
//
//    @Test
//    public void testPrevenirVotoDuplo() {
//        Eleitor eleitor = eleitorRepo.encontrarPorId(2);
//        Candidato candidato = candidatoRepo.encontrarPorId(1);
//        votacaoService.iniciarVotacao();
//
//        assertTrue(votacaoService.votar(eleitor, candidato));
//        assertFalse(votacaoService.votar(eleitor, candidato)); // não pode votar duas vezes
//    }
//
//    @Test
//    public void testVotacaoSemEstarAtiva() {
//        Eleitor eleitor = eleitorRepo.encontrarPorId(2);
//        Candidato candidato = candidatoRepo.encontrarPorId(1);
//
//        boolean resultado = votacaoService.votar(eleitor, candidato);
//        assertFalse(resultado); // não pode votar se votação não estiver ativa
//    }
//}
