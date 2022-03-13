package projeto.grupo7;

public class Produto {
	private String nome;
	private int quantidade;
	private float preco;
	private float porcentagemDoacao;
	
	// Método que adiciona quantidade do produto
	public void adicionarQuantidade(int qtd) {
		this.quantidade += qtd;
	}
	
	// Getters e setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public float getPorcentagemDoacao() {
		return porcentagemDoacao;
	}
	
	public void setPorcentagemDoacao(float porcentagemDoacao) {
		this.porcentagemDoacao = porcentagemDoacao;
	}
}
