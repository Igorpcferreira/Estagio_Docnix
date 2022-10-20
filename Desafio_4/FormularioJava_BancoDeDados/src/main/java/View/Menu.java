package View;
import Controller.ControllerContato;
import Model.Registro;
//import DAO.RegistroDAO;
import Model.Contato;
import Controller.ControllerRegistro;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in); //entrada do teclado
    public ArrayList<Registro> registroArray = new ArrayList();
    public ArrayList<Contato> contatoArray = new ArrayList();
    ControllerRegistro controllerRegistro = new ControllerRegistro();

    ControllerContato controllerContato = new ControllerContato();

    Registro registro;
    Contato contato;

    private String id = "0";
    private String escolha;
    private String idEscolha;
    private String idValidacao;

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
    private int contAux = 0;
    int teste = 0;
    //Regular Expression - Vai validar padrões no texto
    private String stringRegex = "[0-9]*";  //Vai procurar qualquer caractere que não é um dígito
    private String caracterRegex = "[A-Z]*";

    public void MenuCRUD() throws SQLException {
        do {
            System.out.println(printMenuCRUD());
            escolha = checkNumberEscolha();

            switch(escolha)
            {
                //Adicionando o Formulário
                case "1": incluirArray();
                    break;

                //Listando o Formulário
                case "2":
                    controllerRegistro.listar();
                    break;

                //Editando o Formulário
                case "3":
                    editarArray();
                    break;

                //Escluindo o Formulário
                case "4":
                    excluirArray();
                    break;

            }

        } while (!escolha.equals("5"));
    }

    public void setFormulario() throws SQLException {
        setRegistrar();



        //Editando contato
        if (editarAux == 1) {
            contatoArray = new ArrayList<>();
            contatoArray = ControllerContato.listar(Integer.valueOf(idEscolha));
            //System.out.println("EU entro?");
            for (int i = 0; i < contatoArray.size(); i++) {
                if (idEscolha.equals(contatoArray.get(i).getID())) {

                    for (int j = 0; j < contatoArray.size(); j++) {
                        System.out.println("\nContato Alternativo " + (j + 1) + "\n");

                        contato = new Contato();

                        contato.setID(contatoArray.get(j).getID());//

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
                        //Passando os parâmetros pro ControllerContato passar pro ContatoDao
                        ControllerContato.editar(idEscolha, contato);
                    }
                        registro.setArrayList(ControllerContato.listar(Integer.valueOf(idEscolha)));
                        editarAux = 0;
                }
            }

        } else {

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
                //contatoArray.add(contato);
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
                contatoArray.add(contato);
            }
            registro.setArrayList(contatoArray);
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

    //Adicionando um Registro no Formulário
    public void setRegistrar() {
        registro = new Registro();
        if (editarAux == 1) {
            registro.setID(idEscolha);
        } else {
            registro.setID(id);
            id = String.valueOf(Integer.parseInt(id) + 1);
        }
        System.out.print("\nDigite seu Nome: ");
        registro.setNome(input.nextLine());
        System.out.print("\nDigite seu Email: ");
        registro.setEmail(validarEmail());
        System.out.print("\nDigite seu CPF: ");

        cpfAux = input.nextLine();
        while(isCPF(cpfAux) == "false"){
            System.out.println("\nDigite um CPF válido!");
            cpfAux = input.nextLine();
        }
        if(isCPF(cpfAux) == "true"){
            registro.setCpf(printCPF(cpfAux));
        }


        System.out.print("\nDigite seu endereco: ");
        registro.setEndereco(input.nextLine());
        System.out.print("\nDigite seu telefone: ");

        telefoneAux = input.nextLine();
        while(!validarTelefone(telefoneAux))
        {
            System.out.println("\nDigite um Número Válido!!  EX: 62986430079");
            telefoneAux = input.nextLine();
        }
        if(validarTelefone(telefoneAux))
        {
            registro.setTelefone(printTelefone(telefoneAux));
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
        while(stringToCharAux != 'A' &&
                stringToCharAux != 'B' &&
                stringToCharAux != 'C' &&
                stringToCharAux != 'D' &&
                stringToCharAux != 'E' &&
                stringToCharAux != 'F' &&
                stringToCharAux != 'G' &&
                stringToCharAux != 'H' &&
                stringToCharAux != 'I' &&
                stringToCharAux != 'J' &&
                stringToCharAux != 'K' &&
                stringToCharAux != 'L'  )
        {
            System.out.println("Digite um código válido! OBS: COM LETRA MAIÚSCULA");
            escolaridadeAux = input.nextLine();
            stringToCharAux = escolaridadeAux.charAt(0);
        }
        if(stringToCharAux == 'A')
        {
            registro.setEscolaridade("Fundamental - Incompleto");
        }
        else if(stringToCharAux == 'B')
        {
            registro.setEscolaridade("Fundamental - Completo");
        }
        else if(stringToCharAux == 'C')
        {
            registro.setEscolaridade("Medio - Incompleto");
        }
        else if(stringToCharAux == 'D')
        {
            registro.setEscolaridade("Medio - Completo");
        }
        else if(stringToCharAux == 'E')
        {
            registro.setEscolaridade("Superior - Incompleto");
        }
        else if(stringToCharAux == 'F')
        {
            registro.setEscolaridade("Superior - Completo");
        }
        else if(stringToCharAux == 'G')
        {
            registro.setEscolaridade("Pós-graduação (Lato senso) - Incompleto");
        }
        else if(stringToCharAux == 'H')
        {
            registro.setEscolaridade("Pós-graduação (Lato senso) - Completo");
        }
        else if(stringToCharAux == 'I')
        {
            registro.setEscolaridade("Pós-graduação (Stricto sensu, nível mestrado) - Incompleto");
        }
        else if(stringToCharAux == 'J')
        {
            registro.setEscolaridade("Pós-graduação (Stricto sensu, nível mestrado) - Completo");
        }
        else if(stringToCharAux == 'K')
        {
            registro.setEscolaridade("Pós-graduação (Stricto sensu, nível doutor) - Incompleto ");
        }
        else if(stringToCharAux == 'L')
        {
            registro.setEscolaridade("Pós-graduação (Stricto sensu, nível doutor) - Completo ");
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
        while(stringToCharAux != 'A' &&
                stringToCharAux != 'B' &&
                stringToCharAux != 'C'   )
        {
            System.out.println("Digite um código válido! OBS: COM LETRA MAIÚSCULA");
            generoAux = input.nextLine();
            stringToCharAux = generoAux.charAt(0);
        }
        if(stringToCharAux == 'A')
        {
            registro.setGenero("Masculino");
        }
        else if(stringToCharAux == 'B')
        {
            registro.setGenero("Feminino");
        }
        else if(stringToCharAux == 'C')
        {
            registro.setGenero("Outros");
        }
        System.out.print("\nDigite a sua Data de Nascimento: ");
        registro.setDataDeNascimento(input.nextLine());

        contatoArray = new ArrayList();
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


    //Método para incluir/adicionar um registro no Formulário
    public void incluirArray() throws SQLException {
        setFormulario();


        controllerContato.incluir(contatoArray, ControllerRegistro.incluir(registro));

//        controllerRegistro.incluir(registro);
//        controllerContato.incluir(registro, registro.getID());

    }

    //Método para excluir um registro no Formulário
    public void excluirArray() throws SQLException {

        System.out.println(  "_________________________________________________\n"  +
                            "|*************************************************|\n" +
                            "|*                                               *|\n" +
                            "|*                                               *|\n" +
                            "|*  !>>>> SELECIONE O ID PARA SER EXCLUÍDO <<<<! *|\n" +
                            "|*                                               *|\n" +
                            "|*                                               *|\n" +
                            "|*************************************************|\n" +
                            "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        controllerRegistro.listar();
        idEscolha = validarInputNumero("ID");

        controllerRegistro.excluir(String.valueOf(controllerContato.excluir(idEscolha)));
        controllerRegistro.listar();


        id = String.valueOf(registroArray.size());
    }

    //Método para editar um registro no Formulário
    public void editarArray() throws SQLException {

        System.out.println(  "_________________________________________________\n"  +
                            "|*************************************************|\n" +
                            "|*                                               *|\n" +
                            "|*                                               *|\n" +
                            "|*  !>>>> SELECIONE O ID PARA SER EDITADO <<<<!  *|\n" +
                            "|*                                               *|\n" +
                            "|*                                               *|\n" +
                            "|*************************************************|\n" +
                            "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        controllerRegistro.listar();
        idEscolha = validarInputNumero("ID");
        editarAux = 1;
        setFormulario();
        controllerRegistro.editar(idEscolha, registro);


        for (int i = 0; i < registroArray.size(); i++) {
            System.out.println(registroArray.get(i).toString());
        }

    }

    public String checkNumberEscolha(){
        return validarInputNumero("value");
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
    public static String isCPF(String cpfAux) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpfAux.equals("00000000000") ||
                cpfAux.equals("11111111111") ||
                cpfAux.equals("22222222222") || cpfAux.equals("33333333333") ||
                cpfAux.equals("44444444444") || cpfAux.equals("55555555555") ||
                cpfAux.equals("66666666666") || cpfAux.equals("77777777777") ||
                cpfAux.equals("88888888888") || cpfAux.equals("99999999999") ||
                (cpfAux.length() != 11))
            return("false");

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(cpfAux.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpfAux.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpfAux.charAt(9)) && (dig11 == cpfAux.charAt(10)))
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
        if (!(telefone.length() >= 10 && telefone.length() <= 11)) return false;

        //Se tiver 11 caracteres, verificar se começa com 9 o celular
        if (telefone.length() == 11 && Integer.parseInt(telefone.substring(2, 3)) != 9) return false;

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
        String verificacaoInputNum = input.nextLine();

        while(!verificacaoInputNum.matches(stringRegex)){
            System.out.println("Digite um "+ verificacao   + " valido!");
            verificacaoInputNum = input.nextLine();
        }
        return verificacaoInputNum;
    }

    public char validarInputCaracter(char verificacao)
    {
        if(verificacao != 'A' && verificacao != 'B')
        {
            return 'f';
        }
        else{
            return 't';
        }
    }

    public String validarID() {
        idValidacao = validarInputNumero("ID");
        for (int i = 0; i <= registroArray.size(); i++) {
            if (id.equals(String.valueOf(i))) {
                contAux++;
            }
        }

        if(contAux == 0){
            System.out.println("Digite um ID válido");
        }
        return idValidacao;
    }


}
