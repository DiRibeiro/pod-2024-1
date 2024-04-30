package savory.DAO;

import java.util.List;

import savory.Cliente;

public interface ClienteDAO {
    void adicionarCliente(Cliente cliente);
    Cliente buscarCliente(int idCliente);
    List<Cliente> listarClientes();
    void atualizarCliente(Cliente cliente);
    void removerCliente(int idCliente);
}
