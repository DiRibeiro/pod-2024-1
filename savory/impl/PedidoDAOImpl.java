package savory.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import savory.Pedido;
import savory.DAO.PedidoDAO;

public class PedidoDAOImpl implements PedidoDAO {
    private Connection conexao;

    // Construtor que recebe a conexão com o banco de dados
    public PedidoDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void adicionarPedido(Pedido pedido) {
        String query = "INSERT INTO Pedido (Data, Hora, Status, Forma_de_pagamento, Valor_total, ID_Cliente) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(pedido.getData().getTime()));
            stmt.setTime(2, pedido.getHora());
            stmt.setString(3, pedido.getStatus());
            stmt.setString(4, pedido.getFormaPagamento());
            stmt.setBigDecimal(5, pedido.getValorTotal());
            stmt.setInt(6, pedido.getCliente().getIdCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido buscarPedido(int idPedido) {
        String query = "SELECT * FROM Pedido WHERE ID_Pedido = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pedido(
                    rs.getInt("ID_Pedido"),
                    rs.getDate("Data"),
                    rs.getTime("Hora"),
                    rs.getString("Status"),
                    rs.getString("Forma_de_pagamento"),
                    rs.getBigDecimal("Valor_total"),
                    new ClienteDAOImpl(conexao).buscarCliente(rs.getInt("ID_Cliente"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT * FROM Pedido";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pedidos.add(new Pedido(
                    rs.getInt("ID_Pedido"),
                    rs.getDate("Data"),
                    rs.getTime("Hora"),
                    rs.getString("Status"),
                    rs.getString("Forma_de_pagamento"),
                    rs.getBigDecimal("Valor_total"),
                    new ClienteDAOImpl(conexao).buscarCliente(rs.getInt("ID_Cliente"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public void atualizarPedido(Pedido pedido) {
        String query = "UPDATE Pedido SET Data = ?, Hora = ?, Status = ?, Forma_de_pagamento = ?, Valor_total = ?, ID_Cliente = ? WHERE ID_Pedido = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(pedido.getData().getTime()));
            stmt.setTime(2, pedido.getHora());
            stmt.setString(3, pedido.getStatus());
            stmt.setString(4, pedido.getFormaPagamento());
            stmt.setBigDecimal(5, pedido.getValorTotal());
            stmt.setInt(6, pedido.getCliente().getIdCliente());
            stmt.setInt(7, pedido.getIdPedido());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerPedido(int idPedido) {
        String query = "DELETE FROM Pedido WHERE ID_Pedido = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
