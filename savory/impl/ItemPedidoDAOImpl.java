package savory.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import savory.ItemPedido;
import savory.DAO.ItemPedidoDAO;

public class ItemPedidoDAOImpl implements ItemPedidoDAO {
    private Connection conexao;

    // Construtor que recebe a conexão com o banco de dados
    public ItemPedidoDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void adicionarItemPedido(ItemPedido itemPedido) {
        String query = "INSERT INTO ItemPedido (ID_Pedido, ID_Salgado, Quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, itemPedido.getPedido().getIdPedido());
            stmt.setInt(2, itemPedido.getSalgado().getIdSalgado());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemPedido buscarItemPedido(int idItemPedido) {
        String query = "SELECT * FROM ItemPedido WHERE ID_ItemPedido = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, idItemPedido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ItemPedido(
                    rs.getInt("ID_ItemPedido"),
                    new PedidoDAOImpl(conexao).buscarPedido(rs.getInt("ID_Pedido")),
                    new SalgadoDAOImpl(conexao).buscarSalgado(rs.getInt("ID_Salgado")),
                    rs.getInt("Quantidade")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemPedido> listarItensPedido() {
        List<ItemPedido> itensPedido = new ArrayList<>();
        String query = "SELECT * FROM ItemPedido";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itensPedido.add(new ItemPedido(
                    rs.getInt("ID_ItemPedido"),
                    new PedidoDAOImpl(conexao).buscarPedido(rs.getInt("ID_Pedido")),
                    new SalgadoDAOImpl(conexao).buscarSalgado(rs.getInt("ID_Salgado")),
                    rs.getInt("Quantidade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itensPedido;
    }

    @Override
    public void atualizarItemPedido(ItemPedido itemPedido) {
        String query = "UPDATE ItemPedido SET ID_Pedido = ?, ID_Salgado = ?, Quantidade = ? WHERE ID_ItemPedido = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, itemPedido.getPedido().getIdPedido());
            stmt.setInt(2, itemPedido.getSalgado().getIdSalgado());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setInt(4, itemPedido.getIdItemPedido());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerItemPedido(int idItemPedido) {
        String query = "DELETE FROM ItemPedido WHERE ID_ItemPedido = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, idItemPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
