package Model;

public class Contato extends Formulario {
    private String nomeContato;
    private String telefoneContato;
    private String emailContato;


    public String getNomeContato() {
        return this.nomeContato;
    }

    public void setNomeContato(String nomeContatoValue) {
        this.nomeContato = nomeContatoValue;
    }

    public String getEmailContato() {
        return this.emailContato;
    }

    public void setEmailContato(String emailContatoValue) {
        this.emailContato = emailContatoValue;
    }

    public String getTelefoneContato() {
        return this.telefoneContato;
    }

    public void setTelefoneContato(String telefoneContatoValue) {
        this.telefoneContato = telefoneContatoValue;
    }
}
