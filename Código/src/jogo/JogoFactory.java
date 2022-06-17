package jogo;

public class JogoFactory {

	public static IJogo criator(CategoriaJogos tipoJogo, String titulo, double preco, String genero, int classificacaoIndicativa, String produtora) throws Exception {
		IJogo jogo = null;
		if (tipoJogo == null) {
			throw new Exception("Tipo do jogo não expecificado");
		}
		if (tipoJogo == CategoriaJogos.REGULAR) {
			jogo = new JogoRegular(titulo, preco, genero, classificacaoIndicativa, produtora);
		}
		if (tipoJogo == CategoriaJogos.PROMOCAO) {
			jogo = new JogoPromocao(titulo, preco, genero, classificacaoIndicativa, produtora);
		}
		if (tipoJogo == CategoriaJogos.LANCAMENTO) {
			jogo = new JogoLancamento(titulo, preco, genero, classificacaoIndicativa, produtora);
		}
		if (tipoJogo == CategoriaJogos.PREMIUM) {
			jogo = new JogoPremuim(titulo, preco, genero, classificacaoIndicativa, produtora);
		}
		return jogo;
	}

}
