package iterator;

import java.util.Queue;

import compra.Compra;

public class ImplementedItaratorCompra implements IteratorCompra { //Utilizando para gerar o extrato.

	private Queue<Compra> compras;

	public ImplementedItaratorCompra(Queue<Compra> compras) {
		this.compras = compras;
	}

	public Compra getNext() { // Pilha de compras, removendo o próximo da fila e retornando-a.
		return compras.remove();
	}

	public boolean hasNext() { // Pilha de compras, verifica se ainda existe um elemento na pilha.
		return !compras.isEmpty();
	}

}
