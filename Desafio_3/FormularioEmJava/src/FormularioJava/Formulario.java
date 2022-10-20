package FormularioJava;

import java.security.KeyStore.ProtectionParameter;
import java.util.ArrayList;
//sysout + crtl + espaço


//Abstract Class não permite que novos objetos sejam instanciados a partir desta classe
public abstract class Formulario {

    //Declarando as variáveis principais
    protected String nome; //O protected faz com que apenas as classes filhas enxerguem tal atributo
    protected String email;  //Protected == Público para os filhos
    protected String cpf;
    protected String endereco;
    protected String telefone;
    protected String escolaridade;
    protected String genero;
    protected String dataDeNascimento;

    //private static int quantDeFormularios;  CONTADOR PRA USAR NA QUANT DE FORMULARIOS
    //STATIC QUER DIZER -> DA CLASSE
    //se tiver problema em puxar o objeto rever a aula 4.5 orientação a obj
    //private Contatos contatos = new Contatos();

//    public Formulario(String nome,String email, String cpf, String endereco, String telefone,
//                      String escolaridade, String genero, String dataDeNascimento) {
//        this.nome = nome;
//        this.email = email;
//        this.cpf = cpf;
//        this.endereco = endereco;
//        this.telefone = telefone;
//        this.escolaridade = escolaridade;
//        this.genero = genero;
//        this.dataDeNascimento = dataDeNascimento;
//    }
    //Getters and Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nomeValue) {
        this.nome = nomeValue;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String emailValue) {
        this.email = emailValue;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpfValue) {
        this.cpf = cpfValue;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String enderecoValue) {
        this.endereco = enderecoValue;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefoneValue){
        this.telefone = telefoneValue;
    }

    public String getEscolaridade() {
        return this.escolaridade;
    }

    public void setEscolaridade(String escolaridadeValue) {
        this.escolaridade = escolaridadeValue;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String generoValue) {
        this.genero = generoValue;
    }

    public String getDataDeNascimento() {
        return this.dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimentoValue) {
        this.dataDeNascimento = dataDeNascimentoValue;
    }

    //Criando ID & um vetor-lista p/ contato + ID

    protected String ID;
    private ArrayList<Contato> arrayList;

    public ArrayList<Contato> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Contato> arrayList) {
        this.arrayList = arrayList;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
