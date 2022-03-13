package projeto.grupo7;

public class Estoque extends Inventario implements Mostravel {
	@Override
	// Método para mostrar o relatório do estoque
	public void mostrar() {
		// Alface
		// ---------------
		// Alface
		/*
		 * Código |Nome |Preço |Qtd no estoque |% de doação
		 * 
		 * 0 |Tomate(KG) |R$ 10,00 |300 |5% 1 |Alface(KG) |R$ 07,50 |247 |2,5% 2
		 * |Feijão(KG) |R$ 25,00 |300 |10%
		 */

		// Mostra o cabeçalho do relatário
		System.out.print("\n" + Utilitario.padRightSpaces("Código", 8) + "|");
		System.out.print(Utilitario.padRightSpaces("Nome", 16) + "|");
		System.out.print(Utilitario.padRightSpaces("Preço", 16) + "|");
		System.out.print(Utilitario.padRightSpaces("Qtd no estoque", 16) + "|");
		System.out.println(Utilitario.padRightSpaces("% de doação", 16));
		System.out.println();

		// Para cada produto
		for (int i = 0; i < this.getQuantidadeElementos(); i++) {
			// Pega o código e o produto
			int codigo = this.getCodigo(i);
			Produto produto = this.getProduto(codigo);

			// Mostra as propriedades do produto
			System.out.print(Utilitario.padRightSpaces("" + codigo, 8) + "|");
			System.out.print(Utilitario.padRightSpaces(produto.getNome(), 16) + "|");
			System.out.print(Utilitario.padRightSpaces(String.format("R$ %.2f", produto.getPreco()), 16) + "|");
			System.out.print(Utilitario.padRightSpaces("" + produto.getQuantidade(), 16) + "|");
			System.out.println(
					Utilitario.padRightSpaces(String.format("%.2f", produto.getPorcentagemDoacao() * 100) + "%", 16));
		}
	}
}
