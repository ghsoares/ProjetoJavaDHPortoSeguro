package projeto.grupo7;

public class Carrinho extends Inventario implements Mostravel {

	@Override
	// Método para mostrar o relatório do carrinho
	public void mostrar() {
		// Alface
		// ---------------
		// Alface
		/*
		 * Nome |Preço individual |% de doação |Qtd |Preço |Valor de doação
		 * 
		 * Alface(KG) |R$ 07,50 |2,5% |3 |R$ 22,50 |R$ 00,56 Tomate(KG) |R$ 10,00 |5% |5
		 * |R$ 50,00 |R$ 02,50
		 */
				
		// Mostra o cabeçalho do relatário
		System.out.print("\n" + Utilitario.padRightSpaces("Código", 8) + "|");
		System.out.print(Utilitario.padRightSpaces("Nome", 20) + "|");
		System.out.print(Utilitario.padRightSpaces("Qtd", 6) + "|");
		System.out.print(Utilitario.padRightSpaces("Preço", 16) + "|");
		System.out.print(Utilitario.padRightSpaces("% de doação", 16) + "|");
		System.out.println(Utilitario.padRightSpaces("Valor de doação", 16));
		System.out.println();
		
		// Para cada produto
		for (int i = 0; i < this.getQuantidadeElementos(); i++) {
			// Pega o código e o produto
			int codigo = this.getCodigo(i);
			Produto produto = this.getProduto(codigo);
			
			// Calcula o preço e o valor de doação do produto
			float preco = produto.getPreco() * produto.getQuantidade();
			float valorDoacao = (produto.getPreco() * produto.getPorcentagemDoacao()) * produto.getQuantidade();
			
			// Mostra as propriedades do produto
			System.out.print(Utilitario.padRightSpaces("" + codigo, 8) + "|");
			System.out.print(Utilitario.padRightSpaces(produto.getNome(), 20) + "|");
			System.out.print(Utilitario.padRightSpaces("" + produto.getQuantidade(), 6) + "|");
			System.out.print(Utilitario.padRightSpaces(String.format("R$ %.2f", preco), 16) + "|");
			System.out.print(Utilitario.padRightSpaces(String.format("%.2f", produto.getPorcentagemDoacao() * 100) + "%", 16) + "|");
			System.out.println(Utilitario.padRightSpaces(String.format("R$ %.2f", valorDoacao), 16));
			
		}
	}

}
