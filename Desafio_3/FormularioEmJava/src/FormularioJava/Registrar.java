package FormularioJava;

public class Registrar extends Formulario {
    @Override //Garante que você está sobrescrevendo um método e não criando um novo.

    public String toString() { //Retorna uma string do objeto

        int quantContato = 0;

        //Criando string dinamicamente
        StringBuilder stringDinamic = new StringBuilder();
        stringDinamic.append("\nID: ").append(super.getID()).append("\n");
        stringDinamic.append("Nome: ").append(super.getNome()).append("\n");
        stringDinamic.append("Email: ").append(super.getEmail()).append("\n");
        stringDinamic.append("CPF: ").append(super.getCpf()).append("\n");
        stringDinamic.append("Endereco: ").append(super.getEndereco()).append("\n");
        stringDinamic.append("Telefone: ").append(super.getTelefone()).append("\n");
        stringDinamic.append("Escolaridade: ").append(super.getEscolaridade()).append("\n");
        stringDinamic.append("Genero: ").append(super.getGenero()).append("\n");
        stringDinamic.append("Data De Nascimento: ").append(super.getDataDeNascimento()).append("\n");
        for (int i = 0; i < getArrayList().size(); i++) {
            Contato contatoExtra = getArrayList().get(i);
            stringDinamic.append("\nContato Alternativo: ").append(quantContato+1).append("\n");
            stringDinamic.append("Nome: ").append(contatoExtra.getNomeContato()).append("\n");
            stringDinamic.append("Email: ").append(contatoExtra.getEmailContato()).append("\n");
            stringDinamic.append("Telefone: ").append(contatoExtra.getTelefoneContato()).append("\n");
            quantContato++;
        }
        quantContato = 0;
        return stringDinamic.toString();
    }

}

