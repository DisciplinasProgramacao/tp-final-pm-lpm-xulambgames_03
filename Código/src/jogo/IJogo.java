package jogo;

import java.io.Serializable;

public interface IJogo extends Serializable{

	double calcularPreco();
	void comprarJogo();
	int getNumComprados();
	String getTitulo();

}
