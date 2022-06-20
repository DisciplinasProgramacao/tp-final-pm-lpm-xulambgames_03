package cliente;

import java.io.Serializable;
import java.util.List;
import compra.Compra;
import iterator.IteratorCompra;

public interface ICliente extends Serializable{

	double calculaMensalidade();
	double calculaDesconto();
	void addCompras(Compra compra);
	IteratorCompra getExtrato();
	String getNome();
	String getNomeDeUsuario();
	String getSenha();
	String getEmail();
	List<Compra> getCompras();
	double calcularValorCompras(Compra compra);



}
