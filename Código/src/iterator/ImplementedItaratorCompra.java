package iterator;
import java.util.List;

import compra.Compra;

public class ImplementedItaratorCompra implements IteratorCompra {

	private List<Compra> compras;

	public Compra getNext() {
		return null;
	}

	public boolean hasNext() {
		return false;
	}

}
