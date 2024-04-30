package savory.DAO;

import java.util.List;

import savory.ItemPedido;

public interface ItemPedidoDAO {
    void adicionarItemPedido(ItemPedido itemPedido);
    ItemPedido buscarItemPedido(int idItemPedido);
    List<ItemPedido> listarItensPedido();
    void atualizarItemPedido(ItemPedido itemPedido);
    void removerItemPedido(int idItemPedido);
}

