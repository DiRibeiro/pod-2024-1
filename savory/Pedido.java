package savory;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class Pedido {
	private int idPedido;
    private Date data;
    private Time hora;
    private String status;
    private String formaPagamento;
    private BigDecimal valorTotal;
    private Cliente cliente;
    
    public Pedido(int int1, java.sql.Date date, Time time, String string, String string2, BigDecimal bigDecimal,
    		Cliente buscarCliente) {
    	// TODO Auto-generated constructor stub
    }

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}
