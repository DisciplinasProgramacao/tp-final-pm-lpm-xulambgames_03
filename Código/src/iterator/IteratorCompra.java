package iterator;
import compra.Compra;

public interface IteratorCompra {

	// private Compra compra; // não pode ser private // veio com o astah
	// private Cliente cliente; // não pode ser private // veio com o astah

	public abstract boolean hasNext();
	public abstract Compra getNext();

}
