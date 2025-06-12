package ui;

import model.*;
import repository.*;
import services.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final UtilizadorRepository utilizadorRepo = new UtilizadorRepository();
    private static final EleitorRepository eleitorRepo = new EleitorRepository();
    private static final CandidatoRepository candidatoRepo = new CandidatoRepository();
    private static final VotoRepository votoRepo = new VotoRepository();
    private static final Votacao votacao = new Votacao();

    private static final AutenticacaoService authService = new AutenticacaoService(utilizadorRepo);
    private static final GestaoCandidatosService candidatosService = new GestaoCandidatosService(candidatoRepo);
    private static final GestaoEleitoresService eleitoresService = new GestaoEleitoresService(utilizadorRepo, eleitorRepo);
    private static final VotacaoService votacaoService = new VotacaoService(votacao, candidatoRepo, eleitorRepo, votoRepo);
    private static final ResultadosService resultadosService = new ResultadosService(candidatoRepo);

    public static void main(String[] args) {
        seedDados();

        while (true) {
            System.out.println("\n===== SISTEMA DE VOTAÇÃO =====");
            System.out.print("Username (0 para sair): ");
            String username = scanner.nextLine();
            if (username.equals("0")) break;

            System.out.print("Password (0 para sair): ");
            String password = scanner.nextLine();
            if (password.equals("0")) break;

            Utilizador user = authService.login(username, password);
            if (user == null) {
                System.out.println("❌ Credenciais inválidas. Tente novamente.");
                continue;
            }

            if (user instanceof Administrador) {
                menuAdministrador();
            } else if (user instanceof Eleitor) {
                menuEleitor((Eleitor) user);
            }
        }

        System.out.println("👋 Programa encerrado.");
    }

    private static void menuAdministrador() {
        while (true) {
            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("1. Gerir Candidatos");
            System.out.println("2. Gerir Eleitores");
            System.out.println("3. Iniciar/Encerrar Votação");
            System.out.println("4. Ver Resultados");
            System.out.println("0. Logout");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    gerirCandidatos();
                    break;
                case "2":
                    gerirEleitores();
                    break;
                case "3":
                    if (votacaoService.isVotacaoAtiva()) {
                        votacaoService.encerrarVotacao();
                    } else {
                        votacaoService.iniciarVotacao();
                    }
                    break;
                case "4":
                    resultadosService.mostrarResultados();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        }
    }

    private static void menuEleitor(Eleitor eleitor) {
        while (true) {
            System.out.println("\n===== MENU ELEITOR =====");
            if (!eleitor.isVotou() && votacaoService.isVotacaoAtiva()) {
                System.out.println("1. Votar");
            }
            System.out.println("0. Logout");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) return;

            if (opcao.equals("1") && !eleitor.isVotou() && votacaoService.isVotacaoAtiva()) {
                List<Candidato> candidatos = candidatosService.listarCandidatos();
                System.out.println("Candidatos:");
                for (Candidato c : candidatos) {
                    System.out.println(c.getId() + " - " + c.getNome() + " (" + c.getPartido() + ")");
                }

                System.out.print("ID do candidato: ");
                String idStr = scanner.nextLine();
                if (idStr.equals("0")) return;

                try {
                    int id = Integer.parseInt(idStr);
                    Candidato candidato = candidatosService.getCandidatoPorId(id);
                    if (candidato == null) {
                        System.out.println("❌ Candidato não encontrado.");
                    } else {
                        votacaoService.votar(eleitor, candidato);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ ID inválido.");
                }
            } else {
                System.out.println("⚠️ Não pode votar neste momento.");
            }
        }
    }

    private static void gerirCandidatos() {
        while (true) {
            System.out.println("\n--- Gestão de Candidatos ---");
            System.out.println("1. Adicionar");
            System.out.println("2. Editar");
            System.out.println("3. Remover");
            System.out.println("0. Voltar");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Partido: ");
                    String partido = scanner.nextLine();
                    candidatosService.inserir(nome, partido);
                    break;
                case "2":
                    System.out.print("ID do candidato: ");
                    int idEditar = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo partido: ");
                    String novoPartido = scanner.nextLine();
                    candidatosService.editar(idEditar, novoNome, novoPartido);
                    break;
                case "3":
                    System.out.print("ID do candidato a remover: ");
                    int idRemover = Integer.parseInt(scanner.nextLine());
                    candidatosService.remover(idRemover);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        }
    }

    private static void gerirEleitores() {
        while (true) {
            System.out.println("\n--- Gestão de Eleitores ---");
            System.out.println("1. Adicionar");
            System.out.println("2. Editar");
            System.out.println("3. Remover");
            System.out.println("0. Voltar");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    eleitoresService.adicionarEleitor(nome, username, password);
                    break;
                case "2":
                    System.out.print("ID do eleitor: ");
                    int idEditar = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova password: ");
                    String novaPass = scanner.nextLine();
                    boolean sucesso = eleitoresService.editarEleitor(idEditar, novoNome, novaPass);
                    if (sucesso) {
                        System.out.println("✅ Eleitor atualizado.");
                    } else {
                        System.out.println("❌ Eleitor não encontrado.");
                    }
                    break;
                case "3":
                    System.out.print("ID do eleitor a remover: ");
                    int idRemover = Integer.parseInt(scanner.nextLine());
                    eleitoresService.removerEleitor(idRemover);
                    System.out.println("✅ Eleitor removido.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        }
    }

    private static void seedDados() {
        if (utilizadorRepo.listarUtilizadores().isEmpty()) {
            Administrador admin = new Administrador(1, "Admin", "admin", "admin123");
            Eleitor eleitor = new Eleitor(2, "Maria", "maria", "1234");
            utilizadorRepo.adicionarUtilizador(admin);
            utilizadorRepo.adicionarUtilizador(eleitor);
            eleitorRepo.adicionarEleitor(eleitor);

            candidatoRepo.adicionarCandidato(new Candidato(1, "Carlos Silva", "Partido A"));
            candidatoRepo.adicionarCandidato(new Candidato(2, "Ana Costa", "Partido B"));
        }
    }
}
