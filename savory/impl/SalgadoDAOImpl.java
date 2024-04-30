package savory.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import savory.Salgado;
import savory.DAO.SalgadoDAO;

public class SalgadoDAOImpl implements SalgadoDAO {
    private Connection conexao;

    // Construtor que recebe a conexão com o banco de dados
    public SalgadoDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void adicionarSalgado(Salgado salgado) {
        String query = "INSERT INTO Salgado (Descricao, Valor_unitario) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, salgado.getDescricao());
            stmt.setBigDecimal(2, salgado.getValorUnitario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Salgado buscarSalgado(int idSalgado) {
        String query = "SELECT * FROM Salgado WHERE ID_Salgado = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, idSalgado);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Salgado(
                    rs.getInt("ID_Salgado"),
                    rs.getString("Descricao"),
                    rs.getBigDecimal("Valor_unitario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Salgado> listarSalgados() {
        List<Salgado> salgados = new ArrayList<>();
        String query = "SELECT * FROM Salgado";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                salgados.add(new Salgado(
                    rs.getInt("ID_Salgado"),
                    rs.getString("Descricao"),
                    rs.getBigDecimal("Valor_unitario")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salgados;
    }

    @Override
    public void atualizarSalgado(Salgado salgado) {
        String query = "UPDATE Salgado SET Descricao = ?, Valor_unitario = ? WHERE ID_Salgado = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, salgado.getDescricao());
            stmt.setBigDecimal(2, salgado.getValorUnitario());
            stmt.setInt(3, salgado.getIdSalgado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerSalgado(int idSalgado) {
        String query = "DELETE FROM Salgado WHERE ID_Salgado = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, idSalgado);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
