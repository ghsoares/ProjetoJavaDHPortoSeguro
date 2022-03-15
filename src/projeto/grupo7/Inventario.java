package projeto.grupo7;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Inventario {
	// Map de produtos do invent�rio
	protected Map<Integer, Produto> produtos;

	// Construtor do invent�rio
	public Inventario() {
		// Cria uma inst�ncia de map de produtos, mapeado pelo c�digo int
		produtos = new LinkedHashMap<Integer, Produto>();
	}

	// M�todo para pegar a quantidade de elementos de produtos
	// no map
	public int getQuantidadeElementos() {
		return produtos.size();
	}

	// M�todo para pegar o c�digo de um produto, a partir da posi��o
	// no Map
	public int getCodigo(int idx) {
		return (int) produtos.keySet().toArray()[idx];
	}

	// M�todo para pegar um produto individual pelo c�digo
	public Produto getProduto(int cod) {
		return produtos.getOrDefault(cod, null);
	}

	// M�todo para adicionar um produto, de determinado c�digo
	public void adicionarProduto(int cod, Produto prod) {
		// Tentar pegar do map
		Produto outroProduto = produtos.getOrDefault(cod, null);

		// Caso existir, s� adiciono a quantidade
		if (outroProduto != null) {
			outroProduto.adicionarQuantidade(prod.getQuantidade());
		}
		// Sen�o adiciono ao map
		else {
			produtos.put(cod, prod);
		}
	}

	// M�todo para tirar um produto do invent�rio
	public Produto tirarProduto(int cod) {
		return produtos.remove(cod);
	}

	// M�todo que tenta reduzir a quantidade de um produto,
	// caso conseguir reduzir, return um novo objeto
	public Produto tentarSubtrairQuantidade(int cod, int qtd) {
		// Pega o produto usando o m�todo da classe Inventario
		Produto produto = getProduto(cod);

		// Checa se a quantidade no invent�rio � maior ou igual
		// � quantidade passada no m�todo
		if (produto.getQuantidade() >= qtd) {
			// Reduz a quantidade do produto
			produto.adicionarQuantidade(-qtd);

			// Criar um novo objeto do tipo produto
			Produto outroProduto = new Produto(produto.getNome(), qtd, produto.getPreco(),
					produto.getPorcentagemDoacao());

			// Retorna esse novo produto
			return outroProduto;
		} else {
			return null;
		}
	}
}
