package ui;

import model.Administrador;
import model.Eleitor;
import model.Utilizador;
import repository.CandidatoRepository;
import repository.EleitorRepository;
import repository.UtilizadorRepository;
import services.AutenticacaoService;
import services.GestaoCandidatosService;
import service.GestaoEleitoresService;
import services.VotacaoService;

import java.util.Scanner;

public class Main {

    private static UtilizadorRepository utilizadorRepo = new UtilizadorRepository();
    private static CandidatoRepository candidatoRepo = new CandidatoRepository();
    private static EleitorRepository eleitorRepo = new EleitorRepository();

    private static AutenticacaoService autenticacaoService = new AutenticacaoService(utilizadorRepo);
    private static GestaoCandidatosService gestaoCandidatosService = new GestaoCandidatosService(candidatoRepo);
    private static GestaoEleitoresService gestaoEleitoresService = new GestaoEleitoresService(eleitorRepo);
    private static VotacaoService votacaoService = new VotacaoService(candidatoRepo, eleitorRepo);

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDadosDemo();

        System.out.println("=== Sistema de Votação ===");

        while (true) {
            System.out.print("\nUsername: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            Utilizador utilizador = autenticacaoService.login(username, password);

            if (utilizador == null) {
                System.out.println("❌ Credenciais inválidas. Tenta novamente.");
                continue;
            }

            if (utilizador instanceof Administrador) {
                menuAdministrador((Administrador) utilizador);
            } else if (utilizador instanceof Eleitor) {
                menuEleitor((Eleitor) utilizador);
            }
        }
    }

    private static void menuAdministrador(Administrador admin) {
        while (true) {
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1. Gerir Candidatos");
            System.out.println("2. Gerir Eleitores");
            System.out.println("3. Iniciar Votação");
            System.out.println("4. Encerrar Votação");
            System.out.println("5. Ver Resultados");
            System.out.println("0. Logout");
            System.out.print("Escolha: ");

            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    gerirCandidatos();
                    break;
                case "2":
                    gerirEleitores();
                    break;
                case "3":
                    votacaoService.iniciarVotacao();
                    break;
                case "4":
                    votacaoService.encerrarVotacao();
                    break;
                case "5":
                    mostrarResultados();
                    break;
                case "0":
                    System.out.println("Logout realizado.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void gerirCandidatos() {
        System.out.println("\n--- Gestão de Candidatos ---");
        System.out.println("1. Inserir Candidato");
        System.out.println("2. Editar Candidato");
        System.out.println("3. Remover Candidato");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                System.out.print("ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Partido: ");
                String partido = scanner.nextLine();

                gestaoCandidatosService.inserirCandidato(new model.Candidato(id, nome, partido));
                break;
            case "2":
                System.out.print("ID do candidato a editar: ");
                int idEditar = Integer.parseInt(scanner.nextLine());
                model.Candidato cExistente = candidatoRepo.encontrarPorId(idEditar);
                if (cExistente == null) {
                    System.out.println("Candidato não encontrado.");
                    break;
                }
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();
                System.out.print("Novo partido: ");
                String novoPartido = scanner.nextLine();

                cExistente.setNome(novoNome);
                cExistente.setPartido(novoPartido);
                gestaoCandidatosService.editarCandidato(cExistente);
                break;
            case "3":
                System.out.print("ID do candidato a remover: ");
                int idRemover = Integer.parseInt(scanner.nextLine());
                gestaoCandidatosService.removerCandidato(idRemover);
                break;
            case "0":
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void gerirEleitores() {
        System.out.println("\n--- Gestão de Eleitores ---");
        System.out.println("1. Inserir Eleitor");
        System.out.println("2. Editar Eleitor");
        System.out.println("3. Remover Eleitor");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                System.out.print("ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                gestaoEleitoresService.inserirEleitor(new model.Eleitor(id, nome, username, password));
                break;
            case "2":
                System.out.print("ID do eleitor a editar: ");
                int idEditar = Integer.parseInt(scanner.nextLine());
                model.Eleitor eExistente = eleitorRepo.encontrarPorId(idEditar);
                if (eExistente == null) {
                    System.out.println("Eleitor não encontrado.");
                    break;
                }
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();
                System.out.print("Novo username: ");
                String novoUsername = scanner.nextLine();
                System.out.print("Nova password: ");
                String novaPassword = scanner.nextLine();

                eExistente.setNome(novoNome);
                eExistente.setUsername(novoUsername);
                eExistente.setPassword(novaPassword);
                gestaoEleitoresService.editarEleitor(eExistente);
                break;
            case "3":
                System.out.print("ID do eleitor a remover: ");
                int idRemover = Integer.parseInt(scanner.nextLine());
                gestaoEleitoresService.removerEleitor(idRemover);
                break;
            case "0":
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void mostrarResultados() {
        service.ResultadosService resultadosService = new service.ResultadosService(candidatoRepo);

        int totalVotos = resultadosService.calcularTotalVotos();
        System.out.println("\n--- Resultados da Votação ---");
        System.out.println("Total de votos: " + totalVotos);

        var percentagens = resultadosService.calcularPercentagens();
        percentagens.forEach((candidato, percent) -> {
            System.out.printf("Candidato: %s - %.2f%% (%d votos)\n",
                    candidato.getNome(), percent, candidato.getNumeroVotos());
        });

        var vencedor = resultadosService.determinarVencedor();
        if (vencedor != null) {
            System.out.println("Vencedor: " + vencedor.getNome());
        } else {
            System.out.println("Não há vencedor (empate ou sem votos).");
        }
    }

    private static void menuEleitor(Eleitor eleitor) {
        while (true) {
            System.out.println("\n--- Menu Eleitor ---");
            System.out.println("1. Votar");
            System.out.println("0. Logout");
            System.out.print("Escolha: ");

            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    if (!votacaoService.isVotacaoAtiva()) {
                        System.out.println("A votação não está ativa.");
                        break;
                    }
                    if (eleitor.isVotou()) {
                        System.out.println("Você já votou.");
                        break;
                    }

                    System.out.println("\nCandidatos disponíveis:");
                    candidatoRepo.listarTodos().forEach(c ->
                            System.out.printf("%d - %s (%s)\n", c.getId(), c.getNome(), c.getPartido())
                    );

                    System.out.print("Escolha o ID do candidato: ");
                    int idCandidato = Integer.parseInt(scanner.nextLine());
                    model.Candidato escolhido = candidatoRepo.encontrarPorId(idCandidato);

                    if (escolhido == null) {
                        System.out.println("Candidato inválido.");
                        break;
                    }

                    boolean votoRealizado = votacaoService.votar(eleitor, escolhido);
                    if (votoRealizado) {
                        System.out.println("Voto registado com sucesso!");
                    } else {
                        System.out.println("Erro ao votar.");
                    }
                    break;
                case "0":
                    System.out.println("Logout realizado.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void carregarDadosDemo() {
        // Adiciona admin e eleitores demo para teste
        Administrador admin = new Administrador(1, "Admin", "admin", "admin");
        Eleitor e1 = new Eleitor(2, "Alice", "alice123", "senha1");
        Eleitor e2 = new Eleitor(3, "Bob", "bob123", "senha2");

        utilizadorRepo.adicionarUtilizador(admin);
        utilizadorRepo.adicionarUtilizador(e1);
        utilizadorRepo.adicionarUtilizador(e2);

        // Adiciona candidatos demo
        candidatoRepo.adicionarCandidato(new model.Candidato(1, "Candidato A", "Partido 1"));
        candidatoRepo.adicionarCandidato(new model.Candidato(2, "Candidato B", "Partido 2"));
    }
}
