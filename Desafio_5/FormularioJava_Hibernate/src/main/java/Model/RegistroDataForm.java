package Model;
import javax.persistence.*;
import java.util.List;

//Mapeamento
@Entity
@Table(name = "registro_data_fix")
public class RegistroDataForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_data")
    private Integer idRegistro;
    private String nome;
    private String email;
    private String cpf;
    private String endereco;
    private String telefone;
    private String escolaridade;
    private String genero;
    private String dataDeNascimento;



    @OneToMany(mappedBy = "registroDataForm")
    private List<Contato> listadeContato;

    public List<Contato> getListadeContato() {
        return listadeContato;
    }

    public void setListadeContato(List<Contato> listadeContato) {
        this.listadeContato = listadeContato;
    }
    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer id) {
        this.idRegistro = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }


    @Override //Garante que você está sobrescrevendo um método e não criando um novo.

    public String toString() { //Retorna uma string do objeto

        int quantContato = 0;

        //Criando string dinamicamente
        StringBuilder stringDinamic = new StringBuilder();
        stringDinamic.append("\nID: ").append(getIdRegistro()).append("\n");
        stringDinamic.append("Nome: ").append(getNome()).append("\n");
        stringDinamic.append("Email: ").append(getEmail()).append("\n");
        stringDinamic.append("CPF: ").append(getCpf()).append("\n");
        stringDinamic.append("Endereco: ").append(getEndereco()).append("\n");
        stringDinamic.append("Telefone: ").append(getTelefone()).append("\n");
        stringDinamic.append("Escolaridade: ").append(getEscolaridade()).append("\n");
        stringDinamic.append("Genero: ").append(getGenero()).append("\n");
        stringDinamic.append("Data De Nascimento: ").append(getDataDeNascimento()).append("\n");
        for (int i = 0; i < getListadeContato().size(); i++) {
            Contato contato = getListadeContato().get(i);
            stringDinamic.append("\nContato Alternativo: ").append(quantContato+1).append("\n");
            stringDinamic.append("Nome: ").append(contato.getNomeContato()).append("\n");
            stringDinamic.append("Email: ").append(contato.getEmailContato()).append("\n");
            stringDinamic.append("Telefone: ").append(contato.getTelefoneContato()).append("\n");
            quantContato++;
        }
        quantContato = 0;
        return stringDinamic.toString();
    }

}

