package savory;

public class ItemPedido {
	private int idItemPedido;
    private Pedido pedido;
    private Salgado salgado;
    private int quantidade;
    
	public ItemPedido(int int1, Pedido buscarPedido, Salgado buscarSalgado, int int2) {
		// TODO Auto-generated constructor stub
	}
	public int getIdItemPedido() {
		return idItemPedido;
	}
	public void setIdItemPedido(int idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Salgado getSalgado() {
		return salgado;
	}
	public void setSalgado(Salgado salgado) {
		this.salgado = salgado;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
