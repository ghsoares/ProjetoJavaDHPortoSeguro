package projeto.grupo7;

import java.util.HashMap;
import java.util.Map;

public abstract class Inventario {
	// Array list de produtos do inventário
	private Map<Integer, Produto> produtos;

	// Construtor do inventário
	public Inventario() {		
		// Cria uma instância de map de produtos, mapeado pelo código int
		produtos = new HashMap<Integer, Produto>();
	}

	// Método para pegar a quantidade de elementos de produtos
	// no map
	public int getQuantidadeElementos() {
		return produtos.size();
	}
	
	// Método para pegar o código de um produto, a partir da posição
	// no Map
	public int getCodigo(int idx) {
		return (int)produtos.keySet().toArray()[idx];
	}

	// Método para pegar um produto individual pelo código
	public Produto getProduto(int cod) {
		return produtos.get(cod);
	}
	
	// Método para adicionar um produto, de determinado código
	public void adicionarProduto(int cod, Produto prod) {
		produtos.put(cod, prod);
	}
	
	// Método que tenta reduzir a quantidade de um produto,
	// caso conseguir reduzir, return true
	public boolean tentarSubtrairQuantidade(int cod, int qtd) {
		// Pega o produto usando o método da classe Inventario
		Produto produto = getProduto(cod);
		
		// Checa se a quantidade no inventário é maior ou igual
		// á quantidade passada no método
		if (produto.getQuantidade() >= qtd) {
			// Reduz a quantidade do produto
			produto.adicionarQuantidade(-qtd);
			return true;
		} else {
			return false;
		}
	}
}
