package jogo;

import excecao.TipoInvalidoExcecao;

public class JogoFactory {

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
		else if (tipo.equalsIgnoreCase("premuim")) {
			jogo = new JogoPremuim();
		}
		else {
			throw new TipoInvalidoExcecao(" o " + tipo+ " não é um tipo válido para jogo");
		}
		return jogo;
	}

}
