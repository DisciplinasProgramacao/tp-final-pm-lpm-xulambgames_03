package jogo;

import excecao.TipoInvalidoExcecao;

public class JogoFactory { //Factory method

	public static Jogo creator(String tipo) throws TipoInvalidoExcecao {
		Jogo jogo = null;
		if (tipo.equalsIgnoreCase("regular")) {
			jogo = new JogoRegular();
		}
		else if (tipo.equalsIgnoreCase("promocao")) {
			jogo = new JogoPromocao();
		}
		else if (tipo.equalsIgnoreCase("lancamento")) {
			jogo = new JogoLancamento();
		}
		else if (tipo.equalsIgnoreCase("premium")) {
			jogo = new JogoPremuim();
		}
		else {
			throw new TipoInvalidoExcecao(" O " +tipo+ " não é um tipo válido para jogo");
		}
		return jogo;
	}

}
