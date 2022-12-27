package br.com.IgorFerreira.Model;

import java.util.List;
import lombok.*;
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
}

