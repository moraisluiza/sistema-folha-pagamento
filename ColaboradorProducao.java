
public class ColaboradorProducao extends Colaborador {

    private int quantidadePecas;
    private double valorPorPeca;

    public ColaboradorProducao(int matricula, String nome,
                               int quantidadePecas, double valorPorPeca) {
        super(matricula, nome);
        this.quantidadePecas = quantidadePecas;
        this.valorPorPeca = valorPorPeca;
    }

    @Override
    public double calcularSalarioFinal() {
        double adicional = quantidadePecas * valorPorPeca;
        return getSalarioBase() + adicional;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Quantidade de pecas: " + quantidadePecas);
        System.out.printf("Valor por peca: R$ %.2f%n", valorPorPeca);
    }
}
