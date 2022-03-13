package projeto.grupo7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Mercado {
	// Scanner do input do usuário
	private static Scanner leia;

	// Variáveis do estoque e do carrinho
	private static Estoque estoque;
	private static Carrinho carrinho;

	// Método para mostrar e pegar a opção do menu
	public static int menu() {
		// Mostra o menu
		System.out.println("\nInsira uma opção:");

		System.out.println("\n1 - Adicionar um produto ao carrinho.");
		System.out.println("2 - Checar o carrinho.");
		System.out.println("3 - Sair do programa.");

		// Ler a opção
		int op = leia.nextInt();

		// Continua lendo a opção caso for inválida
		while (op < 1 || op > 3) {
			System.out.println("\nOpção inválida! Tenta de novo:");
			op = leia.nextInt();
		}

		// Retorna a opção
		return op;
	}

	// Método para pegar o input código de produto do usuário
	public static int pegarCodigoProduto() {
		// Pega o input código
		int cod;
		do {
			// Tenta pegar o código do input
			try {
				cod = leia.nextInt();

				// Caso for menor que zero, continua o loop
				if (cod < 0) {
					System.out.println("\nCódigo inválido, por favor, insira um número maior que zero.");
					continue;
				}

				// Caso for válido, sai do loop
				break;
				// Erro de digitação
			} catch (InputMismatchException e) {
				System.out.println("\nCódigo inválido, por favor, insira um número inteiro.");

				// Limpar o cache do Scanner
				leia.nextLine();
			}
		} while (true);

		// Retorna o código
		return cod;
	}

	// Método para pegar o input quantidade de produto do usuário
	public static int pegarQuantidadeProduto() {
		// Pega o input quantidade
		int qtd;
		do {
			// Tenta pegar a quantidade do input
			try {
				qtd = leia.nextInt();

				// Caso for menor que zero, continua o loop
				if (qtd < 0) {
					System.out.println("\nQuantidade inválida, por favor, insira um número maior que zero.");
					continue;
				}

				// Caso for válido, sai do loop
				break;
				// Erro de digitação
			} catch (InputMismatchException e) {
				System.out.println("\nQuantidade inválida, por favor, insira um número inteiro.");

				// Limpar o cache do Scanner
				leia.nextLine();
			}
		} while (true);

		// Retorna a quantidade
		return qtd;
	}

	// Método para adicionar um produto ao carrinho
	public static void adicionarAoCarrinho() {
		System.out.println("\nEsses são os produtos disponíveis:");

		// Mostra o estoque
		estoque.mostrar();

		System.out.println("\nInsira o código do produto que deseja (0 para retornar):");

		// Pega o código de produto
		int cod = pegarCodigoProduto();

		// Retorna para o menu, caso o código for zero
		if (cod == 0) {
			return;
		}

		// Pega o produto pelo código, ou nulo caso não exista
		Produto prod = estoque.getProduto(cod);

		// Enquanto o produto for nulo, continua pegando o código do usuário
		while (prod == null) {
			System.out.println("\nNão existe um produto com esse código, tente novamente");

			// Pega o input código
			cod = pegarCodigoProduto();

			// Retorna para o menu, caso o código for zero
			if (cod == 0) {
				return;
			}

			// Pega o produto pelo código, ou nulo caso não exista
			prod = estoque.getProduto(cod);
		}

		System.out.printf("\nInsira a quantidade do produto [%s] que deseja (0 para retornar):\n", prod.getNome());

		// Pega a quantidade do input do usuário
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

	
	
	// Método principal de execução do programa
	public static void main(String[] args) {
		// Criação do estoque e do carrinho
		estoque = new Estoque();
		carrinho = new Carrinho();

		// Popular o estoque do mercado
		estoque.adicionarProduto(32, new Produto("Alface(UND)", 250, 2.50f, 0.03f));
		estoque.adicionarProduto(64, new Produto("Arroz(5KG)", 400, 26.60f, 0.15f));
		estoque.adicionarProduto(80, new Produto("Creme dental(UND)", 500, 7.40f, 0.10f));
		estoque.adicionarProduto(76, new Produto("Esponja(10 UND)", 300, 15.50f, 0.09f));
		estoque.adicionarProduto(47, new Produto("Feijão(1KG)", 300, 7.40f, 0.085f));
		estoque.adicionarProduto(100, new Produto("Ki-suco", 0, 1f, 0.5f));
		estoque.adicionarProduto(79, new Produto("Sabonete(UND)", 250, 3.50f, 0.075f));
		estoque.adicionarProduto(97, new Produto("Suco de caixa(1L)", 4, 6f, 0.12f));
		estoque.adicionarProduto(16, new Produto("Tomate(1KG)", 300, 6.75f, 0.05f));

		// Criação de um objeto scanner para ler a entrada do usuário
		leia = new Scanner(System.in);

		System.out.println("\nSeja bem vindo ao mercado!");

		int op;

		do {
			// Mostrar o menu e pegar a opção
			op = menu();

			switch (op) {
			case 1: {
				// Adicionar um produto ao carrinho
				adicionarAoCarrinho();
				break;
			}
			}

		} while (op != 3);

		System.out.println("\nAté mais!");
	}
}





