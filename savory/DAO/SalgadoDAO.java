package savory.DAO;

import java.util.List;

import savory.Salgado;

public interface SalgadoDAO {
    void adicionarSalgado(Salgado salgado);
    Salgado buscarSalgado(int idSalgado);
    List<Salgado> listarSalgados();
    void atualizarSalgado(Salgado salgado);
    void removerSalgado(int idSalgado);
}
