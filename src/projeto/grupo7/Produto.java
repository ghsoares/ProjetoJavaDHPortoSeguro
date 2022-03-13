package projeto.grupo7;

public interface Produto {
	String 	getNome();
	int 	getQuantidade();
	float 	getPreco();
	float 	getPorcentagemDoacao();
	
	void	adicionarQuantidade(int qtd);
}
