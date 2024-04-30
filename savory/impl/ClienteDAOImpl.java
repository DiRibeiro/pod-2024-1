package savory.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import savory.Cliente;
import savory.DAO.ClienteDAO;

public class ClienteDAOImpl implements ClienteDAO {
    private Connection conexao;

    // Construtor que recebe a conexão com o banco de dados
    public ClienteDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void adicionarCliente(Cliente cliente) {
        String query = "INSERT INTO Cliente (Nome, Endereco, Telefone, Email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscarCliente(int idCliente) {
        String query = "SELECT * FROM Cliente WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("ID_Cliente"),
                    rs.getString("Nome"),
                    rs.getString("Endereco"),
                    rs.getString("Telefone"),
                    rs.getString("Email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("ID_Cliente"),
                    rs.getString("Nome"),
                    rs.getString("Endereco"),
                    rs.getString("Telefone"),
                    rs.getString("Email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        String query = "UPDATE Cliente SET Nome = ?, Endereco = ?, Telefone = ?, Email = ? WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getIdCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerCliente(int idCliente) {
        String query = "DELETE FROM Cliente WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
