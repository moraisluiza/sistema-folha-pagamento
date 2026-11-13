
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Colaborador> colaboradores = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE FOLHA DE PAGAMENTO ===");
            System.out.println("1 - Cadastrar colaborador padrao");
            System.out.println("2 - Cadastrar colaborador comissionado");
            System.out.println("3 - Cadastrar colaborador de producao");
            System.out.println("4 - Listar colaboradores e salarios");
            System.out.println("0 - Sair");

            opcao = lerInteiro(sc, "Escolha uma opcao: ");

            if (opcao >= 1 && opcao <= 3) {
                int matricula = lerInteiro(sc, "Matricula: ");

                if (matricula <= 0) {
                    System.out.println("Matricula invalida.");
                    continue;
                }

                boolean matriculaExiste = false;

                for (Colaborador c : colaboradores) {
                    if (c.getMatricula() == matricula) {
                        matriculaExiste = true;
                        break;
                    }
                }

                if (matriculaExiste) {
                    System.out.println("Essa matricula ja esta cadastrada.");
                    continue;
                }

                System.out.print("Nome: ");
                String nome = sc.nextLine().trim();

                if (nome.isEmpty()) {
                    System.out.println("O nome nao pode ficar vazio.");
                    continue;
                }

                Colaborador novo;

                if (opcao == 1) {
                    novo = new ColaboradorPadrao(matricula, nome);

                } else if (opcao == 2) {
                    double vendas = lerDoubleNaoNegativo(
                        sc, "Valor das vendas: "
                    );

                    double percentual = lerDoubleNaoNegativo(
                        sc, "Percentual de comissao: "
                    );

                    novo = new ColaboradorComissionado(
                        matricula, nome, vendas, percentual
                    );

                } else {
                    int quantidade = lerInteiroNaoNegativo(
                        sc, "Quantidade de pecas: "
                    );

                    double valorPeca = lerDoubleNaoNegativo(
                        sc, "Valor por peca: "
                    );

                    novo = new ColaboradorProducao(
                        matricula, nome, quantidade, valorPeca
                    );
                }

                colaboradores.add(novo);
                System.out.println("Colaborador cadastrado com sucesso!");

            } else if (opcao == 4) {
                if (colaboradores.isEmpty()) {
                    System.out.println("Nenhum colaborador cadastrado.");
                } else {
                    System.out.println("\n=== FOLHA DE PAGAMENTO ===");

                    double totalFolha = 0;

                    for (Colaborador c : colaboradores) {
                        System.out.println("-------------------------");
                        c.exibirDados();
                        totalFolha += c.calcularSalarioFinal();
                    }

                    System.out.println("-------------------------");
                    System.out.printf(
                        "Total da folha: R$ %.2f%n", totalFolha
                    );
                }

            } else if (opcao == 0) {
                System.out.println("Programa encerrado.");

            } else {
                System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);

        sc.close();
    }

    public static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    public static int lerInteiroNaoNegativo(
        Scanner sc, String mensagem
    ) {
        while (true) {
            int valor = lerInteiro(sc, mensagem);

            if (valor >= 0) {
                return valor;
            }

            System.out.println("O valor nao pode ser negativo.");
        }
    }

    public static double lerDoubleNaoNegativo(
        Scanner sc, String mensagem
    ) {
        while (true) {
            System.out.print(mensagem);

            try {
                String entrada = sc.nextLine().trim().replace(',', '.');
                double valor = Double.parseDouble(entrada);

                if (!Double.isNaN(valor)
                        && !Double.isInfinite(valor)
                        && valor >= 0) {
                    return valor;
                }

                System.out.println(
                    "Digite um numero valido e nao negativo."
                );

            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            }
        }
    }
}
