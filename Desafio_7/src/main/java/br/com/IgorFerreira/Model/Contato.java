package br.com.IgorFerreira.Model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

//Mapeamento
@Entity
@Getter
@Setter
@Table(name = "contato")
public class Contato{

    //Referenciando essa entidade contato com a entidade registro_data_fix
    @ManyToOne
    @JoinColumn(name="id_registro_data", nullable = false)
    RegistroDataForm registroDataForm;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato")
    private Integer IdContato;

    @Transient
    private Integer id_formulario;
    private String nomeContato;
    private String telefoneContato;
    private String emailContato;
}
