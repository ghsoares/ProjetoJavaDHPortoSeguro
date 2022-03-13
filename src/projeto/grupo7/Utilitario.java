package projeto.grupo7;

public class Utilitario {

	// Ele formata uma string e preenche com espaços
	public static String padRightSpaces(String inputString, int length) {
		// Caso já for maior que o tamanho desejado, retorna a substring
		if (inputString.length() >= length) {
			return inputString.substring(0, length);
		}

		// Criação de um criador de strings
		StringBuilder sb = new StringBuilder();

		// Adiciona a string input
		sb.append(inputString);

		// Preenche com espaços enquanto o tamanho for menor que o tamanho desejado
		while (sb.length() < length) {
			sb.append(' ');
		}

		// Retorna a string resultado
		return sb.toString();
	}

}
