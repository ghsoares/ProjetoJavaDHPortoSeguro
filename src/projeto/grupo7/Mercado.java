package projeto.grupo7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Mercado {
	// Scanner do input do usu�rio
	private static Scanner leia;

	// Vari�veis do estoque e do carrinho
	private static Estoque estoque;
	private static Carrinho carrinho;

	// M�todo para mostrar e pegar a op��o do menu
	public static int menu() {
		// Pega o input op��o do menu
		int op;
		do {
			// Tenta pegar a op��o do menu
			try {
				op = leia.nextInt();

				// Caso for menor que um ou maior que tr�s, continua o loop
				if (op < 1 || op > 3) {
					System.out.println("\nOp��o inv�lida, por favor, insira uma op��o entre 1 e 3.");
					continue;
				}

				// Caso for v�lido, sai do loop
				break;
				// Erro de digita��o
			} catch (InputMismatchException e) {
				System.out.println("\nOp��o inv�lida, por favor, insira um n�mero inteiro.");

				// Limpar o cache do Scanner
				leia.nextLine();
			}
		} while (true);

		// Retorna a op��o
		return op;
	}

	// M�todo para pegar o input c�digo de produto do usu�rio
	public static int pegarCodigoProduto() {
		// Pega o input c�digo
		int cod;
		do {
			// Tenta pegar o c�digo do input
			try {
				cod = leia.nextInt();

				// Caso for menor que zero, continua o loop
				if (cod < 0) {
					System.out.println("\nC�digo inv�lido, por favor, insira um n�mero maior que zero.");
					continue;
				}

				// Caso for v�lido, sai do loop
				break;
				// Erro de digita��o
			} catch (InputMismatchException e) {
				System.out.println("\nC�digo inv�lido, por favor, insira um n�mero inteiro.");

				// Limpar o cache do Scanner
				leia.nextLine();
			}
		} while (true);

		// Retorna o c�digo
		return cod;
	}

	// M�todo para pegar o input quantidade de produto do usu�rio
	public static int pegarQuantidadeProduto() {
		// Pega o input quantidade
		int qtd;
		do {
			// Tenta pegar a quantidade do input
			try {
				qtd = leia.nextInt();

				// Caso for menor que zero, continua o loop
				if (qtd < 0) {
					System.out.println("\nQuantidade inv�lida, por favor, insira um n�mero maior que zero.");
					continue;
				}

				// Caso for v�lido, sai do loop
				break;
				// Erro de digita��o
			} catch (InputMismatchException e) {
				System.out.println("\nQuantidade inv�lida, por favor, insira um n�mero inteiro.");

				// Limpar o cache do Scanner
				leia.nextLine();
			}
		} while (true);

		// Retorna a quantidade
		return qtd;
	}

	// M�todo para adicionar um produto ao carrinho
	public static void adicionarAoCarrinho() {
		System.out.println("\nEsses s�o os produtos dispon�veis:");

		// Mostra o estoque
		estoque.mostrar();

		System.out.println("\nInsira o c�digo do produto que deseja (0 para retornar):");

		// Pega o c�digo de produto
		int cod = pegarCodigoProduto();

		// Retorna para o menu, caso o c�digo for zero
		if (cod == 0) {
			return;
		}

		// Pega o produto pelo c�digo, ou nulo caso n�o exista
		Produto prod = estoque.getProduto(cod);

		// Enquanto o produto for nulo, continua pegando o c�digo do usu�rio
		while (prod == null) {
			System.out.println("\nN�o existe um produto com esse c�digo, tente novamente");

			// Pega o input c�digo
			cod = pegarCodigoProduto();

			// Retorna para o menu, caso o c�digo for zero
			if (cod == 0) {
				return;
			}

			// Pega o produto pelo c�digo, ou nulo caso n�o exista
			prod = estoque.getProduto(cod);
		}

		System.out.printf("\nInsira a quantidade do produto [%s] que deseja (0 para retornar):\n", prod.getNome());

		// Pega a quantidade do input do usu�rio
		int qtd = pegarQuantidadeProduto();

		// Retorna para o menu, caso a quantidade for zero
		if (qtd == 0) {
			return;
		}

		Produto edson = estoque.tentarSubtrairQuantidade(cod, qtd);

		// Tenta subtrair a quantidade do produto do estoque
		if (edson != null) {
			// Adiciono ao carrinho o produto com a quantidade requisitada
			carrinho.adicionarProduto(cod, edson);

			System.out.printf("\nx%d unidades do produto [%s] foram adicionados ao carrinho!\n", qtd, edson.getNome());
		} else {
			System.out.println("\nQuantidade insuficiente no estoque, voltando ao menu");
		}
	}

	
	
	// M�todo principal de execu��o do programa
	public static void main(String[] args) {
		// Cria��o do estoque e do carrinho
		estoque = new Estoque();
		carrinho = new Carrinho();

		// Popular o estoque do mercado
		estoque.adicionarProduto(32, new Produto("Alface(UND)", 250, 2.50f, 0.03f));
		estoque.adicionarProduto(64, new Produto("Arroz(5KG)", 400, 26.60f, 0.15f));
		estoque.adicionarProduto(80, new Produto("Creme dental(UND)", 500, 7.40f, 0.10f));
		estoque.adicionarProduto(76, new Produto("Esponja(10 UND)", 300, 15.50f, 0.09f));
		estoque.adicionarProduto(47, new Produto("Feij�o(1KG)", 300, 7.40f, 0.085f));
		estoque.adicionarProduto(100, new Produto("Ki-suco", 0, 1f, 0.5f));
		estoque.adicionarProduto(79, new Produto("Sabonete(UND)", 250, 3.50f, 0.075f));
		estoque.adicionarProduto(97, new Produto("Suco de caixa(1L)", 4, 6f, 0.12f));
		estoque.adicionarProduto(16, new Produto("Tomate(1KG)", 300, 6.75f, 0.05f));

		// Cria��o de um objeto scanner para ler a entrada do usu�rio
		leia = new Scanner(System.in);

		System.out.println("\nSeja bem vindo ao mercado!");

		int op;

		do {
			// Mostrar o menu e pegar a op��o
			op = menu();

			switch (op) {
			case 1: {
				// Adicionar um produto ao carrinho
				adicionarAoCarrinho();
				break;
			}
			}

		} while (op != 3);

		System.out.println("\nAt� mais!");
	}
}





