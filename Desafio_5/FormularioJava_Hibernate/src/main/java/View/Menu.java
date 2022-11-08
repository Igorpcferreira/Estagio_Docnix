package View;
import Controller.ControllerContato;
import Controller.ControllerRegistro;
import Model.Contato;
import Model.RegistroDataForm;
import Util.JpaUtil;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in); //entrada do teclado
    List<Contato> listaDeContatos = new ArrayList<>();
    ControllerRegistro controllerRegistro = new ControllerRegistro();

    ControllerContato controllerContato = new ControllerContato();

    EntityManager em = JpaUtil.getEntityManager();

    RegistroDataForm registroDataForm;
    Contato contato;

    private String idForm = "0";

    private String escolha;

    //Variaveis Auxiliares
    private String emailAux;
    private int emailValidacao;
    private String escolaridadeAux;
    private String generoAux;
    private char stringToCharAux;
    private String cpfAux;
    private String telefoneAux;
    private String telefoneContatoAux;
    private int editarAux = 0;
    private String contatoAux;



    private int TAMANHO_MAXIMO_TELEFONE = 11;

    public void MenuCRUD() {
        do {
            System.out.println(printMenuCRUD());
            escolha = checkNumberEscolha();
            switch(escolha)
            {
                //Adicionando o Formulário
                case "1":
                    incluirRegistroFormulario();
                    break;

                //Listando o Formulário
                case "2":
                    em.getTransaction().begin();
                    em.flush();
                    em.getTransaction().commit();
                    controllerRegistro.listarRegistro(em);
                    break;

                //Editando o Formulário
                case "3":
                    editarRegistroFormulario();
                    break;

                //Escluindo o Formulário
                case "4":
                    excluirRegistroFormulario();
                    break;

                case "5":
                    em.close();
                    System.exit(0);

                default:
                    System.out.print("Digite um valor válido \n");
            }

        } while (!escolha.equals("5"));
    }

    public void setRegistrarDadosFormulario(){
        setRegistrarDadosFixosFormulario();



        //Editando contato
        if (editarAux == 1) {
            listaDeContatos = new ArrayList<>();



            registroDataForm.setIdRegistro(Integer.valueOf(idForm));

            controllerRegistro.editarRegistro(registroDataForm);

            listaDeContatos = controllerContato.listarContato(Integer.valueOf(idForm), em);

            for (int j = 0; j < listaDeContatos.size(); j++) {

                System.out.println("\nContato Alternativo " + (j + 1) + "\n");

                contato = new Contato();

                contato.setIdContato(listaDeContatos.get(j).getIdContato());

                System.out.print("\nDigite o Nome do Contato: ");
                contato.setNomeContato(input.nextLine());
                System.out.print("\nDigite o Email do Contato: ");
                contato.setEmailContato(validarEmail());
                System.out.print("\nDigite o Telefone do Contato: ");

                telefoneContatoAux = input.nextLine();
                while(!validarTelefone(telefoneContatoAux))
                {
                    System.out.println("\nDigite um Número Válido!!  EX: 62986430079");
                    telefoneContatoAux = input.nextLine();
                }
                if(validarTelefone(telefoneContatoAux))
                {
                    contato.setTelefoneContato(printTelefone(telefoneContatoAux));
                }

                contato.setRegistroData(registroDataForm);
                controllerContato.editarContato(contato);
            }
            editarAux = 0;

        } else {
            controllerRegistro.incluirRegistro(registroDataForm);
            System.out.println(  "_____________________________________\n"+
                                "|*************************************|\n" +
                                "|*              CONTATO              *|\n" +
                                "|*                                   *|\n" +
                                "|*    A - Adicionar Outro Contato    *|\n" +
                                "|*    B - Salvar Formulário          *|\n" +
                                "|*                                   *|\n" +
                                "|*************************************|\n" +
                                " ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

            //Adicionando contato(s)
            contatoAux = input.nextLine();
            stringToCharAux = contatoAux.charAt(0);

            while(validarInputCaracter(stringToCharAux) == 'f')
            {
                System.out.println("Digite uma opcao válida");
                contatoAux = input.nextLine();
                stringToCharAux = contatoAux.charAt(0);
            }

            while (stringToCharAux == 'A') {
                contato = new Contato();
                System.out.print("\nDigite o Nome do Contatooooooooo: ");
                contato.setNomeContato(input.nextLine());
                System.out.print("\nDigite o Email do Contato: ");
                contato.setEmailContato(validarEmail());
                System.out.print("\nDigite o Telefone do Contato: ");
                telefoneContatoAux = input.nextLine();
                while(!validarTelefone(telefoneContatoAux))
                {
                    System.out.println("\nDigite um Número Válido!!  EX: 62986430079");
                    telefoneContatoAux = input.nextLine();
                }
                if(validarTelefone(telefoneContatoAux))
                {
                    contato.setTelefoneContato(printTelefone(telefoneContatoAux));
                }
                listaDeContatos.add(contato);
                System.out.println( " _____________________________________\n"+
                        "|*************************************|\n" +
                        "|*              CONTATO              *|\n" +
                        "|*                                   *|\n" +
                        "|*    A - Adicionar Outro Contato    *|\n" +
                        "|*    B - Salvar Formulário          *|\n" +
                        "|*                                   *|\n" +
                        "|*************************************|\n" +
                        " ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

                contatoAux = input.nextLine();
                stringToCharAux = contatoAux.charAt(0);

                listaDeContatos.add(contato);
                contato.setRegistroData(registroDataForm);
                controllerContato.incluirContato(contato);



            }
        }



        System.out.println(  "_______________________________________________\n"  +
                "|***********************************************|\n" +
                "|*                                             *|\n" +
                "|*                                             *|\n" +
                "|*  !>>>>FORMULÁRIO INCLUÍDO COM SUCESSO<<<<!  *|\n" +
                "|*                                             *|\n" +
                "|*                                             *|\n" +
                "|***********************************************|\n" +
                " ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }



    //Adicionando um Registro de dados fixos no Formulário
    public void setRegistrarDadosFixosFormulario() {
        registroDataForm = new RegistroDataForm();

        System.out.print("\nDigite seu Nome: ");
        registroDataForm.setNome(input.nextLine());
        System.out.print("\nDigite seu Email: ");
        registroDataForm.setEmail(validarEmail());
        System.out.print("\nDigite seu CPF: ");

        cpfAux = input.nextLine();
        while(validarCPF(cpfAux) == "false"){
            System.out.println("\nDigite um CPF válido!");
            cpfAux = input.nextLine();
        }
        if(validarCPF(cpfAux) == "true"){
            registroDataForm.setCpf(printCPF(cpfAux));
        }


        System.out.print("\nDigite seu endereco: ");
        registroDataForm.setEndereco(input.nextLine());
        System.out.print("\nDigite seu telefone: ");

        telefoneAux = input.nextLine();
        while(!validarTelefone(telefoneAux))
        {
            System.out.println("\nDigite um Número Válido!!  EX: 62986430079");
            telefoneAux = input.nextLine();
        }
        if(validarTelefone(telefoneAux))
        {
            registroDataForm.setTelefone(printTelefone(telefoneAux));
        }

        System.out.println( " __________________________________________________________________________\n"+
                "|**************************************************************************|\n" +
                "|*                            ESCOLARIDADE                                *|\n" +
                "|*                                                                        *|\n" +
                "|*      A  - Fundamental                                   - Incompleto   *|\n" +
                "|*      B  - Fundamental                                   - Completo     *|\n" +
                "|*      C  - Medio                                         - Incompleto   *|\n" +
                "|*      D  - Medio                                         - Completo     *|\n" +
                "|*      E  - Superior                                      - Incompleto   *|\n" +
                "|*      F  - Superior                                      - Completo     *|\n" +
                "|*      G  - Pós-graduação (Lato senso)                    - Incompleto   *|\n" +
                "|*      H  - Pós-graduação (Lato senso)                    - Completo     *|\n" +
                "|*      I  - Pós-graduação (Stricto sensu, nível mestrado) - Incompleto   *|\n" +
                "|*      J  - Pós-graduação (Stricto sensu, nível mestrado) - Completo     *|\n" +
                "|*      K  - Pós-graduação (Stricto sensu, nível doutor)   - Incompleto   *|\n" +
                "|*      L  - Pós-graduação (Stricto sensu, nível doutor)   - Completo     *|\n" +
                "|*                                                                        *|\n" +
                "|**************************************************************************|\n" +
                " ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");

        System.out.print("\nDigite o Código da sua Escolaridade: ");
        escolaridadeAux = input.nextLine();
        stringToCharAux = escolaridadeAux.charAt(0);

        while(stringToCharAux != 'A' && stringToCharAux != 'a' &&
                stringToCharAux != 'B' && stringToCharAux != 'b' &&
                stringToCharAux != 'C' && stringToCharAux != 'c' &&
                stringToCharAux != 'D' && stringToCharAux != 'd' &&
                stringToCharAux != 'E' && stringToCharAux != 'e' &&
                stringToCharAux != 'F' && stringToCharAux != 'f' &&
                stringToCharAux != 'G' && stringToCharAux != 'g' &&
                stringToCharAux != 'H' && stringToCharAux != 'h' &&
                stringToCharAux != 'I' && stringToCharAux != 'i' &&
                stringToCharAux != 'J' && stringToCharAux != 'j' &&
                stringToCharAux != 'K' && stringToCharAux != 'k' &&
                stringToCharAux != 'L' && stringToCharAux != 'l' )
        {
            System.out.println("Digite um código válido!");
            escolaridadeAux = input.nextLine();
            stringToCharAux = escolaridadeAux.charAt(0);
        }
        if(stringToCharAux != 'A' || stringToCharAux != 'a')
        {
            registroDataForm.setEscolaridade("Fundamental - Incompleto");
        }
        else if(stringToCharAux != 'B' || stringToCharAux != 'b')
        {
            registroDataForm.setEscolaridade("Fundamental - Completo");
        }
        else if(stringToCharAux != 'C' || stringToCharAux != 'c')
        {
            registroDataForm.setEscolaridade("Medio - Incompleto");
        }
        else if(stringToCharAux != 'D' || stringToCharAux != 'd')
        {
            registroDataForm.setEscolaridade("Medio - Completo");
        }
        else if(stringToCharAux != 'E' || stringToCharAux != 'e')
        {
            registroDataForm.setEscolaridade("Superior - Incompleto");
        }
        else if(stringToCharAux != 'F' || stringToCharAux != 'f')
        {
            registroDataForm.setEscolaridade("Superior - Completo");
        }
        else if(stringToCharAux != 'G' || stringToCharAux != 'g')
        {
            registroDataForm.setEscolaridade("Pós-graduação (Lato senso) - Incompleto");
        }
        else if(stringToCharAux != 'H' || stringToCharAux != 'h')
        {
            registroDataForm.setEscolaridade("Pós-graduação (Lato senso) - Completo");
        }
        else if(stringToCharAux != 'I' || stringToCharAux != 'i')
        {
            registroDataForm.setEscolaridade("Pós-graduação (Stricto sensu, nível mestrado) - Incompleto");
        }
        else if(stringToCharAux != 'J' || stringToCharAux != 'j')
        {
            registroDataForm.setEscolaridade("Pós-graduação (Stricto sensu, nível mestrado) - Completo");
        }
        else if(stringToCharAux != 'K' || stringToCharAux != 'k')
        {
            registroDataForm.setEscolaridade("Pós-graduação (Stricto sensu, nível doutor) - Incompleto ");
        }
        else if(stringToCharAux != 'L' || stringToCharAux != 'l')
        {
            registroDataForm.setEscolaridade("Pós-graduação (Stricto sensu, nível doutor) - Completo ");
        }
        System.out.println(  "_____________________________________\n"+
                "|*************************************|\n" +
                "|*              GÊNERO               *|\n" +
                "|*                                   *|\n" +
                "|*          A - Masculino            *|\n" +
                "|*          B - Feminino             *|\n" +
                "|*          C - Outros...            *|\n" +
                "|*                                   *|\n" +
                "|*************************************|\n" +
                " ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        System.out.print("\nDigite o código do seu Gênero: ");
        generoAux = input.nextLine();
        stringToCharAux = generoAux.charAt(0);
        while(stringToCharAux != 'A' && stringToCharAux != 'a' &&
                stringToCharAux != 'B' && stringToCharAux != 'b' &&
                stringToCharAux != 'C'  && stringToCharAux != 'c' )
        {
            System.out.println("Digite um código válido!");
            generoAux = input.nextLine();
            stringToCharAux = generoAux.charAt(0);
        }
        if(stringToCharAux == 'A' || stringToCharAux != 'a')
        {
            registroDataForm.setGenero("Masculino");
        }
        else if(stringToCharAux == 'B' || stringToCharAux != 'b')
        {
            registroDataForm.setGenero("Feminino");
        }
        else if(stringToCharAux == 'C' || stringToCharAux != 'c')
        {
            registroDataForm.setGenero("Outros");
        }
        System.out.print("\nDigite a sua Data de Nascimento: ");
        registroDataForm.setDataDeNascimento(input.nextLine());

        listaDeContatos = new ArrayList<>();
    }

    public String printMenuCRUD(){
        return " _____________________________________\n"+
                "|*************************************|\n" +
                "|*           FORMULARIO              *|\n" +
                "|*                                   *|\n" +
                "|*     1 - Incluir   Registro        *|\n" +
                "|*     2 - Listar    Registro(s)     *|\n" +
                "|*     3 - Editar    Registro        *|\n" +
                "|*     4 - Excluir   Registro        *|\n" +
                "|*     5 - Finalizar Programa        *|\n" +
                "|*                                   *|\n" +
                "|*************************************|\n" +
                " ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";

    }

    //Método para printar o telefone com o formato correto
    public String printTelefone(String TEL)
    {
        return ("(" + TEL.substring(0, 2) + ")" + TEL.substring(2, 7) + "-" + TEL.substring(7, 11));
    }


    //Método para incluir/adicionar um registroDataForm no Formulário
    public void incluirRegistroFormulario(){
        setRegistrarDadosFormulario();
//        em.close();
    }

    //Método para excluir um registroDataForm no Formulário
    public void excluirRegistroFormulario()  {

        System.out.println(  "_________________________________________________\n"  +
                            "|*************************************************|\n" +
                            "|*                                               *|\n" +
                            "|*                                               *|\n" +
                            "|*  !>>>> SELECIONE O ID PARA SER EXCLUÍDO <<<<! *|\n" +
                            "|*                                               *|\n" +
                            "|*                                               *|\n" +
                            "|*************************************************|\n" +
                            "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        listaDeContatos = new ArrayList<>();


        controllerRegistro.listarRegistro(em);
        idForm = validarInputNumero("ID");


        //Excluindo os Contatos
        listaDeContatos = controllerContato.listarContato(Integer.valueOf(idForm), em);
        for (int i = 0; i < listaDeContatos.size(); i++) {
            controllerContato.excluirContato(listaDeContatos.get(i).getIdContato(), listaDeContatos.get(i));
        }

        //Excluindo os Dados de Registro do Formulário
        registroDataForm = new RegistroDataForm();
        registroDataForm.setIdRegistro(Integer.valueOf(idForm));
        controllerRegistro.excluirRegistro(Integer.valueOf(idForm), registroDataForm);

//        em.close();

    }

    //Método para editar um registroDataForm no Formulário
    public void editarRegistroFormulario(){

        System.out.println(  "_________________________________________________\n"  +
                            "|*************************************************|\n" +
                            "|*                                               *|\n" +
                            "|*                                               *|\n" +
                            "|*  !>>>> SELECIONE O ID PARA SER EDITADO <<<<!  *|\n" +
                            "|*                                               *|\n" +
                            "|*                                               *|\n" +
                            "|*************************************************|\n" +
                            "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

        controllerRegistro.listarRegistro(em);

        idForm = validarInputNumero("ID");
        editarAux = 1;

        setRegistrarDadosFormulario();
//        em.close();


    }

    public String checkNumberEscolha() {
        return validarInputNumero("valor");
    }

    //Validações
    public String validarEmail(){
        emailAux = input.nextLine();
        do {
            emailValidacao = 0;
            if (emailAux.contains("@") && emailAux.contains(".")) {
                if (emailAux.indexOf("@") < emailAux.indexOf(".")) {
                    emailValidacao = 1;
                    return emailAux;
                }
            } else {
                System.out.println("Digite um Email Válido ex: igorpcferreira@gmail.com");
                emailAux = input.nextLine();
            }
        } while (emailValidacao != 1);
        return emailAux;
    }
    public static String validarCPF(String cpfAux) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpfAux.equals("00000000000") ||
                cpfAux.equals("11111111111") ||
                cpfAux.equals("22222222222") || cpfAux.equals("33333333333") ||
                cpfAux.equals("44444444444") || cpfAux.equals("55555555555") ||
                cpfAux.equals("66666666666") || cpfAux.equals("77777777777") ||
                cpfAux.equals("88888888888") || cpfAux.equals("99999999999") ||
                (cpfAux.length() != 11))
            return("false");

        char digito10, digito11;
        int soma, i, aux, caracterParaNumero, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            soma = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                caracterParaNumero = (int)(cpfAux.charAt(i) - 48);
                soma = soma + (caracterParaNumero * peso);
                peso = peso - 1;
            }

            aux = 11 - (soma % 11);
            if ((aux == 10) || (aux == 11))
                digito10 = '0';
            else digito10 = (char)(aux + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            soma = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                caracterParaNumero = (int)(cpfAux.charAt(i) - 48);
                soma = soma + (caracterParaNumero * peso);
                peso = peso - 1;
            }

            aux = 11 - (soma % 11);
            if ((aux == 10) || (aux == 11))
                digito11 = '0';
            else digito11 = (char)(aux + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((digito10 == cpfAux.charAt(9)) && (digito11 == cpfAux.charAt(10)))
                return("true");
            else return("false");
        } catch (InputMismatchException erro) {
            return("false");
        }
    }

    public static String printCPF(String cpfAux) {
        return(cpfAux.substring(0, 3) + "." + cpfAux.substring(3, 6) + "." +
                cpfAux.substring(6, 9) + "-" + cpfAux.substring(9, 11));
    }


    private boolean validarTelefone(String telefone) {

        //retira todos os caracteres não-numéricos (incluindo espaço,tab, etc)
        telefone = telefone.replaceAll("\\D","");

        //verifica se tem a qtde de numeros correta
        if (telefone.length() != TAMANHO_MAXIMO_TELEFONE) return false;

        //Se tiver 11 caracteres, verificar se começa com 9 o celular
        if (telefone.length() == TAMANHO_MAXIMO_TELEFONE && Integer.parseInt(telefone.substring(2, 3)) != 9) return false;

        //verifica se o numero foi digitado com todos os dígitos iguais
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(telefone.charAt(0)+"{"+telefone.length()+"}");
        java.util.regex.Matcher m = p.matcher(telefone);
        if(m.find()) return false;

        //DDDs validos
        Integer[] codigosDDD = {
                11, 12, 13, 14, 15, 16, 17, 18, 19,
                21, 22, 24, 27, 28, 31, 32, 33, 34,
                35, 37, 38, 41, 42, 43, 44, 45, 46,
                47, 48, 49, 51, 53, 54, 55, 61, 62,
                64, 63, 65, 66, 67, 68, 69, 71, 73,
                74, 75, 77, 79, 81, 82, 83, 84, 85,
                86, 87, 88, 89, 91, 92, 93, 94, 95,
                96, 97, 98, 99};
        //verifica se o DDD é valido (sim, da pra verificar rsrsrs)
        if ( java.util.Arrays.asList(codigosDDD).indexOf(Integer.parseInt(telefone.substring(0, 2))) == -1) return false;

        //Se o número só tiver dez digitos não é um celular e por isso o número logo após o DDD deve ser 2, 3, 4, 5 ou 7
        Integer[] prefixos = {2, 3, 4, 5, 7};

        if (telefone.length() == 10 && java.util.Arrays.asList(prefixos).indexOf(Integer.parseInt(telefone.substring(2, 3))) == -1) return false;

        //se passar por todas as validações acima, então está tudo certo
        return true;
    }

    public String validarInputNumero(String verificacao){
        String stringRegex = "[0-9]*";
        String campoInput = input.nextLine();
        while (!campoInput.matches(stringRegex)) {
            System.out.println("Digite um " + verificacao + " válido");
            campoInput = input.nextLine();
        }
        return campoInput;
    }

    public char validarInputCaracter(char verificacao)
    {
        if(verificacao != 'A' && verificacao != 'a' &&
                verificacao != 'B' && verificacao != 'b' )
        {
            return 'f';
        }
        else{
            return 't';
        }
    }

}
