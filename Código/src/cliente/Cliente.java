package cliente;
import java.util.List;

import compra.Compra;
import iterator.IteratorCompra;

public abstract class Cliente implements ICliente {

	private String nome;
	private String nomeDeUsuario;
	private String senha;
	private String email;
	private List<Compra> compras;
	private TipoCliente tipoCliente;
	private Compra compra;
	private IteratorCompra iteratorCompra;

	public abstract double calculaMensalidade();

	public abstract double calculaDesconto();

}
