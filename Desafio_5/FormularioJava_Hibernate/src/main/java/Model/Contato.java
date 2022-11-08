package Model;


import javax.persistence.*;

//Mapeamento
@Entity
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
    private String nomeContato;
    private String emailContato;
    private String telefoneContato;


    public Integer getIdContato() {
        return IdContato;
    }

    public void setIdContato(Integer id) {
        this.IdContato = id;
    }
    public RegistroDataForm getRegistroData() {
        return registroDataForm;
    }

    public void setRegistroData(RegistroDataForm registroDataForm) {
        this.registroDataForm = registroDataForm;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }
}
