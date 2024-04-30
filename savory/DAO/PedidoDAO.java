package savory.DAO;

import java.util.List;

import savory.Pedido;

public interface PedidoDAO {
    void adicionarPedido(Pedido pedido);
    Pedido buscarPedido(int idPedido);
    List<Pedido> listarPedidos();
    void atualizarPedido(Pedido pedido);
    void removerPedido(int idPedido);
}