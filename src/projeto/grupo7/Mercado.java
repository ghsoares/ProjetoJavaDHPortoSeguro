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
		// Pega o input opção do menu
		int op;
		do {
			// Tenta pegar a opção do menu
			try {
				op = leia.nextInt();

				// Caso for menor que um ou maior que três, continua o loop
				if (op < 1 || op > 3) {
					System.out.println("\nOpção inválida, por favor, insira uma opção entre 1 e 3.");
					continue;
				}

				// Caso for válido, sai do loop
				break;
				// Erro de digitação
			} catch (InputMismatchException e) {
				System.out.println("\nOpção inválida, por favor, insira um número inteiro.");

				// Limpar o cache do Scanner
				leia.nextLine();
			}
		} while (true);

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

		// Pega o codigo de produto
		int cod = pegarCodigoProduto();

		// Retorna para o menu, caso o código for zero
		if (cod == 0) {
			return;
		}

		// Pega o produto pelo código, ou nulo caso não exista
		Produto prod = estoque.getProduto(cod);

		// Enquanto o produto for nulo, continua pegando o codigo do usuário
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

		// Tenta subtrair a quantidade do produto do estoque
		Produto edson;
		
		do {
			// Pega a quantidade do input do usuário
			System.out.printf("\nInsira a quantidade do produto [%s] que deseja (0 para retornar):\n", prod.getNome());
			int qtd = pegarQuantidadeProduto();

			// Retorna para o menu, caso a quantidade for zero
			if (qtd == 0) {
				return;
			}
			
			edson = estoque.tentarSubtrairQuantidade(cod, qtd);
			if (edson == null) {
				System.out.println("\nQuantidade insuficiente no estoque, tente novamente.");
			} else {
				System.out.printf("\nx%d unidades do produto [%s] foram adicionados ao carrinho!\n", qtd, edson.getNome());
				carrinho.adicionarProduto(cod, edson);
			}
		} while (edson == null);
	}

	// Método para checar o carrinho
	public static void checarCarrinho() {
		System.out.println("\nEsses são os produtos no carrinho:");

		// Mostra o carrinho
		carrinho.mostrar();

		// Valores da compra
		float valorTotal = 0;
		float valorDoacaoTotal = 0;

		// Para cada produto
		for (int i = 0; i < carrinho.getQuantidadeElementos(); i++) {
			// Pega o código e o produto
			int codigo = carrinho.getCodigo(i);
			Produto produto = carrinho.getProduto(codigo);

			// Calcula o valor do produto
			float valor = produto.getPreco() * produto.getQuantidade();

			// Calcula o valor de doação do produto
			float valorDoacao = valor * produto.getPorcentagemDoacao();

			// Soma os valores
			valorTotal += valor;
			valorDoacaoTotal += valorDoacao;
		}

		// Mostra o resumo da compra
		System.out.println("\nResumo da compra:");

		System.out.printf("\nValor total:        R$ %2.2f\n", valorTotal);
		System.out.printf("Valor de doação:      R$ %2.2f\n", valorDoacaoTotal);

		// Mostra o menu
		System.out.println("\n1 - Finalizar compra");
		System.out.println("2 - Excluir um item");
		System.out.println("3 - Voltar");

		// Pega a opção do menu
		int op = menu();

		if (op == 3) {
			return;
		}

		switch (op) {
		case 1: // Finalizar a compra
		{
			System.out.println("\nSua compra foi efetuada com sucesso!");
			System.out.printf(
					"\nR$ %2.2f foram doados para a ONG Amigos do Bem, que têm como objetivo principal a extinção da fome.\n",
					valorDoacaoTotal);
			System.out.println("Para mais informações, acesse o site: https://www.amigosdobem.org/ !!!");
			System.out.println("Muito obrigado pela preferência!");

			// Limpa o carrinho
			carrinho.limpar();
			
			System.out.println("\nO carrinho está vazio, voltando ao menu principal.");
			break;
		}
		case 2: // Excluir um item
		{
			System.out.println("\nInsira o código do produto para remover do carrinho (0 - Voltar):");
			
			// Pega o código de produto
			int cod = pegarCodigoProduto();

			// Retorna para o menu, caso o código for zero
			if (cod == 0) {
				return;
			}

			// Pega o produto pelo código, ou nulo caso não exista
			Produto prod = carrinho.getProduto(cod);

			// Enquanto o produto for nulo, continua pegando o código do usuário
			while (prod == null) {
				System.out.println("\nNão tem um produto com esse código no carrinho, tente novamente");

				// Pega o input código
				cod = pegarCodigoProduto();

				// Retorna para o menu, caso o código for zero
				if (cod == 0) {
					return;
				}

				// Pega o produto pelo código, ou nulo caso não exista
				prod = carrinho.getProduto(cod);
			}
			
			// Tenta subtrair a quantidade do produto do estoque
			Produto edson;
			
			do {
				// Pega a quantidade do input do usuário
				System.out.printf("\nInsira a quantidade do produto [%s] que deseja (0 para retornar):\n", prod.getNome());
				int qtd = pegarQuantidadeProduto();

				// Retorna para o menu, caso a quantidade for zero
				if (qtd == 0) {
					return;
				}
				
				edson = carrinho.tentarSubtrairQuantidade(cod, qtd);
				if (edson == null) {
					System.out.println("\nQuantidade insuficiente no carrinho, tente novamente.");
				} else {
					System.out.printf("\nx%d unidades do produto [%s] foram removidos do carrinho!\n", qtd, edson.getNome());
					estoque.adicionarProduto(cod, edson);
				}
			} while (edson == null);
			break;
		}
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

		System.out.println("\nSeja bem vindo ao mercado do Edson!");

		int op;

		do {
			// Mostrar o menu
			System.out.println("\nInsira uma opção:");

			System.out.println("\n1 - Adicionar um produto ao carrinho.");
			System.out.println("2 - Checar o carrinho.");
			System.out.println("3 - Sair do programa.");

			// Pega a opção
			op = menu();

			switch (op) {
			case 1: {
				// Adicionar um produto ao carrinho
				adicionarAoCarrinho();
				break;
			}
			case 2: {
				// Checa o carrinho
				checarCarrinho();
				break;
			}
			}

		} while (op != 3);

		System.out.println("\nAté mais!");
	}
}
