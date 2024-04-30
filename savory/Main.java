package savory;

import DAO.ClienteDAO;
import savory.DAO.PedidoDAO;
import savory.DAO.SalgadoDAO;
import savory.impl.ClienteDAOImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {

    private static ClienteDAO clienteDAO;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Configurar janela principal
        JFrame frame = new JFrame("Sistema de Pedidos de Salgados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Criar e configurar painel para as operações
        JPanel operationsPanel = new JPanel(new GridLayout(2, 2));

        // Botão para adicionar cliente
        JButton addClienteButton = new JButton("Adicionar Cliente");
        addClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente(frame);
            }
        });
        operationsPanel.add(addClienteButton);

        // Botão para listar clientes
        JButton listarClientesButton = new JButton("Listar Clientes");
        listarClientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientes(frame);
            }
        });
        operationsPanel.add(listarClientesButton);

        // Adicionar painel de operações à janela
        frame.add(operationsPanel, BorderLayout.NORTH);

        // Exibir janela
        frame.setVisible(true);
    }

    // Método para adicionar cliente
    private static void adicionarCliente(JFrame frame) {
        // Criar e configurar painel para o formulário
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField nomeField = new JTextField();
        JTextField enderecoField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField emailField = new JTextField();

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Endereço:"));
        panel.add(enderecoField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Adicionar Cliente", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Obter os dados do formulário
            String nome = nomeField.getText();
            String endereco = enderecoField.getText();
            String telefone = telefoneField.getText();
            String email = emailField.getText();

            // Criar e adicionar o cliente ao banco de dados
            Cliente cliente = new Cliente(nome, endereco, telefone, email);
            clienteDAO.adicionarCliente(cliente);

            JOptionPane.showMessageDialog(frame, "Cliente adicionado com sucesso!");
        }
    }

    // Método para listar clientes
    private static void listarClientes(JFrame frame) {
        // Obter a lista de clientes do banco de dados
        List<Cliente> clientes = clienteDAO.listarClientes();

        // Criar e configurar o modelo da tabela
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Endereço");
        model.addColumn("Telefone");
        model.addColumn("Email");

        // Preencher a tabela com os dados dos clientes
        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{cliente.getIdCliente(), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone(), cliente.getEmail()});
        }

        // Criar e configurar a tabela
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Mostrar a tabela na janela principal
        JOptionPane.showMessageDialog(frame, scrollPane, "Listar Clientes", JOptionPane.PLAIN_MESSAGE);
    }
}
