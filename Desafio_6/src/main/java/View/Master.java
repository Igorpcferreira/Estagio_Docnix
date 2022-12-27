package View;
import Model.*;
import Controller.*;

import org.json.JSONObject;
import Util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Master {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormularioJava_Servlet");
    List<Contato> listaDeContatos;
    ControllerRegistro controllerRegistro = new ControllerRegistro();
    ControllerContato controllerContato = new ControllerContato();
    RegistroDataForm registroDataForm;
    Contato contato;
    private int editarAux = 0;
    private int emailValidacao;
    private static String somenteNumeroRegex = "[0-9]*";




    public String leitorDeRequestBody(HttpServletRequest req) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String data = buffer.toString();
        return data;
    }

    public JSONObject stringToJson(String stringParaConverter) {
        JSONObject json = new JSONObject(stringParaConverter);
        return json;
    }

    public void registrarDadosForm(HttpServletRequest req) throws IOException {
        String dadosRequest = leitorDeRequestBody(req);
        JSONObject json = stringToJson(dadosRequest);

        registrarDadosFixosForm(json);
        registrarDadosContatos(json, registroDataForm);
        if (editarAux == 1) {
            registroDataForm.setIdFormulario(json.getInt("id"));
            controllerRegistro.editar(registroDataForm);
            editarAux = 0;
        }
        System.out.println(registroDataForm);

    }

    public void registrarDadosFixosForm(JSONObject dados) {
        registroDataForm = new RegistroDataForm();
        registroDataForm.setNome(dados.getString("nome"));
        registroDataForm.setEmail(dados.getString("email"));
        registroDataForm.setCpf(dados.getString("cpf"));
        registroDataForm.setEndereco(dados.getString("endereco"));
        registroDataForm.setTelefone(dados.getString("telefone"));
        registroDataForm.setEscolaridade(dados.getString("escolaridade"));
        registroDataForm.setGenero(dados.getString("genero"));
        registroDataForm.setDataDeNascimento(dados.getString("dataDeNascimento"));

    }

    public void registrarDadosContatos(JSONObject dados, RegistroDataForm registroDataForm) {
        listaDeContatos = new ArrayList<>();

        if (dados.has("sizeListaDeContatos"));
        {
            if (editarAux == 1) {
                EntityManager em = JpaUtil.getEntityManager(emf);
                listaDeContatos = controllerContato.listar(dados.getInt("id"), em);
            }

            Integer sizeListaDeContatos = Integer.valueOf(dados.getString("sizeListaDeContatos"));
            for (int i = 1; i <= sizeListaDeContatos; i++) {
                contato = new Contato();

                contato.setNomeContato(String.valueOf(dados.getString("nomeContato" + i)));

                contato.setEmailContato(String.valueOf(dados.getString("emailContato" + i)));

                contato.setTelefoneContato(String.valueOf(dados.getString("telefoneContato" + i)));

                contato.setRegistroDataForm(registroDataForm);
                salvarOuEditarContato(i-1, dados, contato);
            }
            registroDataForm.setLista_de_contatos(listaDeContatos);
        }
        if (editarAux == 0) {
            controllerRegistro.incluir(registroDataForm);

        }
    }

    public void salvarOuEditarContato(int index, JSONObject dados, Contato contato) {
        if (editarAux == 1) {
            contato.setIdContato(listaDeContatos.get(index).getIdContato());
            listaDeContatos.set(index, contato);
        } else {
            listaDeContatos.add(contato);
        }
    }

    public List<RegistroDataForm> listarDadosDeRegistroFormulario() {
        EntityManager em = JpaUtil.getEntityManager(emf);
        return controllerRegistro.listar(em);
    }

    public void editarDadosDeRegistroFormulario(HttpServletRequest req) throws IOException {
        editarAux = 1;
        registrarDadosForm(req);

    }

    public void excluirDadosDeRegistroFormulario(String id) {
        registroDataForm = new RegistroDataForm();
        registroDataForm.setIdFormulario(Integer.valueOf(id));
        controllerRegistro.excluir(Integer.valueOf(id), registroDataForm);
    }


    public boolean validaCPF(String cpfAux) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpfAux.equals("00000000000") ||
                cpfAux.equals("11111111111") ||
                cpfAux.equals("22222222222") || cpfAux.equals("33333333333") ||
                cpfAux.equals("44444444444") || cpfAux.equals("55555555555") ||
                cpfAux.equals("66666666666") || cpfAux.equals("77777777777") ||
                cpfAux.equals("88888888888") || cpfAux.equals("99999999999") ||
                (cpfAux.length() != 11))
            return false;

        else if (!cpfAux.matches(somenteNumeroRegex)) return false;


        char digito10, digito11;
        int soma, i, aux, caracterParaNumero, peso;


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
                return true;
            else return false;
        } catch (InputMismatchException erro) {
            return false;
        }
    }

    public boolean validaTelefone(String telefone) {

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
        //verifica se o DDD é valido
        if ( java.util.Arrays.asList(codigosDDD).indexOf(Integer.parseInt(telefone.substring(0, 2))) == -1) return false;

        //Se o número só tiver dez digitos não é um celular e por isso o número logo após o DDD deve ser 2, 3, 4, 5 ou 7
        Integer[] prefixos = {2, 3, 4, 5, 7};

        if (telefone.length() == 10 && java.util.Arrays.asList(prefixos).indexOf(Integer.parseInt(telefone.substring(2, 3))) == -1) return false;

        return true;
    }

    public boolean validaEmail(String emailAux){

        do {
            emailValidacao = 0;
            if (emailAux.contains("@") && emailAux.contains(".")) {
                if (emailAux.indexOf("@") < emailAux.indexOf(".")) {
                    emailValidacao = 1;
                    return true;
                }
            } else {
                return false;
            }
        } while (emailValidacao != 1);
        return true;
    }


}
