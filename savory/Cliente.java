package savory;

public class Cliente {
    private int idCliente;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    
	public Cliente(int id, String nome, String endereco, String telefone, String email) {
        this.idCliente = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

	public Cliente(String nome2, String endereco2, String telefone2, String email2) {
        this.nome = nome2;
        this.endereco = endereco2;
        this.telefone = telefone2;
        this.email = email2;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
