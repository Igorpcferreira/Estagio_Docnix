package Model;

import java.util.List;


import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;


//Mapeamento
@Entity
@Getter
@Setter
@Table(name = "registro_data_fix")
public class RegistroDataForm {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_data")
    private Integer idFormulario;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String escolaridade;
    @Column(nullable = false)
    private String genero;
    @Column(nullable = false)
    private String dataDeNascimento;

    @OneToMany(mappedBy = "registroDataForm", cascade = CascadeType.ALL)
    private List<Contato> lista_de_contatos;


    @Override

    public String toString() {

        StringBuilder stringDinamic = new StringBuilder();
        stringDinamic.append("{ ").append("\n");
        stringDinamic.append("ID ").append(getIdFormulario()).append("\n");
        stringDinamic.append("Nome: ").append(getNome()).append("\n");
        stringDinamic.append("Email: ").append(getEmail()).append("\n");
        stringDinamic.append("CPF: ").append(getCpf()).append("\n");
        stringDinamic.append("Endereco: ").append(getEndereco()).append("\n");
        stringDinamic.append("Telefone: ").append(getTelefone()).append("\n");
        stringDinamic.append("Escolaridade: ").append(getEscolaridade()).append("\n");
        stringDinamic.append("Genero: ").append(getGenero()).append("\n");
        stringDinamic.append("Data De Nascimento: ").append(getDataDeNascimento()).append("\n");
        stringDinamic.append("\n").append("}");
        return stringDinamic.toString();
    }

}

