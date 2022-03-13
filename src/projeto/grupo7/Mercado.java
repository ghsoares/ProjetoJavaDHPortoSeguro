package projeto.grupo7;

import java.util.Scanner;

public class Mercado {
	// Variáveis do estoque e do carrinho
	private static Estoque estoque;
	private static Carrinho carrinho;

	// Método para mostrar e pegar a opção do menu
	public static int menu(Scanner leia) {
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
	
	// Método para adicionar um produto ao carrinho
	public static void adicionarAoCarrinho() {
		
	}

	public static void main(String[] args) {
		// Criação do estoque e do carrinho
		estoque = new Estoque();
		carrinho = new Carrinho();
		
		// Criação de um objeto scanner para ler a entrada do usuário
		Scanner leia = new Scanner(System.in);

		System.out.println("\nSeja bem vindo ao mercado!");

		int op;

		do {
			// Mostrar o menu e pegar a opção
			op = menu(leia);

			switch (op) {
				case 1: { // Adicionar um produto ao carrinho
					break;
				}
			}

		} while (op != 3);

		System.out.println("\nAté mais!");
	}

}
