package Util;
import View.Master;
import org.junit.Assert;
import org.junit.Test;

public class MasterUtilTest {

    Master master = new Master();

    public static final String cpf_VALIDO = "03482190160";
    public static final String cpf_INVALIDO = "03445713910";
    public static final String cpf_COM_LETRA = "034ABC73813";
    public static final String cpf_COM_CARACTER_ESPECIAL = "03482@$9016";
    public static final String cpf_COM_NUMEROS_REPETIDOS = "11111111111";

    public static final String cpf_VAZIO = "";
    public static final String cpf_LENGHT_INVALIDO = "034821901";

    public static final String telefone_VALIDO = "62986430079";
    public static final String telefone_INVALIDO = "62685182791";
    public static final String telefone_Sem_Digito9 = "6286430079";
    public static final String telefone_Com_DDD_Invalido = "10986430079";
    public static final String telefone_NaoEhCelular_VALIDO = "6232921094";
    public static final String telefone_NaoEhCelular_INVALIDO = "6292921094";
    public static final String telefone_LENGHT_INVALIDO = "629864300";

    public static final String email_VALIDO = "aaaaaaa@bbbbb.com.br";
    public static final String email_SemArroba = "aaaaabbbbb.com.br";
    public static final String email_VAZIO = "";




    @Test
    public void testaCpfValidoDeveRetornarTrue(){
        boolean cpf = master.validaCPF(cpf_VALIDO);
        Assert.assertTrue(cpf);
    }

    @Test
    public void testaCpfInvalidoDeveRetornarFalse(){
        boolean cpf = master.validaCPF(cpf_INVALIDO);
        Assert.assertFalse(cpf);
    }

    @Test
    public void testaCpfComLetraDeveRetornarFalse(){
        boolean cpf = master.validaCPF(cpf_COM_LETRA);
        Assert.assertFalse(cpf);
    }

    @Test
    public void testaCpfComCaracterEspecialDeveRetornarFalse(){
        boolean cpf = master.validaCPF(cpf_COM_CARACTER_ESPECIAL);
        Assert.assertFalse(cpf);
    }

    @Test
    public void testaCpfComNumerosRepetidosDeveRetornarFalse(){
        boolean cpf = master.validaCPF(cpf_COM_NUMEROS_REPETIDOS);
        Assert.assertFalse(cpf);
    }

    @Test
    public void testaCpfVazioDeveRetornarFalse(){
        boolean cpf = master.validaCPF(cpf_VAZIO);
        Assert.assertFalse(cpf);
    }

    @Test
    public void testaCpfComLenghtInvalidoDeveRetornarFalse(){
        boolean cpf = master.validaCPF(cpf_LENGHT_INVALIDO);
        Assert.assertFalse(cpf);
    }

    @Test
    public void testaTelefoneValidoDeveRetornarTrue(){
        boolean telefone = master.validaTelefone(telefone_VALIDO);
        Assert.assertTrue(telefone);
    }

    @Test
    public void testaTelefoneInvalidoDeveRetornarFalse(){
        boolean telefone = master.validaTelefone(telefone_INVALIDO);
        Assert.assertFalse(telefone);
    }

    @Test
    public void testaTelefoneSemDigito9DeveRetornarFalse(){
        boolean telefone = master.validaTelefone(telefone_Sem_Digito9);
        Assert.assertFalse(telefone);
    }

    @Test
    public void testaTelefoneComDddInvalidoDeveRetornarFalse(){
        boolean telefone = master.validaTelefone(telefone_Com_DDD_Invalido);
        Assert.assertFalse(telefone);
    }

    @Test
    public void testaTelefoneNaoEhCelularValidoDeveRetornarFalse(){
        boolean telefone = master.validaTelefone(telefone_NaoEhCelular_VALIDO);
        Assert.assertTrue(telefone);
    }

    @Test
    public void testaTelefoneNaoEhCelularInvalidoDeveRetornarFalse(){
        boolean telefone = master.validaTelefone(telefone_NaoEhCelular_INVALIDO);
        Assert.assertFalse(telefone);
    }

    @Test
    public void testaTelefoneLenghtInvalidoDeveRetornarFalse(){
        boolean telefone = master.validaTelefone(telefone_LENGHT_INVALIDO);
        Assert.assertFalse(telefone);
    }

    @Test
    public void testaEmailValidoDeveRetornarTrue(){
        boolean email = master.validaEmail(email_VALIDO);
        Assert.assertTrue(email);
    }

    @Test
    public void testaEmailSemArrobaDeveRetornarFalse(){
        boolean email = master.validaEmail(email_SemArroba);
        Assert.assertFalse(email);
    }

    @Test
    public void testaEmailVazioDeveRetornarFalse(){
        boolean email = master.validaEmail(email_VAZIO);
        Assert.assertFalse(email);
    }

}
