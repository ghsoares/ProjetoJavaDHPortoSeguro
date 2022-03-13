package projeto.grupo7;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Inventario {
	// Map de produtos do inventário
	private Map<Integer, Produto> produtos;

	// Construtor do inventário
	public Inventario() {
		// Cria uma instância de map de produtos, mapeado pelo código int
		produtos = new LinkedHashMap<Integer, Produto>();
	}

	// Método para pegar a quantidade de elementos de produtos
	// no map
	public int getQuantidadeElementos() {
		return produtos.size();
	}

	// Método para pegar o código de um produto, a partir da posição
	// no Map
	public int getCodigo(int idx) {
		return (int) produtos.keySet().toArray()[idx];
	}

	// Método para pegar um produto individual pelo código
	public Produto getProduto(int cod) {
		return produtos.getOrDefault(cod, null);
	}
	
	// Método para adicionar um produto, de determinado código
	public void adicionarProduto(int cod, Produto prod) {
		// Tentar pegar do map
		Produto outroProduto = produtos.getOrDefault(cod, null);

		// Caso existir, só adiciono a quantidade
		if (outroProduto != null) {
			outroProduto.adicionarQuantidade(prod.getQuantidade());
		}
		// Senão adiciono ao map
		else {
			produtos.put(cod, prod);
		}
	}

	// Método que tenta reduzir a quantidade de um produto,
	// caso conseguir reduzir, return um novo objeto
	public Produto tentarSubtrairQuantidade(int cod, int qtd) {
		// Pega o produto usando o método da classe Inventario
		Produto produto = getProduto(cod);

		// Checa se a quantidade no inventário é maior ou igual
		// á quantidade passada no método
		if (produto.getQuantidade() >= qtd) {
			// Reduz a quantidade do produto
			produto.adicionarQuantidade(-qtd);
			
			// Criar um novo objeto do tipo produto
			Produto outroProduto = new Produto(produto.getNome(), qtd, produto.getPreco(), produto.getPorcentagemDoacao());
			
			// Retorna esse novo produto
			return outroProduto;
		} else {
			return null;
		}
	}
}




