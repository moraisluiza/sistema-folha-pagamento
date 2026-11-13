
public class ColaboradorComissionado extends Colaborador {

    private double valorVendas;
    private double percentualComissao;

    public ColaboradorComissionado(int matricula, String nome,
                                  double valorVendas, double percentualComissao) {
        super(matricula, nome);
        this.valorVendas = valorVendas;
        this.percentualComissao = percentualComissao;
    }

    @Override
    public double calcularSalarioFinal() {
        double comissao = valorVendas * percentualComissao / 100;
        return getSalarioBase() + comissao;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.printf("Valor das vendas: R$ %.2f%n", valorVendas);
        System.out.printf("Percentual de comissao: %.2f%%%n", percentualComissao);
    }
}
